import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static String[][] board;
    static int maxVal = Integer.MIN_VALUE;
    static int minVal = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        board = new String[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().trim().split("\\s+");
        }

        int start = Integer.parseInt(board[0][0]);
        dfs(0, 0, start, ' ');

        System.out.println(maxVal + " " + minVal);
    }

    static void dfs(int x, int y, int value, char op) {
        if (x == N - 1 && y == N - 1) {
            maxVal = Math.max(maxVal, value);
            minVal = Math.min(minVal, value);
            return;
        }

        int[] dx = {0, 1};
        int[] dy = {1, 0};

        for (int d = 0; d < 2; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

            String cell = board[nx][ny];

            if (isOperator(cell)) {
                dfs(nx, ny, value, cell.charAt(0));
            } else {
                int num = Integer.parseInt(cell);
                int nextVal = (op == ' ') ? num : apply(value, op, num);

                dfs(nx, ny, nextVal, op);
            }
        }
    }

    static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*");
    }

    static int apply(int a, char op, int b) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        return a * b; // '*'
    }
}
