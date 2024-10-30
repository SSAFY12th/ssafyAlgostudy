import java.util.*;

public class Main {

    static class Word {
        char c;
        Word before, next;
        public Word(Word before, char c, Word next) {
            this.before = before;
            this.c = c;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        String bomb = sc.next();

        Word tail = null;
        Word head = tail;

        // 폭탄 문자열의 길이
        int bombLength = bomb.length();
        int cnt = 0;

        // 제공 문자열 하나씩 탐색
        for (char c : str.toCharArray()) {
            // head 또는 tail에 추가
            if(head == null) {  // head가 null이면 검증할 문자열이 비어있다는 뜻
                head = new Word(null, c, null);
                tail = head;
            } else {
                tail.next = new Word(tail, c, null);
                tail = tail.next;
            }
            cnt++;

            // 스택의 사이즈가 폭탄문자열 크기를 넘으면 확인
            if (cnt >= bombLength) {
                // 폭탄임을 확인하는 체크 변수
                boolean isBomb = true;
                Word pivot = tail;

                // 한글자 한글자 비교한다
                for (int j = 0; j < bombLength; j++) {
                    if(pivot.c != bomb.charAt(bombLength -1 -j)) {
                        isBomb = false;
                        break;
                    }
                    pivot = pivot.before;
                }

                // 폭탄이면
                if (isBomb) {
                    // boom만큼 제거하고 tail의 위치를 변경.
                    if(pivot != null)
                        pivot.next = null;  // null로 바꾸면서 boom만큼 제거한 것.
                    else
                        head = pivot;   // 만약 pivot이 null이면 가장 앞의 문자까지 온것이라 head를 pivot으로 변경
                    tail = pivot;
                    cnt -= bombLength;
                }
            }
        }

        // 결과 문자열 생성
        StringBuilder result = new StringBuilder();
        while(head != null) {
            result.append(head.c);
            head = head.next;
        }

        // 결과 출력
        System.out.println(result.length() == 0 ? "FRULA" : result.toString());
    }
}
