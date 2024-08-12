import java.util.*;
import java.io.*;

public class Main {

    static int t;
    static int n, m;
    static int[][] dp; 
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            dp = new int[n+1][m+1];

            // 점화식: arr[n][m] = arr[n][m-1]+arr[n-1][m-1]

            // 초기화. n이 1일 때, dp값은 m값을 따라간다.
            for(int j = 1; j < m+1; j++) {
                dp[1][j] = j;
            }

            for(int j = 2; j < n+1; j++) {
                for(int k = 1; k < m+1; k++) {
                    dp[j][k] = dp[j][k-1] + dp[j-1][k-1];
                }
            }
            System.out.println(dp[n][m]);
        }
    }
}
