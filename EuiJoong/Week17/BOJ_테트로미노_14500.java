import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, ans;
    static int[][] map;
    static int[] dr = {-1 ,1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        for (int i = 0 ; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                bruteforce(i, j, 1, map[i][j]);
                visited[i][j] = false;
            }
        }
        System.out.println(ans);


    }

    private static void bruteforce(int r, int c, int depth, int sum) {
        if (depth == 4) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {

            int nr = r + dr[i];
            int nc = c + dc[i];

            if (0 > nr || nr >= N || 0 > nc || nc >= M) continue;
            if (visited[nr][nc]) continue;

            if (depth == 2) {
                visited[nr][nc] = true;
                bruteforce(r, c, depth + 1, sum + map[nr][nc]);
                visited[nr][nc] = false;
            }

            visited[nr][nc] = true;
            bruteforce(nr, nc, depth + 1, sum + map[nr][nc]);
            visited[nr][nc] = false;
        }
    }

}
