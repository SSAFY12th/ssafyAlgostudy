import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1};

    static int N;
    static int result = 0;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(map[i][j], max);
                min = Math.min(map[i][j], min);
            }
        }

        for (int num = min - 1; num <= max; num++) {
            visited = new int[N][N];
            int safeZoneCount = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if (map[i][j] > num && visited[i][j] != 1) {
                        bfs(i, j, num);
                        safeZoneCount++;
                    }
                }
            }

            result = Math.max(result, safeZoneCount);
        }

        System.out.println(result);
    }

    public static void bfs(int x, int y, int height) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y});
        visited[x][y] = 1;

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int curX = pos[0];
            int curY = pos[1];

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && visited[nx][ny] != 1 && map[nx][ny] > height) {
                    visited[nx][ny] = 1;
                    q.offer(new int[] {nx, ny});
                }
            }
        }
    }
}
