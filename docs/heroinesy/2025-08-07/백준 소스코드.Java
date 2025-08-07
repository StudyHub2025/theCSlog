import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baek10820 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            int lower = 0;
            int upper = 0;
            int number = 0;
            int space = 0;

            char[] arr = line.toCharArray();
            for (char c : arr) {
                if (Character.isLowerCase(c)) {
                    lower++;
                }
                if (Character.isUpperCase(c)) {
                    upper++;
                }
                if (Character.isDigit(c)) {
                    number++;
                }
                if (Character.isWhitespace(c)) {
                    space++;
                }
            }
            System.out.println(lower + " " + upper + " " + number + " " + space);
        }
        br.close();
    }
}