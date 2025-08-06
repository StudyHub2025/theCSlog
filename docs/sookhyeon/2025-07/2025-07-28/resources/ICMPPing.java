package _0728;

public class ICMPPing {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 4; i++) {
            System.out.println("ICMP Echo Request → 8.8.8.8");
            Thread.sleep(1000); // 1초 대기
            System.out.println("ICMP Echo Reply ← 8.8.8.8\n");
        }
    }
    
}

/*
실행 결과 (1초 간격 출력)
ICMP Echo Request → 8.8.8.8
ICMP Echo Reply ← 8.8.8.8

ICMP Echo Request → 8.8.8.8
ICMP Echo Reply ← 8.8.8.8

ICMP Echo Request → 8.8.8.8
ICMP Echo Reply ← 8.8.8.8

ICMP Echo Request → 8.8.8.8
ICMP Echo Reply ← 8.8.8.8

 */