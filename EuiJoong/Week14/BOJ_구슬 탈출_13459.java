import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Bead {
        int redR;
        int redC;
        int blueR;
        int blueC;
        int moves;

        public Bead(int redR, int redC, int blueR, int blueC, int moves) {
            this.redR = redR;
            this.redC = redC;
            this.blueR = blueR;
            this.blueC = blueC;
            this.moves = moves;
        }
    }

    static int N, M, ans;
    static char[][] map;
    static boolean[][][][] visit;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visit = new boolean[N][M][N][M];

        int redR = -1;
        int redC = -1;
        int blueR = -1;
        int blueC = -1;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    redR = i;
                    redC = j;
                } else if (map[i][j] == 'B') {
                    blueR = i;
                    blueC = j;
                }
            }
        }

        System.out.println(bfs(redR, redC, blueR, blueC) ? 1 : 0);
    }

    private static boolean bfs(int redR, int redC, int blueR, int blueC) {
        Queue<Bead> q = new LinkedList<>();
        q.offer(new Bead(redR, redC, blueR, blueC, 0));
        visit[redR][redC][blueR][blueC] = true;

        while (!q.isEmpty()) {
            Bead bead = q.poll();
            if (bead.moves >= 10) return false;

            for (int i = 0; i < 4; i++) {
                int[] red = move(bead.redR, bead.redC, dr[i], dc[i]);
                int[] blue = move(bead.blueR, bead.blueC, dr[i], dc[i]);

                int red_nr = red[0];
                int red_nc = red[1];

                int blue_nr = blue[0];
                int blue_nc = blue[1];

                if (map[blue_nr][blue_nc] == 'O') continue;
                if (map[red_nr][red_nc] == 'O') return true;

                if (red_nr == blue_nr && red_nc == blue_nc) {
                    if (red[2] > blue[2]) {
                        red_nr -= dr[i];
                        red_nc -= dc[i];
                    } else {
                        blue_nr -= dr[i];
                        blue_nc -= dc[i];
                    }
                }

                if (!visit[red_nr][red_nc][blue_nr][blue_nc]) {
                    visit[red_nr][red_nc][blue_nr][blue_nc] = true;
                    q.add(new Bead(red_nr, red_nc, blue_nr, blue_nc, bead.moves + 1));
                }

            }
        }

        return false;
    }

    private static int[] move(int r, int c, int dr, int dc) {
        int cnt = 0;
        while (map[r + dr][c + dc] != '#' && map[r][c] != 'O') {
            r += dr;
            c += dc;
            cnt++;
        }
        return new int[] {r, c, cnt};
    }
}
