import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int R, C, T;
    static int[][] map;
    static List<Point> airPurifier;
    static int[] dr = {-1 ,1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        //1. 입력 및 공기청정기 위치와 먼지 위치 리스트에 담아주기.
        input();
        //2. 동작
        for (int t = 0; t < T; t++) {
            solution();
        }
        //3. 미세먼지 세기
        System.out.println(count());
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        airPurifier = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) airPurifier.add(new Point(i, j));
            }
        }
    }

    private static void solution() {
        //1. 먼지 확산
        spreadDust();
        //2. 공기청정기 작동
        clean();
    }

    private static void spreadDust() {
        int[][] tmpMap = new int[R][C];
        for (int i = 0; i < 2; i++) {
            tmpMap[airPurifier.get(i).r][airPurifier.get(i).c] = -1;
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    tmpMap[i][j] += map[i][j];

                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if (0 > nr || nr >= R || 0 > nc || nc >= C || map[nr][nc] == -1) continue;

                        tmpMap[nr][nc] += (map[i][j] / 5);
                        tmpMap[i][j] -= (map[i][j] / 5);
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = tmpMap[i][j];
            }
        }
    }

    private static void clean() {
        //위에꺼 반시계방향으로 돌기
        Point topAirPurifier = airPurifier.get(0);
        int top_r = topAirPurifier.r;
        for (int i = top_r - 1; i >= 1; i--) {
            map[i][0] = map[i - 1][0];
        }
        for (int i = 0; i <= C - 2; i++) {
            map[0][i] = map[0][i + 1];
        }
        for (int i = 0; i <= top_r - 1; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        for (int i = C - 1; i >= 2; i--) {
            map[top_r][i] = map[top_r][i - 1];
        }
        map[top_r][1] = 0;

        //아래꺼 시계방향으로 돌기
        Point bottomAirPurifier = airPurifier.get(1);
        int bottom_r = bottomAirPurifier.r;
        for (int i = bottom_r + 1; i <= R - 2; i++) {
            map[i][0] = map[i + 1][0];
        }
        for (int i = 0; i <= C - 2; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }
        for (int i = R - 1; i >= bottom_r + 1; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }
        for (int i = C - 1; i >= 2; i--) {
            map[bottom_r][i] = map[bottom_r][i - 1];
        }
        map[bottom_r][1] = 0;
    }

    private static int count() {
        int res = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != -1) {
                    res += map[i][j];
                }
            }
        }
        return res;
    }
}