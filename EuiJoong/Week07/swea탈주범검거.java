package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int r;
        int c;
        int cnt;

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    private static int T, N, M, R, C, L;
    private static int ans;
    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int[][] pipe = {
            {},
            {0, 1, 2, 3},
            {0, 2},
            {1, 3},
            {0, 1},
            {1, 2},
            {2, 3},
            {0, 3}
    };
    private static int[][] map;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solution();
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void solution() {
        ans = 1;
        visit = new boolean[N][M];
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(R, C, 1));
        visit[R][C] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            int r = p.r;
            int c = p.c;
            int cnt = p.cnt;
            int pipeNum = map[r][c];

            if (cnt == L) break; //탈주범이 도망갈 수 있는 최대 범위임

            for (int i = 0; i < pipe[pipeNum].length; i++) {
                int n_dir = pipe[pipeNum][i];
                int nr = r + dir[n_dir][0];
                int nc = c + dir[n_dir][1];

                if (0 > nr || nr >= N || 0 > nc || nc >= M || map[nr][nc] == 0 || visit[nr][nc]) {
                    continue;
                }

                if (!isConnected(r, c, nr, nc)) continue;

                visit[nr][nc] = true;
                q.offer(new Point(nr, nc, cnt + 1));
                ans++;
            }
        }
    }

    private static boolean isConnected(int cur_r, int cur_c, int nr, int nc) {

        int pipeNum = map[nr][nc];
        for (int i = 0; i < pipe[pipeNum].length; i++) {
            int n_dir = pipe[pipeNum][i];
            if (nr + dir[n_dir][0] == cur_r && nc + dir[n_dir][1] == cur_c) {
                return true;
            }
        }
        return false;
    }
}