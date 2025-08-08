import java.util.*;

public class SieveOfEratosthenes {
    public static void main(String[] args) {
        int n = 30; // 30 이하의 소수 구하기
        boolean[] isPrime = new boolean[n + 1];

        // 2 이상 모든 수를 소수로 가정
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        // 에라토스테네스의 체
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // 소수 출력
        System.out.println("소수 목록:");
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                System.out.print(i + " ");
            }
        }
    }
}
