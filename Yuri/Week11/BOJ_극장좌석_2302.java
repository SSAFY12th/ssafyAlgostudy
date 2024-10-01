import java.io.*;

public class Main {

    static int N, M;
    static int result = 1;
    static int max = 0;
    static int[] vip;
    static int[] cal;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        vip = new int[M+1];
        cal = new int[M+2];

        for (int i = 1; i < M+1; i++) {
            vip[i] = Integer.parseInt(br.readLine());
            cal[i] = vip[i]-vip[i-1]-1;
            max = Math.max(max, cal[i]);
        }
        cal[M+1] = N-vip[M];
        max = Math.max(max, cal[M+1]);

        if(max < 2)
            max = 2;
        dp = new int[max+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < dp.length; i++)
            dp[i] = dp[i-1] + dp[i-2];

        for (int i = 1; i < cal.length; i++)
            result *= dp[cal[i]];

        System.out.println(result);
    }
}
