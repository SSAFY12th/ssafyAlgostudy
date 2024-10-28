import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String bomb = br.readLine();
        Stack<Character> stack = new Stack<>();

        // 폭탄 문자열의 길이
        int bombLength = bomb.length();

        // 제공 문자열 하나씩 탐색
        for (char c : str.toCharArray()) {
            // 스택에 푸쉬
            stack.push(c);

            // 스택의 사이즈가 폭탄문자열 크기를 넘으면 확인
            if (stack.size() >= bombLength) {
                // 폭탄임을 확인하는 체크 변수
                boolean isBomb = true;

                // 한글자 한글자 비교한다
                for (int j = 0; j < bombLength; j++) {
                    if (stack.get(stack.size() - 1 - j) != bomb.charAt(bombLength - 1 - j)) {
                        // 하나라도 다르면 false 처리
                        isBomb = false;
                        break;
                    }
                }

                // 폭탄이면
                if (isBomb) {
                    // 지금 까지 쌓인 스택에서 폭탄 사이즈 만큼 빼면 됨
                    for (int j = 0; j < bombLength; j++) {
                        stack.pop();
                    }
                }
            }
        }

        // 결과 문자열 생성
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        // 결과 출력
        System.out.println(result.length() == 0 ? "FRULA" : result.toString());
    }
}

