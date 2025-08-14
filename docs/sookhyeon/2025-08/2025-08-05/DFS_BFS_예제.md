# DFS & BFS Java 예제 코드

## 사용 그래프 구조

```
     1
   / | \
  2  3  4
     |
     5
```

- 간선: 1-2, 1-3, 1-4, 3-5 (무방향)

---

## DFS 예제 (Depth-First Search)

```java
import java.util.*;

public class DFSExample {
    static boolean[] visited = new boolean[6];  // 노드 1~5까지
    static ArrayList<Integer>[] graph = new ArrayList[6];

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[1].add(2); graph[2].add(1);
        graph[1].add(3); graph[3].add(1);
        graph[1].add(4); graph[4].add(1);
        graph[3].add(5); graph[5].add(3);

        System.out.print("DFS 탐색 순서: ");
        dfs(1);  // 1번 노드부터 시작
    }

    public static void dfs(int node) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}
```

### DFS 실행 흐름

```
dfs(1)
1 방문 → 2 방문 → 백 → 3 방문 → 5 방문 → 백 → 4 방문 → 종료

출력: 1 2 3 5 4
```

---

## BFS 예제 (Breadth-First Search)

```java
import java.util.*;

public class BFSExample {
    static boolean[] visited = new boolean[6];
    static ArrayList<Integer>[] graph = new ArrayList[6];

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[1].add(2); graph[2].add(1);
        graph[1].add(3); graph[3].add(1);
        graph[1].add(4); graph[4].add(1);
        graph[3].add(5); graph[5].add(3);

        System.out.print("BFS 탐색 순서: ");
        bfs(1);  // 1번 노드부터 시작
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int next : graph[node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }
}
```

### BFS 실행 흐름

```
bfs(1)
1 → 큐에 [2,3,4]
2 → 3 → 4 → 5 → 종료

출력: 1 2 3 4 5
```

---

## 비교 요약

| 항목        | DFS                         | BFS                         |
|-------------|------------------------------|------------------------------|
| 순서 예시    | 1 → 2 → 3 → 5 → 4           | 1 → 2 → 3 → 4 → 5           |
| 구현 방식    | 재귀 (또는 스택)             | 큐(Queue) 기반              |
| 특징        | 깊이 우선, 백트래킹 탐색에 유리 | 너비 우선, 최단 경로 탐색에 유리 |

---

## 마무리

- DFS는 재귀 기반으로 깊이 있게 탐색하고,
- BFS는 큐를 사용해서 레벨 단위로 넓게 퍼지는 방식입니다.
