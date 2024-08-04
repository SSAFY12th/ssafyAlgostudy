import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int[][] graph;
    static StringTokenizer st;
    static int ans;
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        int t_max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                t_max = Math.max(t_max, graph[i][j]);
            }
        }
        for (int t = 0; t < t_max; t++) {
            visit = new boolean[N][N];
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (graph[i][j] > t && !visit[i][j]) {
                        bfs(i, j, t);
                        cnt++;
                    }
                }
            }
            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);

    }
    public static void bfs(int r, int c, int height) {
        Queue<int[]> q = new LinkedList<>();
        visit[r][c] = true;
        q.offer(new int[] {r, c});

        while (!q.isEmpty()) {
            int[] lst = q.poll();
            int cur_r = lst[0];
            int cur_c = lst[1];
            for (int i = 0; i < 4; i++) {
                int nr = cur_r + dr[i], nc = cur_c + dc[i];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visit[nr][nc] && graph[nr][nc] > height) {
                    q.offer(new int[] {nr, nc});
                    visit[nr][nc] = true;
                }
            }
        }
    }
}