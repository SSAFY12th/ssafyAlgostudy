package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int ans;

    //좌, 하, 우, 상
    static int[][] dr = {
            {0, -1, 1, -1, 1, -2, 2, -1, 1},
            {2, 1, 1, 0, 0, 0, 0, -1, -1},
            {0, -1, 1, -1, 1, -2, 2, -1, 1},
            {-2, -1, -1, 0, 0, 0, 0, 1, 1}
    };
    static int[][] dc = {
            {-2, -1, -1, 0, 0, 0, 0, 1, 1},
            {0, -1, 1, -1, 1, -2, 2, -1, 1},
            {2, 1, 1, 0, 0, 0, 0, -1, -1},
            {0, -1, 1, -1, 1, -2, 2, -1, 1}
    };
    static int[] ratio = {5, 10, 10, 7, 7, 2, 2, 1, 1};
    static int[][] push = {
            {0, 1, 0, -1},
            {-1, 0, 1, 0}
    };

    public static void main(String[] args) throws IOException {
        input();
        tornado();
        System.out.println(ans);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void tornado() {
        int num = 1;
        int r = N / 2;
        int c = N / 2;
        int step = 1;
        int dir = 0;

        while (num < N * N) {

            for (int d = 0; d < 2; d++) {
                for (int i = 0; i < step; i++) {
                    if (num >= N * N) break;

                    switch (dir) {
                        case 0: c--; break; //좌
                        case 1: r++; break; //하
                        case 2: c++; break; //우
                        case 3: r--; break; //상
                    }
                    num++;
                    if (map[r][c] != 0) {
                        spread(r, c, dir);
                    }
                }
                dir = (dir + 1 ) % 4;
            }
            step++;
        }
    }

    private static void spread(int r, int c, int dir) {
        int sand = map[r][c];
        int totalSpread = 0;

        for (int i = 0; i < 9; i++) {
            int nr = r + dr[dir][i];
            int nc = c + dc[dir][i];
            int spreadAmount = sand * ratio[i] / 100;
            totalSpread += spreadAmount;

            if (isRange(nr, nc)) {
                map[nr][nc] += spreadAmount;
            }else {
                ans += spreadAmount;
            }
        }

        int push_r = r + push[0][dir], push_c = c + push[1][dir];
        int remainingSand = sand - totalSpread;
        if (isRange(push_r, push_c)) {
            map[push_r][push_c] += remainingSand;
        }else {
            ans += remainingSand;
        }
    }

    private static boolean isRange(int nr, int nc) {
        if (0 <= nr && nr < N && 0 <= nc && nc < N) return true;
        return false;
    }
}