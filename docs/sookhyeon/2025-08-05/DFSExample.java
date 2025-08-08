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
// DFS 탐색 순서: 1 2 3 5 4