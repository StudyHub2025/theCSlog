import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        String[] arr = new String[N];
        Set<String> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().trim();
            set.add(arr[i]);
        }

        for (String s : arr) {
            String rev = new StringBuilder(s).reverse().toString();
            if (set.contains(rev)) {
                int L = s.length();
                System.out.println(L + " " + s.charAt(L / 2));
                return;
            }
        }
    }
}
