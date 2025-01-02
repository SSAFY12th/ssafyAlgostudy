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
        System.out.println(dirSymbol[startDir]);
        System.out.println((sr + 1) + " " + (sc + 1));
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

    private static void dfs(int r, int c, int dir) {
        visited[r][c] = true;
        int nr = r + dr[dir] * 2;
        int nc = c + dc[dir] * 2;

        if (isRange(nr, nc) && map[nr][nc] == '#' && map[r][c] == '#') {
            visited[nr][nc] = true;
            System.out.print('A');
            dfs(nr, nc, dir);
        } else {
            int rightTurn = (dir + 3) % 4;
            int leftTurn = (dir + 1) % 4;
            
        }

    }



    private static boolean isRange(int r, int c) {
        if (0 <= r && r < H && 0 <= c && c < W) return true;
        return false;
    }
}
