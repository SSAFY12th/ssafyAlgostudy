import java.io.*;
import java.util.*;
public class BOJ14501 {
    public static int N;
    public static int[] T, P;
    public static int maxval;
    static int[] dp = new int [15];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = new int[N + 1];
        P = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
            dp[i] = P[i];
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (i - j >= T[j]) dp[i] = Math.max(P[i] + dp[j], dp[i]);
            }
        }
        for (int i = 0; i < N; i++)
            if (i + T[i] <= N)
                if (maxval < dp[i]) maxval = dp[i];
        System.out.println(maxval);
    }
}

//dfs로 푼 코드
//public class BOJ14501 {
//    public static int N;
//    public static int[] T, P;
//    public static int maxval;
//    public static void dfs(int day, int sum) {
//        if (day > N) return;
//        maxval = Math.max(maxval, sum);
//        for (int i = day; i < N; i++) {
//            dfs (i + T[i], sum + P[i]);
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        T = new int[N + 1];
//        P = new int[N + 1];
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            T[i] = Integer.parseInt(st.nextToken());
//            P[i] = Integer.parseInt(st.nextToken());
//        }
//
//        dfs(0, 0);
//        System.out.println(maxval);
//    }
//}
