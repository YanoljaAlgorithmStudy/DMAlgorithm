package lv1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KakaoBlindRecruitment2021NewIDRecommendation {
    public static void main(String[] args) {
        System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
    }

    public static String solution(String new_id) {

        int i = 0;
        String answer = new_id;

        while (i++ != 2) {

            // 소문자로 치환
            String lowerCaseNewId = answer.toLowerCase();
            // 문자열 배열로 분할
            String[] words = lowerCaseNewId.split("");
            // LinkedList로 변환
            LinkedList<String> linkedList = new LinkedList<>(Arrays.asList(words));

            // 빈 문자열인 경우 a 대입
            LinkedList<String> aLinkedList = exchangeBlanksToAs(linkedList);

            // 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 제외한 나머지 삭제
            LinkedList<String> deletedWrongWords = deleteWrongWords(aLinkedList);

            // 마침표 2번 이상 연속된 부분은 하나의 마침표로 치환
            LinkedList<String> deletedDuplicateCommas = deleteDuplicateComas(deletedWrongWords);

            // LinkedList를 문자열로 변환
            String convertedString = convertLinkedListToString(deletedDuplicateCommas);

            // 시작과 끝에 마침표 있으면 삭제
            String DeletedStartAndEndCommas = deleteStartAndEndCommas(convertedString);

            // 길이 16 이상 자르기
            answer = DeletedStartAndEndCommas.length() >= 16 ? DeletedStartAndEndCommas.substring(0, 15) : DeletedStartAndEndCommas;

            // 길이가 2이하인 경우 마지막 글자 붙이기
            if (answer.length() <= 2) {
                answer = extendsLength(DeletedStartAndEndCommas);
            }
        }

        return answer;
    }

    private static String extendsLength(String id) {
        do {
            id += id.charAt(id.length() - 1);
        } while (id.length() != 3);
        return id;
    }

    private static LinkedList<String> deleteWrongWords(LinkedList<String> linkedList) {
        String regexPattern = "^[a-z0-9_.-]$";
        Pattern pattern = Pattern.compile(regexPattern);

        for (int i = 0; i < linkedList.size(); i++) {
            Matcher matcher = pattern.matcher(linkedList.get(i));
            if (!matcher.matches()) {
                linkedList.remove(i);
                i -= 1;
            }
        }
        return linkedList;
    }

    private static LinkedList<String> deleteDuplicateComas(LinkedList<String> linkedList) {
        for (int i = 1; i < linkedList.size(); i++) {
            String word = linkedList.get(i);
            String preWord = linkedList.get(i - 1);
            if (word.equals(".") && preWord.equals(".")) {
                linkedList.remove(i);
                i -= 1;
            }
        }

        return linkedList;
    }

    private static String convertLinkedListToString(LinkedList<String> linkedList) {
        StringBuffer sb = new StringBuffer();
        for (String word : linkedList) {
            sb.append(word);
        }
        return sb.toString();
    }

    private static LinkedList<String> exchangeBlanksToAs(LinkedList<String> linkedList) {
        for (int i = 0; i < linkedList.size(); i++) {
            if (linkedList.get(i).equals(" ")) {
                linkedList.remove(i);
                linkedList.add(i, "a");
            }
        }
        return linkedList;
    }

    private static String deleteStartAndEndCommas(String id) {
        String answer = id;
        // 시작, 끝 마침표 삭제
        if (answer.startsWith(".")) {
            answer = answer.substring(1);
        }
        if (answer.endsWith(".")) {
            answer = (answer.substring(0, id.length() - 1));
        }
        if (answer.length() == 0) {
            answer = " ";
        }
        return answer;
    }
}
