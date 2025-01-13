import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] soldiers = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            soldiers[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 뒤집기 이래야 증가하는 부분을 찾기 편함
        int[] reversed = new int[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = soldiers[n - 1 - i];
        }

        // DP 배열 초기화
        int[] dp = new int[n];
        int maxLength = 0;

        // LIS 계산하기
        for (int i = 0; i < n; i++) {
            dp[i] = 1; // 본인 한명 길이 1
            for (int j = 0; j < i; j++) {
                // 증가하는 부분 찾으면
                if (reversed[j] < reversed[i]) {
                    // 현재 dp값 vs 증가부분 dp 값 + 1 중에 큰 걸로 저장
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 최장 증가 부분 수열 저장
            maxLength = Math.max(maxLength, dp[i]);
        }

        // 결과 == 전체 병사 수 - 최장 증가 부분 수열의 길이
        System.out.println(n - maxLength);
    }
}
