import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static StringTokenizer st;
    static int T;
    static int N, K;
    static char[] str;
    static Set<String> passwordSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            str = new char[N];
            passwordSet = new HashSet<>();

            String input = br.readLine();
            for (int i = 0; i < N; i++) {
                str[i] = input.charAt(i);
            }

            int sectionSize = N / 4;

            for (int i = 0; i < sectionSize; i++) {
                extractPasswords(sectionSize);
                rotateArray();
            }

            List<String> passwordList = new ArrayList<>(passwordSet);
            passwordList.sort((a, b) -> Long.compare(Long.parseLong(b, 16), Long.parseLong(a, 16)));

            System.out.println("#" + tc + " " + Integer.parseInt(passwordList.get(K - 1),16));
        }
    }

    // 각 회전 상태에서 비밀번호 추출
    private static void extractPasswords(int sectionSize) {
        for (int i = 0; i < 4; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < sectionSize; j++) {
                sb.append(str[i * sectionSize + j]);
            }
            passwordSet.add(sb.toString());
        }
    }

    // 배열을 시계방향으로 회전
    private static void rotateArray() {
        char last = str[N - 1];
        for (int i = N - 1; i > 0; i--) {
            str[i] = str[i - 1];
        }
        str[0] = last;
    }
}
