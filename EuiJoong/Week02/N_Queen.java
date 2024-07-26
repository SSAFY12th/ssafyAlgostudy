import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[] visit1, visit2, visit3;
    static int N;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        visit1 = new boolean[N];
        visit2 = new boolean[N + N];
        visit3 = new boolean[N + N];

        dfs(0);
        System.out.println(cnt);

    }
    public static void dfs(int depth) {
        if (depth == N) {
            cnt += 1;
            return;
        }


        for (int i = 0; i < N; i++) {
            if (!visit1[i] && !visit2[depth + i] && !visit3[depth - i + N]) {
                visit1[i] = true;
                visit2[depth + i] = true;
                visit3[depth - i + N] = true;
                dfs(depth + 1);
                visit1[i] = false;
                visit2[depth + i] = false;
                visit3[depth - i + N] = false;
            }
        }
    }
}