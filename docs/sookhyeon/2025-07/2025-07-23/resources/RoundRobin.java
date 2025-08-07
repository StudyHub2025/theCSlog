import java.util.*;

public class RoundRobin {
    public static void main(String[] args) {
        Queue<Process> queue = new LinkedList<>();
        queue.add(new Process("P1", 0, 5, 0));
        queue.add(new Process("P2", 0, 3, 0));
        queue.add(new Process("P3", 0, 6, 0));

        int quantum = 2;
        int time = 0;

        while (!queue.isEmpty()) {
            Process p = queue.poll();
            int run = Math.min(quantum, p.remainingTime);
            System.out.println("Time " + time + ": " + p.id + " 실행 (" + run + ")");
            p.remainingTime -= run;
            time += run;

            if (p.remainingTime > 0)
                queue.add(p); // 다시 대기열로
            else
                System.out.println("Time " + time + ": " + p.id + " 완료");
        }
    }
}

/*
Time 0: P1 실행 (2)
Time 2: P2 실행 (2)
Time 4: P3 실행 (2)
Time 6: P1 실행 (2)
Time 8: P2 실행 (1)
Time 9: P2 완료
Time 9: P3 실행 (2)
Time 11: P1 실행 (1)
Time 12: P1 완료
Time 12: P3 실행 (2)
Time 14: P3 완료
 */