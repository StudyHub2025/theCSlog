package _0724;

import java.util.*;

public class FIFOPageReplacement {
    public static void main(String[] args) {
        int[] pages = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        int capacity = 3;

        Queue<Integer> memory = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        int pageFaults = 0;

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];

            if (!set.contains(page)) { // 페이지 폴트 발생
                pageFaults++;
                if (set.size() == capacity) {
                    int removed = memory.poll(); // 가장 먼저 들어온 페이지 제거
                    set.remove(removed);
                    System.out.println("페이지 제거 (FIFO): " + removed);
                }
                memory.add(page);
                set.add(page);
                System.out.println("페이지 추가: " + page + " (페이지 폴트 발생)");
            } else {
                System.out.println("페이지 히트: " + page);
            }

            System.out.println("현재 메모리 상태: " + memory);
            System.out.println("----------------------------");
        }

        System.out.println("FIFO 페이지 폴트: " + pageFaults);
    }
}
