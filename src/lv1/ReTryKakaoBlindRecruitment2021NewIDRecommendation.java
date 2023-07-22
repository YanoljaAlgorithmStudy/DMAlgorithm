package lv1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReTryKakaoBlindRecruitment2021NewIDRecommendation {

    public static void main(String[] args) {
        System.out.println(solution("."));
    }

    public static String solution(String new_id) {
        String s = new KakaoId(new_id)
                .convertToLowerCase()
                .filter()
                .toOneDot()
                .deleteEndOrStartDot()
                .addAToBlank()
                .limitFifteenWords()
                .noTwoWordsLess()
                .getS();

        return s;

    }
    private static class KakaoId {
        private String s;

        private KakaoId(String s)  {
            this.s = s;
        }

        private KakaoId convertToLowerCase() {
            s = s.toLowerCase();
            return this;
        }

        private KakaoId filter() {
            s = s.replaceAll("[^a-z0-9._-]", "");
            return this;
        }

        private KakaoId toOneDot() {
            s = s.replaceAll("[.]{2,}", ".");
            return this;
        }

        private KakaoId deleteEndOrStartDot() {
            s = s.replaceAll("^[.]|[.]$", "");
            return this;
        }

        private KakaoId addAToBlank() {
            s = s.isEmpty() ? "a" : s;
            return this;
        }

        private KakaoId limitFifteenWords() {
            s = s.length() >= 16 ? s.substring(0, 15) : s;
            return deleteEndOrStartDot();
        }

        private KakaoId noTwoWordsLess() {
            StringBuilder sb = new StringBuilder(s);
            while (sb.length() <= 2) {
                sb.append(s.charAt(s.length() - 1));
            }
            s = sb.toString();
            return this;
        }

        private String getS() {
            return s;
        }
    }
}
