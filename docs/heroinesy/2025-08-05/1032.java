import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baek1032 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] cmd = br.readLine().split("");

        for (int i = 1; i < N; i++) {
            String[] name = br.readLine().split("");
            for (int j = 0; j < cmd.length; j++) {
                if (!cmd[j].equals(name[j])) {
                    cmd[j] = "?";
                }
            }
        }
        for (String s : cmd) {
            System.out.print(s);
        }
        br.close();
    }
}