import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] time = new int[N + 1];
        int[] pay = new int[N + 1];
        int[] dp = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 1; i <= N; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);
            
            if (i + time[i] - 1 <= N) {
                dp[i + time[i] - 1] = Math.max(dp[i + time[i] - 1], dp[i - 1] + pay[i]);
            }
        }
        
        System.out.println(dp[N]);
    }
}
