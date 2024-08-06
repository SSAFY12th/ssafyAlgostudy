import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static StringTokenizer st;
    static int[][] graph;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static Queue<int[]> que = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) {
                    que.offer(new int[] {i, j});
                }
            }
        }
        bfs();

        boolean zero_chk = false;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 0) {
                    zero_chk = true;
                    break;
                }
            }
        }

        int ans = 0;
        if (!zero_chk) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    ans = Math.max(ans, graph[i][j]);
                }
            }
            ans -= 1;
        }
        else if (zero_chk) {
            ans = -1;
        }
        System.out.println(ans);
    }
    public static void bfs() {
        while (!que.isEmpty()) {
            int[] lst = que.poll();
            int cur_r = lst[0], cur_c = lst[1];
            for (int i = 0; i < 4; i++) {
                int nr = cur_r + dr[i], nc = cur_c + dc[i];
                if (nr >= 0 && nr < M && nc >= 0 && nc < N && graph[nr][nc] == 0) {
                    graph[nr][nc] = graph[cur_r][cur_c] + 1;
                    que.offer(new int[] {nr, nc});
                }
            }
        }
    }
}