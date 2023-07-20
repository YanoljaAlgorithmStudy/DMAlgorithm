package lv1;

public class KakaoInternship2020PressKeypad {
    public static void main(String[] args) {
        int[] nubmers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        String hand = "left";
        System.out.println(solution(nubmers, hand));
    }

    public static String solution(int[] numbers, String hand) {
        int[][] keyPads = {{1, 4, 7, 99}, {2, 5, 8, 0}, {3, 6, 9, 99}};

        int[] positionOfLeft = {0, 3};
        int[] positionOfRight = {2, 3};
        int[] positionOfNumber = new int[2];
        boolean isRightHand = hand.charAt(0) == 'r';
        StringBuilder sb = new StringBuilder();

        for (int number : numbers) {
            if (number == 7 || number == 4 || number == 1) {
                sb.append("L");
                positionOfLeft = getIndexOfNumber(keyPads, number);
            } else if (number == 3 || number == 6 || number == 9) {
                sb.append("R");
                positionOfRight = getIndexOfNumber(keyPads, number);
            } else {
                positionOfNumber = getIndexOfNumber(keyPads, number);
                int diffLeft = Math.abs(positionOfNumber[0] - positionOfLeft[0]) + Math.abs(positionOfNumber[1] - positionOfLeft[1]);
                int diffRight = Math.abs(positionOfNumber[0] - positionOfRight[0]) + Math.abs(positionOfNumber[1] - positionOfRight[1]);
                if (diffLeft > diffRight) {
                    sb.append("R");
                    positionOfRight[0] = positionOfNumber[0];
                    positionOfRight[1] = positionOfNumber[1];
                } else if (diffLeft < diffRight) {
                    sb.append("L");
                    positionOfLeft[0] = positionOfNumber[0];
                    positionOfLeft[1] = positionOfNumber[1];
                } else {
                    sb.append(Character.toString(hand.charAt(0)).toUpperCase());
                    if (isRightHand) {
                        positionOfRight[0] = positionOfNumber[0];
                        positionOfRight[1] = positionOfNumber[1];
                    } else {
                        positionOfLeft[0] = positionOfNumber[0];
                        positionOfLeft[1] = positionOfNumber[1];
                    }
                }
            }
        }
        return sb.toString();
    }

    private static int[] getIndexOfNumber(int[][] keyPads, int number) {
        int[] indexOfNumber = new int[2];
        for (int i = 0; i < keyPads.length; i++) {
            for (int j = 0; j < keyPads[i].length; j++) {
                if (keyPads[i][j] == number) {
                    indexOfNumber[0] = i;
                    indexOfNumber[1] = j;
                    return indexOfNumber;
                }
            }
        }
        return new int[] {0, 0};
    }
}


