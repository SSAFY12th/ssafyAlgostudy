import java.util.*;
import java.io.*;

public class Main {

    static int n, k;
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        coins = new int[n+1];
        dp = new int[k+1];

        dp[0] = 1;

        for (int i = 1; i <= n; i++)
            coins[i] = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++)
            for (int j = coins[i]; j <= k; j++)
                dp[j] += dp[j-coins[i]];

        System.out.println(dp[k]);

    }
}
