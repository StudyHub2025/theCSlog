package _0724;

import java.util.*;

public class OptimalPageReplacement {
    public static void main(String[] args) {
        int[] pages = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        int capacity = 3;
        Set<Integer> memory = new HashSet<>();
        int pageFaults = 0;

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];

            if (!memory.contains(page)) { // 페이지 폴트 발생
                pageFaults++;
                if (memory.size() == capacity) {
                    int farthest = -1;
                    int indexToRemove = -1;

                    for (int memPage : memory) {
                        int nextUse = Integer.MAX_VALUE;
                        for (int j = i + 1; j < pages.length; j++) {
                            if (pages[j] == memPage) {
                                nextUse = j;
                                break;
                            }
                        }
                        if (nextUse > farthest) {
                            farthest = nextUse;
                            indexToRemove = memPage;
                        }
                    }

                    memory.remove(indexToRemove);
                    System.out.println("페이지 제거 (OPT): " + indexToRemove);
                }
                memory.add(page);
                System.out.println("페이지 추가: " + page + " (페이지 폴트 발생)");
            } else {
                System.out.println("페이지 히트: " + page);
            }

            System.out.println("현재 메모리 상태: " + memory);
            System.out.println("----------------------------");
        }

        System.out.println("Optimal 페이지 폴트: " + pageFaults);
    }
}
