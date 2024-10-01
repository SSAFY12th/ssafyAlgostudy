import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int N, M;
    static int[] dp;
    static boolean[] vipList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N, M 입력 받기
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dp = new int[N + 1];
        vipList = new boolean[N + 1];

        // VIP 좌석 입력 받기
        for (int i = 0; i < M; i++) {
            int vipSeat = Integer.parseInt(br.readLine());
            vipList[vipSeat] = true;
        }

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            // 둘 중 하나가 vip면 못바꾸잖아
            if (vipList[i] || vipList[i - 1]) {
                // 그래서 걍 그전 디피값 이어 받음 (같은 경우임)
                dp[i] = dp[i - 1];
            } else {
                // 둘다 vip가 아니면 교환이 되니까 점화식 적용
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }

        System.out.println(dp[N]);
    }
}
