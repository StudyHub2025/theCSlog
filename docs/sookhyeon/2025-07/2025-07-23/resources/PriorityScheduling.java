import java.util.*;

public class PriorityScheduling {
    public static void main(String[] args) {
        List<Process> processes = Arrays.asList(
                new Process("P1", 0, 4, 3),
                new Process("P2", 0, 3, 1),
                new Process("P3", 0, 5, 2)
        );

        processes.sort(Comparator.comparingInt(p -> p.priority)); // 낮은 값 우선

        int time = 0;
        for (Process p : processes) {
            System.out.println("Time " + time + ": " + p.id + " (우선순위 " + p.priority + ") 실행 시작");
            time += p.burstTime;
            System.out.println("Time " + time + ": " + p.id + " 실행 종료");
        }
    }
}

/*
Time 0: P2 (우선순위 1) 실행 시작
Time 3: P2 실행 종료
Time 3: P3 (우선순위 2) 실행 시작
Time 8: P3 실행 종료
Time 8: P1 (우선순위 3) 실행 시작
Time 12: P1 실행 종료
 */