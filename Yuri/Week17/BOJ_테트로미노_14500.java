import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int result = 0;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][][] dir = {    // ㅏ 모양
            { {0, 0, 0, 1}, {0, 1, 2, 1} },
            { {0, 1, 2, 1}, {0, 0, 0, -1} },
            { {0, 0, 0, -1}, {0, 1, 2, 1} },
            { {0, 1, 2, 1}, {0, 0, 0, 1} }
    };

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Point p = new Point(i, j);
                find(0, 0, p);
                find(p);
            }
        }

        System.out.println(result);

    }
    
    public static void find(Point start) {
        for (int i = 0; i < 4; i++) {
            boolean canMake = true;
            int sum = 0;
            for (int j = 0; j < 4; j++) {
                int moveR = start.r + dir[i][0][j];
                int moveC = start.c + dir[i][1][j];

                if(moveR >= 0 && moveC >= 0 && moveR < n && moveC < m)
                    sum += map[moveR][moveC];
                else {
                    canMake = false;
                    break;
                }
            }

            if(canMake)
                result = Math.max(result, sum);
        }
    }

    public static void find(int cnt, int sum, Point now) {
        if(cnt == 4) {
            result = Math.max(result, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int moveR = now.r + dr[i];
            int moveC = now.c + dc[i];

            if(moveR >= 0 && moveC >= 0 && moveR < n && moveC < m && !visited[moveR][moveC]) {
                visited[moveR][moveC] = true;
                find(cnt+1, sum + map[moveR][moveC], new Point(moveR, moveC));
                visited[moveR][moveC] = false;
            }
        }
    }
}
