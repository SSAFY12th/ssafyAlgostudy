import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] time;
    static int[] pay;
    static StringTokenizer st;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        time = new int[N];
        pay = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0,0);
        System.out.println(ans);
    }
    public static void dfs(int day, int total) {
        if (day >= N) {
            ans = Math.max(ans, total);
            return;
        }

        if (day + time[day] <= N) {
            dfs(day + time[day], total + pay[day]);
        }
        dfs(day + 1, total);
    }
}
