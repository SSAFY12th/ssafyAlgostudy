import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    static class Point {
        int r;
        int c;
        int cost;

        public Point(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    static int T;
    static int N;
    static int[][] map;
    static boolean[][] visit;
    static int[][] distance;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }

            ans = 0;
            move();
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void move() {
        PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        visit = new boolean[N][N];
        distance = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        pq.offer(new Point(0,0,0));
        distance[0][0] = 0;

        while (!pq.isEmpty()) {
            Point p = pq.poll();
            int r = p.r;
            int c = p.c;
            int cost = p.cost;

            if (r == N - 1 && c == N - 1) {
                ans = cost;
                break;
            }
            if (visit[r][c]) continue;
            visit[r][c] = true;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (0 <= nr && nr < N && 0 <= nc && nc < N && distance[nr][nc] > cost + map[nr][nc]) {
                    distance[nr][nc] = cost + map[nr][nc];
                    pq.offer(new Point(nr, nc, distance[nr][nc]));
                }
            }
        }


    }
}