import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[][] day = new int[16][2];
    static int[] dp = new int[17];
    static StringTokenizer st;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            day[i][0] = Integer.parseInt(st.nextToken());
            day[i][1] = Integer.parseInt(st.nextToken());
        }

        // 뒤에서부터 확인.
        for(int i = n; i > 0; i--) {
            if(i + day[i][0] -1 > n) {  // 만약 일하는데 필요한 시간이 n을 넘긴다면
                dp[i] = Math.max(0, dp[i+1]);   // 0 또는 바로 다음 날 벌 수 있는 돈 중 max값 선택
            }
            else {
                // 현재 날에서 일하는데 필요한 시간만큼 뒤로 이동하여 dp값과 벌 수 있는 돈(day[i][1])을 더한 값과
                // 바로 다음 날 최대로 벌 수 있는 돈 중 max값을 선택
                dp[i] = Math.max(dp[i+day[i][0]] + day[i][1], dp[i+1]);
            }
        }

        System.out.println(dp[1]);

    }
}
