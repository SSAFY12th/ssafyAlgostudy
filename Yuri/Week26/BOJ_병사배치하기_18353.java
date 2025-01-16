import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] power;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        power = new int[n];
        dp = new int[n];

        Arrays.fill(dp, 1);

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++)
            power[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(power[i] < power[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLength = Arrays.stream(dp).max().getAsInt(); // 최대값 찾기

        System.out.println(n - maxLength);

    }

}
