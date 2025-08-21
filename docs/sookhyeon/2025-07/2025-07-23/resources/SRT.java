import java.util.*;

public class SRT {
    public static void main(String[] args) {
        List<Process> processes = Arrays.asList(
                new Process("P1", 0, 8, 0),
                new Process("P2", 1, 4, 0),
                new Process("P3", 2, 2, 0),
                new Process("P4", 3, 1, 0)
        );

        int time = 0;

        while (processes.stream().anyMatch(p -> p.remainingTime > 0)) {
            Process current = null;
            int minRemaining = Integer.MAX_VALUE;

            // 가장 짧은 remainingTime을 가진 프로세스를 직접 찾음
            for (Process p : processes) {
                if (p.arrivalTime <= time && p.remainingTime > 0 && p.remainingTime < minRemaining) {
                    current = p;
                    minRemaining = p.remainingTime;
                }
            }

            if (current != null) {
                System.out.println("Time " + time + ": " + current.id + " 실행");
                current.remainingTime--;
            } else {
                System.out.println("Time " + time + ": Idle");
            }

            time++;
        }
    }
}

/*
Time 0: P1 실행
Time 1: P2 실행
Time 2: P3 실행
Time 3: P3 실행
Time 4: P4 실행
Time 5: P2 실행
Time 6: P2 실행
Time 7: P2 실행
Time 8: P1 실행
Time 9: P1 실행
Time 10: P1 실행
Time 11: P1 실행
Time 12: P1 실행
Time 13: P1 실행
Time 14: P1 실행
 */