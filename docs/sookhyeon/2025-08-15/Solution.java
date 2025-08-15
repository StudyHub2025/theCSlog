import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int INF = 1_000_000_000;
        int[] dp = new int[N + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i <= N; i++) {
            if (dp[i] == INF) continue;
            if (i + 1 <= N) dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
            int j = i + i / 2;
            if (j <= N) dp[j] = Math.min(dp[j], dp[i] + 1);
        }

        System.out.println(dp[N] <= K ? "minigimbob" : "water");
    }
}
