import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static int[] res;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        res = new int[M];
        visit = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        dfs(0);
        System.out.println(sb);
    }
    public static void dfs(int depth) {
        //탈출부
        if (depth == M) {
            for (int val : res) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }

        //구현부
        int past = 0;
        for (int i = 0; i < N; i++) {
            if (!visit[i] && arr[i] != past) {
                visit[i] = true;
                past = arr[i];
                res[depth] = arr[i];
                dfs(depth + 1);
                visit[i] = false;
            }
        }
    }
}