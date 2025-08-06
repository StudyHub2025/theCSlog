import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int maxSafeArea = 0;

        for (int h = 0; h <= maxHeight; h++) {
            visited = new boolean[N][N];
            int safeAreaCount = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > h) {
                        dfs(i, j, h);
                        safeAreaCount++;
                    }
                }
            }

            maxSafeArea = Math.max(maxSafeArea, safeAreaCount);
        }

        System.out.println(maxSafeArea);
    }

    private static void dfs(int x, int y, int height) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                if (!visited[nx][ny] && map[nx][ny] > height) {
                    dfs(nx, ny, height);
                }
            }
        }
    }
}
