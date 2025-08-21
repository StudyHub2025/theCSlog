import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);

            Deque<String> stack = new ArrayDeque<>();
            while (st.hasMoreTokens()) stack.push(st.nextToken());

            out.append("Case #").append(tc).append(": ");
            while (!stack.isEmpty()) {
                out.append(stack.pop());
                if (!stack.isEmpty()) out.append(' ');
            }
            out.append('\n');
        }
        System.out.print(out);
    }
}
