import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] graph;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);
        System.out.println(ans);

    }
    public static void dfs(int depth) {
        if (depth == 3) {
            int count = bfs();
            ans = Math.max(count, ans);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 0) {
                    graph[i][j] = 1;
                    dfs(depth + 1);
                    graph[i][j] = 0;
                }
            }
        }
    }
    public static int bfs() {
        int[] dr = {0, 0 ,-1, 1};
        int[] dc = {-1, 1, 0, 0};

        int[][] chk = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                chk[i][j] = graph[i][j];
            }
        }
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (chk[i][j] == 2) {
                    q.offer(new int[] {i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] lst = q.poll();
            int cur_r = lst[0];
            int cur_c = lst[1];
            for (int i = 0; i < 4; i++) {
                int nr = cur_r + dr[i];
                int nc = cur_c + dc[i];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && chk[nr][nc] == 0) {
                    chk[nr][nc] = 2;
                    q.offer(new int[] {nr, nc});
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (chk[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}