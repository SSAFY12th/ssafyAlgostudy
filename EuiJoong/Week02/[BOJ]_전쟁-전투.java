import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] graph;
    static int N, M;
    static int[] dr;
    static int[] dc;
    static boolean[][] visit;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new char[M][N];
        visit = new boolean[M][N];
        dr = new int[] {0, 0, -1, 1};
        dc = new int[] {-1, 1, 0, 0};

        int ans1 = 0;
        int ans2 = 0;

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                graph[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 'W' && !visit[i][j]) {
                    ans1 += (int) Math.pow(dfs(i, j), 2);
                }

                if (graph[i][j] == 'B' && !visit[i][j]) {
                    ans2 += (int) Math.pow(dfs(i, j), 2);
                }
            }
        }

        System.out.println(ans1 + " " + ans2);
    }
    public static int dfs(int r, int c) {
        visit[r][c] = true;
        int cnt = 1;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr >= 0 && nr < M && nc >= 0 && nc < N && !visit[nr][nc] && graph[nr][nc] == graph[r][c]) {
                cnt += dfs(nr, nc);
            }
        }
        return cnt;
    }
}