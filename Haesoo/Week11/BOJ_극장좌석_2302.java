import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] vipSeats = new int[M + 2];

        for (int i = 1; i <= M; i++) {
            vipSeats[i] = Integer.parseInt(br.readLine());
        }

        vipSeats[0] = 0;       
        vipSeats[M + 1] = N + 1; 

        Arrays.sort(vipSeats);

        int[] dp = new int[N + 1];
        dp[0] = 1; 
        dp[1] = 1; 
        
        if (N >= 2) dp[2] = 2; 

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int result = 1; 
        for (int i = 1; i <= M + 1; i++) {
            int sectionLength = vipSeats[i] - vipSeats[i - 1] - 1; 
            result *= dp[sectionLength]; 
        }

        System.out.println(result);
    }
}
