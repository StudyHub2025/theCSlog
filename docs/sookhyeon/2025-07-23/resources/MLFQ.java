import java.util.*;

public class MLFQ {
    public static void main(String[] args) {
        Queue<Process> q0 = new LinkedList<>();
        Queue<Process> q1 = new LinkedList<>();
        Queue<Process> q2 = new LinkedList<>();

        q0.add(new Process("P1", 0, 5, 0));
        q0.add(new Process("P2", 0, 8, 0));
        q0.add(new Process("P3", 0, 6, 0));

        int time = 0;
        int q0Quantum = 2, q1Quantum = 4;

        while (!q0.isEmpty() || !q1.isEmpty() || !q2.isEmpty()) {
            Process p = null;
            int quantum = 0;

            if (!q0.isEmpty()) { p = q0.poll(); quantum = q0Quantum; }
            else if (!q1.isEmpty()) { p = q1.poll(); quantum = q1Quantum; }
            else if (!q2.isEmpty()) { p = q2.poll(); quantum = Integer.MAX_VALUE; }

            int run = Math.min(quantum, p.remainingTime);
            System.out.println("Time " + time + ": " + p.id + " 실행 (" + run + ")");
            p.remainingTime -= run;
            time += run;

            if (p.remainingTime > 0) {
                if (quantum == q0Quantum) q1.add(p);
                else if (quantum == q1Quantum) q2.add(p);
                else q2.add(p);
            } else {
                System.out.println("Time " + time + ": " + p.id + " 완료");
            }
        }
    }
}

/*
Time 0: P1 실행 (2)
Time 2: P2 실행 (2)
Time 4: P3 실행 (2)
Time 6: P1 실행 (3)
Time 9: P1 완료
Time 9: P2 실행 (4)
Time 13: P3 실행 (4)
Time 17: P3 완료
Time 17: P2 실행 (2)
Time 19: P2 완료
 */