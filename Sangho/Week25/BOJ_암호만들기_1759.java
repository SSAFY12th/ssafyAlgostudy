import java.io.*;
import java.util.*;

public class Main {

    static int L, C;
    static char[] chars;
    static List<String> results = new ArrayList<>();

    // 모음 리스트 미리 저장
    static final String vowels = "aeiou";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        chars = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            chars[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(chars); // 문자들을 사전 순으로 정렬
        combination(0, 0, new StringBuilder());

        // 결과 출력하기
        for (String result : results) {
            System.out.println(result);
        }
    }

    // 조합 생성
    static void combination(int start, int depth, StringBuilder sb) {
        // L개 뽑히면
        if (depth == L) {
            // 모음 자음 개수 검증
            if (isValid(sb.toString())) {
                results.add(sb.toString());
            }
            return;
        }

        // C개 중에서
        for (int i = start; i < C; i++) {
            sb.append(chars[i]);
            combination(i + 1, depth + 1, sb);
            sb.deleteCharAt(sb.length() - 1); // 백트래킹
        }
    }

    // 조건 검증: 최소 1개의 모음과 2개의 자음을 포함해야 함
    static boolean isValid(String password) {
        int vowelCount = 0, consonantCount = 0;

        for (char c : password.toCharArray()) {
            if (vowels.indexOf(c) >= 0) {
                vowelCount++;
            } else {
                consonantCount++;
            }
        }

        return vowelCount >= 1 && consonantCount >= 2;
    }
}
