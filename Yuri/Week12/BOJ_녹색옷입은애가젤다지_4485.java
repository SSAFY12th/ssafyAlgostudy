import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map, memo;
    static int t = 1;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int r, c, value;
        public Point(int r, int c, int value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            map = new int[N][N];
            memo = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++)
                Arrays.fill(memo[i], Integer.MAX_VALUE);
            memo[0][0] = map[0][0];

            go();

            System.out.println("Problem "+t+": "+memo[N-1][N-1]);
            t++;
        }
    }

    public static void go() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, map[0][0]));

        while(!q.isEmpty()) {
            Point p = q.poll();

            int newR, newC;
            for (int i = 0; i < 4; i++) {
                newR = p.r+dr[i];
                newC = p.c+dc[i];

                if(newR >= 0 && newC >= 0 && newR < N && newC < N) {
                    if(p.value+map[newR][newC] < memo[newR][newC]) {
                        memo[newR][newC] = p.value+map[newR][newC];
                        q.add(new Point(newR, newC, memo[newR][newC]));
                    }
                }
            }
        }
    }
}
