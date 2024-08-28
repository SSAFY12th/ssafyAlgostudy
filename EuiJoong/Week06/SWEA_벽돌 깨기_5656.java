import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point {
        int r;
        int c;
        int power;

        public Point(int r, int c, int power) {
            this.r = r;
            this.c = c;
            this.power = power;
        }
    }
    private static int T, N, W, H;
    private static int[][] map;
    private static int[][] tempMap;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int[] number;
    private static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            tempMap = new int[H][W];
            number = new int[N];
            ans = Integer.MAX_VALUE;

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    int value = Integer.parseInt(st.nextToken());
                    map[i][j] = value;
                    tempMap[i][j] = value;
                }
            }

            permutation(0);
            System.out.println("#" + tc + " " + ans);
        }


    }

    private static void permutation(int depth) {
        if (depth == N) {

            int init_r = 0;
            int init_c = 0;

            for (int c = 0; c < N; c++) {
                for (int r = 0; r < H; r++) {
                    if (map[r][number[c]] != 0) {
                        init_r = r;
                        init_c = number[c];
                        break;
                    }
                }
                removeBlock(init_r, init_c);
                blockDown();
            }
            ans = Math.min(ans, blockCnt());
            copyMap();
            return;
        }

        if (ans == 0) {
            return;
        }

        for (int i = 0; i < W; i++) {
            number[depth] = i;
            permutation(depth + 1);
        }
    }

    private static void removeBlock(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c, map[r][c]));
        map[r][c] = 0;

        while (!q.isEmpty()) {
            Point point = q.poll();
            for (int range = 1; range < point.power; range++) {
                for (int i = 0; i < 4; i++) {
                    int nr = point.r + dr[i] * range;
                    int nc = point.c + dc[i] * range;

                    if (0 <= nr && nr < H && 0 <= nc && nc < W && map[nr][nc] != 0) {
                        q.offer(new Point(nr, nc, map[nr][nc]));
                        map[nr][nc] = 0;
                    }
                }
            }
        }
    }

    private static void blockDown() {
        Stack<Integer> s = new Stack<>();
        for (int c = 0; c < W; c++) {
            for (int r = 0; r < H; r++) {
                if (map[r][c] != 0) {
                    s.add(map[r][c]);
                }
            }

            for (int r = H - 1; r >= 0; r--) {
                if (s.isEmpty()) {
                    map[r][c] = 0;
                }else {
                    map[r][c] = s.pop();
                }
            }
        }
    }

    private static int blockCnt() {

        int res = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] != 0) {
                    res++;
                }
            }
        }

        return res;
    }

    private static void copyMap() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                map[i][j] = tempMap[i][j];
            }
        }
    }
}