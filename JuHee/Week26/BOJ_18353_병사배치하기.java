import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        int[] soldiers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            soldiers[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        int maxLength = 1;
        
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (soldiers[j] > soldiers[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        System.out.println(N - maxLength);
    }
}
