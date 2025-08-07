package _0724;

import java.util.*;

public class LRUPageReplacement {
    public static void main(String[] args) {
        int[] pages = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        int capacity = 3;

        LinkedHashMap<Integer, Integer> memory = new LinkedHashMap<>(capacity, 0.75f, true);
        int pageFaults = 0;

        for (int page : pages) {
            if (memory.containsKey(page)) {
                memory.remove(page);
                memory.put(page, 1);
                System.out.println("페이지 히트: " + page);
            } else {
                pageFaults++;
                if (memory.size() == capacity) {
                    int lruPage = memory.entrySet().iterator().next().getKey();
                    memory.remove(lruPage);
                    System.out.println("페이지 제거 (LRU): " + lruPage);
                }
                memory.put(page, 1);
                System.out.println("페이지 추가: " + page + " (페이지 폴트 발생)");
            }

            System.out.println("현재 메모리 상태: " + memory.keySet());
            System.out.println("----------------------------");
        }

        System.out.println("LRU 페이지 폴트: " + pageFaults);
    }
}
