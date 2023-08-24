package lv1;

import java.util.*;

public class KakaoBlindRecruitment2022ReceivingReportResults {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"}, 2)));
    }

    private static Map<String, Integer> countsMails = new HashMap<>();
    private static Map<String, HashSet<String>> countsList = new HashMap<>();

    public static int[] solution(String[] id_list, String[] report, int k) {

        // 결과 메일 Map 초기화
        for (String id : id_list) {
            countsMails.put(id, 0);
        }

        // 어떤 사람이 누구에게 총 몇 번 신고 당했는지 map에 저장
        for (String c : report) {
            String receiver = distinguishCase(c)[1];
            String reporter = distinguishCase(c)[0];

            if (!countsList.containsKey(receiver)) {
                HashSet<String> reportersSet = new HashSet<>();
                countsList.put(receiver, reportersSet);
            }

            HashSet<String> set = countsList.get(receiver);
            set.add(reporter);
        }

        // 총 결과 메일 갯수 카운팅
        for (int i = 0; i < id_list.length; i++) {
            if (countsList.containsKey(id_list[i]) && isMoreStandard(id_list[i], k)) {
                // 대상을 신고한 id들에게 결과 메일 +1
                for (String reporter : countsList.get(id_list[i])) {
                    countsMails.put(reporter, countsMails.getOrDefault(reporter, 0) + 1);
                }
            }
        }

        // Map을 순서대로 배열로 치환
        int[] answer = new int[countsMails.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = countsMails.get(id_list[i]);
        }

        return answer;
    }

    private static String[] distinguishCase(String c) {
        return c.split(" ");
    }

    private static boolean isMoreStandard(String id, int k) {
        return countsList.get(id).size() >= k;
    }
}
