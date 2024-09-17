import java.util.*;
import java.io.*;

public class Solution {

    static StringTokenizer st;
    static int T;
    static int N;
    static int[][] map;
    static int[][] cost;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            cost = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                Arrays.fill(cost[i], Integer.MAX_VALUE);
            }

            for (int r = 0; r < N; r++) {
                String str = br.readLine();
                for (int c = 0; c < N; c++) {
                    map[r][c] = str.charAt(c) - '0';
                }
            }

            dijkstra(0, 0);

            System.out.println("#" + tc + " " + cost[N - 1][N - 1]);
        }
    }

    public static void dijkstra(int startX, int startY) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.add(new int[]{startX, startY, map[startX][startY]});
        cost[startX][startY] = map[startX][startY];

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int x = current[0];
            int y = current[1];
            int currentCost = current[2];

            if (visited[x][y]) continue;
            visited[x][y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                int newCost = currentCost + map[nx][ny];
                if (newCost < cost[nx][ny]) {
                    cost[nx][ny] = newCost;
                    pq.add(new int[]{nx, ny, newCost});
                }
            }
        }
    }
}
