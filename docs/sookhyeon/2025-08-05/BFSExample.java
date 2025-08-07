package _0806;

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
// BFS 탐색 순서: 1 2 3 4 5