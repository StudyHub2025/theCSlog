import java.util.*;

public class FCFS {
    public static void main(String[] args) {
        List<Process> processes = Arrays.asList(
                new Process("P1", 0, 5, 0),
                new Process("P2", 2, 3, 0),
                new Process("P3", 4, 1, 0)
        );

        // 도착 순서로 정렬
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        int time = 0;
        for (Process p : processes) {
            if (time < p.arrivalTime) time = p.arrivalTime;
            System.out.println("Time " + time + " : " + p.id + " 실행 시작");
            time += p.burstTime;
            System.out.println("Time " + time + " : " + p.id + " 실행 종료");
        }
    }
}

/*
Time 0 : P1 실행 시작
Time 5 : P1 실행 종료
Time 5 : P2 실행 시작
Time 8 : P2 실행 종료
Time 8 : P3 실행 시작
Time 9 : P3 실행 종료
 */