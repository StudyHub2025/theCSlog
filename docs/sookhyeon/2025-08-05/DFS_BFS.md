# DFS vs BFS 알고리즘

## 그래프(Graph)란?
- 정점(Vertex)과 간선(Edge)으로 이루어진 자료구조
- 다양한 관계를 표현할 수 있음 (예: 친구 관계, 도로망 등)

### 그래프 종류
| 구분     | 종류                  |
|----------|-----------------------|
| 방향성   | 방향 그래프, 무방향 그래프 |
| 순환     | 순환 그래프, 비순환 그래프 |
| 연결     | 연결 그래프, 비연결 그래프 |

---

## DFS (Depth-First Search, 깊이 우선 탐색)

### 개념
- 가능한 깊게 들어가면서 방문
- 더 이상 갈 곳이 없으면 되돌아와서 다른 길 탐색
- 재귀 또는 스택으로 구현

### 동작 방식
1. 현재 노드를 방문
2. 인접한 노드 중 방문하지 않은 노드로 이동
3. 더 이상 갈 수 없으면 되돌아와서 다른 방향 탐색

### 특징
- 특정 경로 탐색에 유리
- 미로 탐색에 적합
- 스택 또는 재귀 구조

---

## BFS (Breadth-First Search, 너비 우선 탐색)

### 개념
- 현재 정점과 인접한 정점들을 먼저 모두 방문
- 다음 단계로 넘어가면서 탐색
- 큐를 사용하여 구현

### 동작 방식
1. 시작 노드를 큐에 삽입
2. 큐에서 노드를 꺼내며 인접 노드들을 큐에 삽입
3. 방문한 노드는 표시하고, 큐가 빌 때까지 반복

### 특징
- 최단 거리 탐색에 유리
- 레벨 단위 탐색
- 큐 구조 기반

---

## DFS vs BFS 비교표

| 항목       | DFS                   | BFS                     |
|------------|------------------------|--------------------------|
| 자료구조    | 스택(재귀)             | 큐                       |
| 방문 순서   | 깊이 우선              | 가까운 노드부터          |
| 구현 난이도 | 쉬움 (재귀)            | 큐 사용으로 조금 복잡함  |
| 특징        | 경로 찾기 유리          | 최단 거리 탐색에 유리     |
| 메모리      | 적게 사용함             | 많이 사용할 수 있음       |

---

## 자바 코드 예시

### DFS (재귀 방식)
```java
static boolean[] visited;
static ArrayList<ArrayList<Integer>> graph;

public static void dfs(int node) {
    visited[node] = true;
    System.out.print(node + " ");
    for (int next : graph.get(node)) {
        if (!visited[next]) dfs(next);
    }
}
```

### BFS (큐 사용)
```java
public static void bfs(int start) {
    Queue<Integer> queue = new LinkedList<>();
    visited[start] = true;
    queue.offer(start);

    while (!queue.isEmpty()) {
        int node = queue.poll();
        System.out.print(node + " ");
        for (int next : graph.get(node)) {
            if (!visited[next]) {
                visited[next] = true;
                queue.offer(next);
            }
        }
    }
}
```