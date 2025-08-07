import java.util.*;

public class FIFOQueue {
    public static void main(String[] args) {
        Queue<String> fifo = new LinkedList<>();
        fifo.add("A");
        fifo.add("B");
        fifo.add("C");

        while (!fifo.isEmpty()) {
            System.out.println("처리 : " + fifo.poll()); // A → B → C
        }
    }
}

/*
처리 : A
처리 : B
처리 : C
 */