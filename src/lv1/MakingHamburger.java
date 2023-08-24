package lv1;

import java.util.ArrayList;
import java.util.Stack;

public class MakingHamburger {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {1, 1, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1}));
    }

    public static int solution(int[] ingredient) {
        int answer = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int n : ingredient) {
            list.add(n);
            if (list.size() >= 4) {
                if (list.get(list.size() - 4) == 1 && list.get(list.size() - 3) == 2 && list.get(list.size() - 2) == 3 && list.get(list.size() - 1) == 1) {
                    for (int i = 0; i < 4; i++) {
                        list.remove(list.size() - 1);
                    }
                    answer++;
                }
            }
        }

        return answer;
    }
}
