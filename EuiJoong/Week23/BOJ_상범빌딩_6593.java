import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int h, r, c, step;

        public Point(int h, int r, int c, int step) {
            this.h = h;
            this.r = r;
            this.c = c;
            this.step = step;
        }
    }

    static int L, R, C;
    static char[][][] map;
    static boolean[][][] visited;
    static int[] dh = {-1, 1, 0, 0, 0, 0};
    static int[] dr = {0, 0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 0, -1, 1};
    static int start_h, start_r, start_c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) break;

            map = new char[L][R][C];
            visited = new boolean[L][R][C];
            start_h = 0;
            start_r = 0;
            start_c = 0;

            for (int h = 0; h < L; h++) {
                for (int r = 0; r < R; r++) {
                    String str = br.readLine();
                    for (int c = 0; c < C; c++) {
                        map[h][r][c] = str.charAt(c);
                        if (map[h][r][c] == 'S') {
                            start_h = h;
                            start_r = r;
                            start_c = c;
                        }
                    }
                }
                br.readLine();
            }

            boolean check = bfs(start_h, start_r, start_c);
            if (!check) {
                System.out.println("Trapped!");
            }
        }
    }

    private static boolean bfs(int h, int r, int c) {
        Queue<Point> q = new LinkedList<>();
        visited[h][r][c] = true;
        q.offer(new Point(h, r, c, 0));

        while (!q.isEmpty()) {
            Point p = q.poll();
            int cur_h = p.h, cur_r = p.r, cur_c = p.c, cur_step = p.step;

            if (map[cur_h][cur_r][cur_c] == 'E') {
                System.out.println("Escaped in " + cur_step + " minute(s).");
                return true;
            }

            for (int i = 0; i < 6; i++) {
                int nh = cur_h + dh[i];
                int nr = cur_r + dr[i];
                int nc = cur_c + dc[i];
                
                if (0 <= nh && nh < L && 0 <= nr && nr < R && 0 <= nc && nc < C && !visited[nh][nr][nc] && map[nh][nr][nc] != '#') {
                    visited[nh][nr][nc] = true;
                    q.offer(new Point(nh, nr, nc, cur_step + 1));
                }
            }
        }
        return false;
    }
}
