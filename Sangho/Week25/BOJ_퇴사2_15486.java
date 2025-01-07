import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int N;

    static int[] time;
    static int[] money;

    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        time = new int[N];
        money = new int[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            money[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1];

        for(int i = N-1; i > -1; i--){
            // 현재 날짜 + 상담 소요일이 퇴사일을 넘어가면 다음날을 선택하는것으로 결정됨
            if(i + time[i] > N){
                dp[i] = dp[i+1];
            }

            else {
                // 다음날 dp값 vs 현재값 + 현재+소요일의 dp값을 비교하여 더 큰 값 선택
                dp[i] = Math.max(dp[i + 1],dp[i + time[i]] + money[i]);
            }
        }

        System.out.println(dp[0]);

    }
}
