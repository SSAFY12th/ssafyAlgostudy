import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        // 파일 정렬을 위해 List로 선언
        List<String> fileList = Arrays.asList(files);

        fileList.sort((file1, file2) -> {
            // HEAD NUMBER 추출하기 !
            String[] parts1 = splitFileName(file1);
            String[] parts2 = splitFileName(file2);

            // HEAD 비교
            int headCompare = parts1[0].compareToIgnoreCase(parts2[0]);
            if (headCompare != 0) {
                return headCompare;
            }

            // NUMBER 비교
            int number1 = Integer.parseInt(parts1[1]);
            int number2 = Integer.parseInt(parts2[1]);
            if (number1 != number2) {
                return Integer.compare(number1, number2);
            }

            // HEAD와 NUMBER가 같으면 입력 순서 유지
            return 0;
        });

        return fileList.toArray(new String[0]);
    }

    // 파일명을 HEAD, NUMBER, TAIL로 나누는 메서드
    private String[] splitFileName(String file) {
        String head = "";
        String number = "";
        boolean numberStarted = false;

        for (char c : file.toCharArray()) {
            if (Character.isDigit(c)) {
                numberStarted = true;
                number += c;
            } else if (!numberStarted) {
                head += c;
            } else {
                break;
            }
        }

        return new String[] { head, number };
    }
}
