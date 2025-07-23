import java.util.*;

public class SJF {
    public static void main(String[] args) {
        List<Process> input = new ArrayList<>(Arrays.asList(
                new Process("P1", 0, 6, 0),
                new Process("P2", 1, 8, 0),
                new Process("P3", 2, 7, 0),
                new Process("P4", 3, 3, 0)
        ));

        List<Process> queue = new ArrayList<>();
        int time = 0;
        while (!input.isEmpty() || !queue.isEmpty()) {
            for (Iterator<Process> it = input.iterator(); it.hasNext();) {
                Process p = it.next();
                if (p.arrivalTime <= time) {
                    queue.add(p);
                    it.remove();
                }
            }

            if (queue.isEmpty()) {
                time++;
                continue;
            }

            queue.sort(Comparator.comparingInt(p -> p.burstTime));
            Process current = queue.remove(0);
            System.out.println("Time " + time + " : " + current.id + " 실행 시작");
            time += current.burstTime;
            System.out.println("Time " + time + " : " + current.id + " 실행 종료");
        }
    }
}

/*
Time 0 : P1 실행 시작
Time 6 : P1 실행 종료
Time 6 : P4 실행 시작
Time 9 : P4 실행 종료
Time 9 : P3 실행 시작
Time 16 : P3 실행 종료
Time 16 : P2 실행 시작
Time 24 : P2 실행 종료
 */