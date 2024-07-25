import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] arr;
    static int[] result;
    static StringBuilder sb;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        result = new int[m];
        sb = new StringBuilder();
        visit = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        dfs(0);
        System.out.println(sb.toString());
    }

    public static void dfs(int cnt) {
        if (cnt == m) {
            for (int i = 0; i < m; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int last = 0;
        for (int i = 0; i < n; i++) {
            // 이미 방문했거나, 현재값이 이전값과 같고(중복으로 사용되고 있고), 이전값을 사용하지 않은 경우
            if (visit[i] || (i > 0 && arr[i] == arr[i - 1] && !visit[i - 1])) {
                continue;
            }
            result[cnt] = arr[i];
            visit[i] = true;
            dfs(cnt + 1);
            visit[i] = false; 
        }
    }
}
