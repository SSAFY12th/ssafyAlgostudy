import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class wormHolePoint {
        int r;
        int c;
        int point;

        public wormHolePoint(int r, int c, int point) {
            this.r = r;
            this.c = c;
            this.point = point;
        }

        @Override
        public String toString() {
            return "wormHolePoint{" +
                    "r=" + r +
                    ", c=" + c +
                    ", point=" + point +
                    '}';
        }
    }

    static int T, N, ans;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc= {0, 1, 0, -1};
    static int[][] nextDir = {
            {0, 2, 1, 3, 2, 2},
            {0, 3, 3, 2, 0, 3},
            {0, 1, 0, 0, 3, 0},
            {0, 0, 2, 1, 1, 1}
    };
    static List<wormHolePoint> wp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            wp = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 6) {
                        wp.add(new wormHolePoint(i, j, map[i][j]));
                    }
                }
            }

            //System.out.println(wp);

            ans = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) {
                        for (int d = 0; d < 4; d++) {
                            int score = move(i, j, d);
                            ans = Math.max(ans, score);
                        }
                    }
                }
            }

            //System.out.println(move(2, 3, 1));
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static int move(int r, int c, int dir) {

        int nr = r;
        int nc = c;
        int score = 0;
        boolean moveChk = false;

        while (true) {
            //원래 시작 지점으로 돌아오면 끝~
            if (nr == r && nc == c && moveChk) {
                break;
            }
            //블랙홀에 빠지면 끝~~
            if (map[nr][nc] == -1) {
                break;
            }

            nr += dr[dir];
            nc += dc[dir];
            //System.out.println(nr+","+nc);
            moveChk = true;

            //벽에 부딪히면~~ 점수 올라감, 방향 반대로 바뀜.
            if (0 > nr || nr >= N || 0 > nc || nc >= N) {
                nr -= dr[dir];
                nc -= dc[dir];

                switch (dir) {
                    case 0:
                        dir = 2;
                        break;
                    case 1:
                        dir = 3;
                        break;
                    case 2:
                        dir = 0;
                        break;
                    case 3:
                        dir = 1;
                        break;
                }
                score++;

            }

            //블록에 부딪혔다?
            if (map[nr][nc] >= 1 && map[nr][nc] <= 5) {
                dir = nextDir[dir][map[nr][nc]];
                score++;
                continue;
            }

            //웜홀이다!!
            if (map[nr][nc] > 5) {
                int[] lst = wormhole(nr, nc);
                if (lst[0] >= 0 && lst[0] < N && lst[1] >= 0 && lst[1] < N) {
                    nr = lst[0];
                    nc = lst[1];
                }
            }
        }
        return score;
    }

    private static int[] wormhole (int r, int c) {

        int[] res = new int[2];

        for (int i = 0; i < wp.size(); i++) {
            wormHolePoint wh = wp.get(i);
            if (wh.point == map[r][c]) {
                if (wh.r != r || wh.c != c) {
                    res[0] = wh.r;
                    res[1] = wh.c;
                }
            }
        }

        return res;
    }
}