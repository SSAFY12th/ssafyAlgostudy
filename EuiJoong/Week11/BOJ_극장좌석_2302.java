import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int M = Integer.parseInt(br.readLine());
        int split = 0;
        int ans = 1;
        for (int i = 0; i < M; i++) {
            int vip = Integer.parseInt(br.readLine());
            ans *= dp[vip - split - 1];
            split = vip;
        }
        ans *= dp[N - split];
        System.out.println(ans);
    }
}