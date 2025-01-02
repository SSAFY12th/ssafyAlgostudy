import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int H, W, startDir, sr, sc;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static char[] dirSymbol = {'^', '>', 'v', '<'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];

        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        start();
        System.out.println((sr + 1) + " " + (sc + 1));
        System.out.println(dirSymbol[startDir]);
        visited = new boolean[H][W];
        int dir = startDir;
        dfs(sr, sc, dir);

    }

    private static void start() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                int adjWayCount = 0;
                if (map[i][j] == '#') {
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dr[d];
                        int nj = j + dc[d];
                        if (isRange(ni, nj) && map[ni][nj] == '#') adjWayCount++;
                    }
                }

                if (adjWayCount == 1) {
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dr[d];
                        int nj = j + dc[d];
                        if (isRange(ni, nj)) {
                            if (map[i][j] == '#' && map[ni][nj] == '#') {
                                startDir = d;
                                sr = i;
                                sc = j;
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 1. 현재 바라보고 있는 방향에서 두 칸 앞으로 갈 수 있는지 확인한다.
     * 2. 갈 수 있으면 전진(dfs), 갈 수 없다면 방향 회전하여 확인해보기!
     */

    private static void dfs(int r, int c, int dir) {
        visited[r][c] = true;
        if (isForward(r, c, dir)) {
            System.out.print("A");
            for (int i = 0; i < 2; i++) {
                r += dr[dir];
                c += dc[dir];
                visited[r][c] = true;
            }
            dfs(r, c, dir);
        } else {
            int leftTurn = (dir + 3) % 4;
            int rightTurn = (dir + 1) % 4;

            if (isForward(r, c, leftTurn)) {
                System.out.print("L");
                dfs(r, c, leftTurn);
            } else if (isForward(r, c, rightTurn)) {
                System.out.print("R");
                dfs(r, c, rightTurn);
            }
        }


    }

    private static boolean isForward(int r, int c, int dir) {
        int nr1 = r + dr[dir];
        int nc1 = c + dc[dir];
        int nr2 = r + dr[dir] * 2;
        int nc2 = c + dc[dir] * 2;
        if (isRange(nr1, nc1) && isRange(nr2, nc2) && map[nr1][nc1] == '#' && map[nr2][nc2] == '#') return true;
        return false;
    }


    private static boolean isRange(int r, int c) {
        if (0 <= r && r < H && 0 <= c && c < W) return true;
        return false;
    }
}
