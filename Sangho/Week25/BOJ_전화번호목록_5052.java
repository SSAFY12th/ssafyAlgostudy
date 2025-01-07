import java.io.*;
import java.util.*;

public class BOJ_전화번호목록_5052 {

    static int T, n;
    static String[] phoneNumbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine());
            phoneNumbers = new String[n];

            for (int i = 0; i < n; i++) {
                phoneNumbers[i] = br.readLine();
            }

            // 전화번호 사전순 정렬하기
            Arrays.sort(phoneNumbers);

            // 위반 여부 변수
            boolean isConsistent = true;

            // 인접한 번호끼리만 비교하기
            for (int i = 0; i < n - 1; i++) {
                if (phoneNumbers[i + 1].startsWith(phoneNumbers[i])) {
                    isConsistent = false;
                    break;
                }
            }

            sb.append(isConsistent ? "YES\n" : "NO\n");
        }

        System.out.print(sb);
    }
}
