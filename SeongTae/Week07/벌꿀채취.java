import java.util.*;
import java.io.*;

public class Solution {
    static int T, N, M, C, max;
    static int[][] map;
    static List<Point> first_worker = new LinkedList<>();
    static List<Point> second_worker = new LinkedList<>();
    static boolean[][] visited;

    static class Point {
        int r;
        int c;
        int value;

        public Point(int r, int c, int value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 맵의 크기 N*N
            M = Integer.parseInt(st.nextToken()); // 벌통의 개수
            C = Integer.parseInt(st.nextToken()); // 하나의 통에 채취할 수 있는 최대 꿀의 양
            map = new int[N][N];
            max = Integer.MIN_VALUE;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            select_first_worker(0, 0, 0);
            System.out.println("#" + tc + " " + max);
        }
    }

    static void select_first_worker(int cnt, int row, int col) {
        if (cnt == 1) {
            select_second_worker(0, row, col);
            return;
        }
        for (int i = row; i < N; i++) {
            for (int j = (i == row) ? col : 0; j < N - M + 1; j++) {
                for (int k = 0; k < M; k++) {
                    first_worker.add(new Point(i, j + k, map[i][j+k]));
                }
                select_first_worker(cnt + 1, i, j + M);
                for (int k = 0; k < M; k++) {
                    first_worker.remove(first_worker.size() - 1);
                }
            }
        }
    }

    static void select_second_worker(int cnt, int row, int col) {
        if (cnt == 1) {
            int first = select_tong(0, 0, new ArrayList<>(), 0, first_worker);
            int second = select_tong(0, 0, new ArrayList<>(), 0 , second_worker);
            int result = first + second;
            max = Math.max(max, result);
            return;
        }
        for (int i = row; i < N; i++) {
            for (int j = (i == row) ? col : 0; j < N - M + 1; j++) {
                for (int k = 0; k < M; k++) {
                    second_worker.add(new Point(i, j + k, map[i][j+k]));
                }
                select_second_worker(cnt + 1, i, j + M);
                for (int k = 0; k < M; k++) {
                    second_worker.remove(second_worker.size() - 1);
                }
            }
        }
    }

    private static int select_tong(int cnt, int sum, List<Integer> cal, int cal_max, List<Point> worker) {
        if (sum > C) {
            return 0;
        }
        if (cnt == M) {
            return cal_max;
        }
        int value = worker.get(cnt).value;
        cal.add(value);
        int include = select_tong(cnt + 1, sum + value, cal, cal_max + (value * value), worker);
        cal.remove(cal.size() - 1);
        int exclude = select_tong(cnt + 1, sum, cal, cal_max, worker);
        return Math.max(include, exclude);
    }
}
