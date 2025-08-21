# Jenkins 기반 CI/CD 흐름

### **🧱 인프라 구성도**

```bash
[Jenkins Server] (server-002)
   ↓ SSH (같은 서브넷)
[Target Server] (server-001)
   ├── Docker 네트워크 (bridge/ubuntu_net)
   │    ├── app_8080 (포트 8080) or app_8081 (포트 8081)
   │    └── redis (내부 통신용, 외부 노출 없음)
   └── Nginx (리버스 프록시: 80 → 8080 또는 8081)
```

- Jenkins에서 바로 SSH로 private-server-001(api) 접속하여 배포

### **🛠️ Jenkins 파이프라인 흐름**

**📄 Jenkinsfile 구성 요약**

| **단계** | **설명** |
| --- | --- |
| Git Clone | develop 브랜치 기준으로 코드 클론 |
| Build JAR | Gradle로 bootJar 생성 |
| Docker Build | JAR 기반 Docker 이미지 빌드 및 DockerHub 푸시 |
| Remote Deploy | Oracle 인스턴스에 SSH 접속 및 배포 스크립트 실행 |

### **🛠️ Jenkins 파이프라인 전체 스크립트**

```bash
pipeline {
    agent any

    environment {
        IMAGE_NAME = "api"
        SERVER = ""
        SSH_USER = "ubuntu"
        SSH_KEY = credentials('server_key') 
        GIT_CREDENTIALS_ID = 'jenkins_github_key'
        IMAGE_TAR = "api.tar"
        ENV_PATH = '/var/lib/jenkins/.env'
    }

    stages {
        stage('Git Clone') {
            steps {
                checkout([$class: 'GitSCM',
                    branches: [[name: 'develop']],
                    userRemoteConfigs: [[
                        url: 'git@github.com:team/server.git',
                        credentialsId: "${GIT_CREDENTIALS_ID}"
                    ]]
                ])
            }
        }

        stage('Jar Build') {
            steps {
                sh 'chmod +x ./gradlew'
                sh './gradlew clean :server-api:bootJar'
            }
        }
        
        stage('Docker Build') {
            steps {
                sh 'docker build -f ./Dockerfile -t $IMAGE_NAME ./server-api'
                sh 'docker save -o $IMAGE_TAR $IMAGE_NAME'
            }
        }
        
        stage('Remote Deploy') {
            steps {
                script {
                    def activePort = sh(
                        script: "ssh -i $SSH_KEY $SSH_USER@$SERVER 'docker ps --format \"{{.Names}}\" | grep app_8080 || true'",
                        returnStdout: true
                    ).trim() == "app_8080" ? "8080" : "8081"

                    def nextPort = activePort == "8080" ? "8081" : "8080"
                    def prevPort = activePort
                    def nextContainer = "app_${nextPort}"
                    def prevContainer = "app_${prevPort}"

                    sh "scp -i $SSH_KEY $IMAGE_TAR $SSH_USER@$SERVER:/home/$SSH_USER/"
                    
                    // 서버에서 새 버전의 컨테이너 실행
                    sh """
                    ssh -i $SSH_KEY $SSH_USER@$SERVER '
                        docker load -i $IMAGE_TAR &&
                        docker stop $nextContainer || true &&
                        docker rm $nextContainer || true &&
                        docker run -d --name $nextContainer \\
                            --env-file /home/$SSH_USER/.env \\
                            -e "SPRING_PROFILES_ACTIVE=prod" \\
                            --network ubuntu_net \\
                            -p ${nextPort}:8080 \\
                            -v /var/log/app:/var/log/app \\
                            $IMAGE_NAME
                    '
                    """
                    
                    // 헬스 체크 후 이전 컨테이너 종료
                    sh """
                    ssh -i $SSH_KEY $SSH_USER@$SERVER '
                        echo "Health checking new container on port ${nextPort}..."
                        # 60초 동안 5초 간격으로 헬스 체크를 시도합니다.
                        for i in {1..12}; do
                            if curl -f http://localhost:${nextPort}/actuator/health; then
                                echo "Health check successful!"
                                docker stop ${prevContainer} || true &&
                                docker rm ${prevContainer} || true
                                exit 0
                            fi
                            echo "Health check failed. Retrying in 5 seconds..."
                            sleep 5
                        done
                        echo "Health check failed after 60 seconds. Rolling back..."
                        docker stop ${nextContainer} || true &&
                        docker rm ${nextContainer} || true
                        exit 1
                    '
                    """
                    
                    // Nginx 설정 심볼릭 링크 변경 및 reload
                    sh """
                    ssh -i $SSH_KEY $SSH_USER@$SERVER '
                        echo "Switching Nginx to port ${nextPort}..."
                        sudo ln -sf /etc/nginx/sites-available/app_${nextPort}.conf /etc/nginx/sites-enabled/app.conf &&
                        sudo nginx -t && sudo systemctl reload nginx
                    '
                    """
                }
            }
        }
    }
}
```

### **🚀 배포 전략**

- 단일 서버에서도 **다운 타임을 최소화**하기 위해, 8080과 8081 포트를 번갈아 사용하며
Nginx 설정을 심볼릭 링크로 전환하는 방식으로 중단 없는 배포 환경을 구성

### **1. 새로운 컨테이너 빌드 및 실행**

- 현재 구동 중인 포트를 확인 (예: 8080)
- 반대 포트(예: 8081)에 새로운 컨테이너 실행
- Docker Network를 통해 Redis 등 다른 컨테이너와의 연결 유지

### **2. Nginx 심볼릭 링크 전환**

```bash
# 기존 Nginx 설정 링크 제거
rm /etc/nginx/sites-enabled/app_8080.conf
# 새 포트를 바라보는 설정 링크 생성
ln -s /etc/nginx/sites-available/app_8081.conf /etc/nginx/sites-enabled/app_8081.conf
# Nginx 설정 적용
nginx -s reload
```

- app_8080.conf 및 app_8081.conf
    
    ```bash
    server {
        listen 80;
        server_name _;
    
        # /healthcheck 요청 시 /healthcheck/로 리다이렉트
        location = /healthcheck {
            return 301 /healthcheck/;
        }
    		# healthcheck 요청 시 ok 반환
        location /healthcheck/ {
            alias /var/www/healthcheck/;
            index index.html;
        }
    
        # prometheus 메트릭 데이터 수집 8090 포트로
        location /actuator/prometheus {
    				proxy_pass http://localhost:8090/actuator/prometheus;
    				proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
        }
    
        # 나머지 모든 요청은 8080 or 8081 포트 백엔드로 프록시
        location / {
            proxy_pass http://localhost:8080/;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
        }
    
    }
    ```
    

### **3. 헬스 체크 후 기존 컨테이너 종료**

- /actuator/health 로 정상 동작 확인
- 새 컨테이너가 정상 상태일 경우, 이전 컨테이너 종료 및 정리