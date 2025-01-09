import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] dp;
    static int[][] schedule;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        dp = new int[n+2];
        schedule = new int[n+1][2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = n; i > 0 ; i--) {
            int day = schedule[i][0];
            if(i+day-1 > n) { // 이러면 이 일은 할 수 없음.
                dp[i] = dp[i+1];
            } else {
                dp[i] = Math.max(dp[i+day] + schedule[i][1], dp[i+1]);
            }
        }

        System.out.println(dp[1]);

    }
}
