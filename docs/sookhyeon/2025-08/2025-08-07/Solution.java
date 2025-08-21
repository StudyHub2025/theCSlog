import java.io.*;
import java.util.*;

public class Solution {
    static List<List<Node>> graph = new ArrayList<>();
    static boolean[] visited;
    static long maxDistance = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            long C = Long.parseLong(st.nextToken());

            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        visited = new boolean[N + 1];
        dfs(1, 0L);

        System.out.println(maxDistance);
    }

    static void dfs(int current, long dist) {
        visited[current] = true;
        maxDistance = Math.max(maxDistance, dist);

        for (Node next : graph.get(current)) {
            if (!visited[next.to]) {
                dfs(next.to, dist + next.weight);
            }
        }
    }

    static class Node {
        int to;
        long weight;

        public Node(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}