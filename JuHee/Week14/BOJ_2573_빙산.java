import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    static int N, M;
    static int[][] map;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int years = 0;
        while (true) {
            visited = new int[N][M];
            int icebergCount = 0;
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0 && visited[i][j] == 0) {
                        bfs(i, j);
                        icebergCount++;
                    }
                }
            }
            
            if (icebergCount == 0) {
                System.out.println(0);
                break;
            }
            
            if (icebergCount > 1) {
                System.out.println(years);
                break;
            }
            
            meltIcebergs();
            years++;
        }
    }

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = 1;

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            x = pos[0];
            y = pos[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    map[x][y] = Math.max(0, map[x][y] - 1);
                } else if (visited[nx][ny] == 0) {
                    visited[nx][ny] = 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }

    public static void meltIcebergs() {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = map[i][j];
                int adjacentWater = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
                        adjacentWater++;
                    }
                }
                temp[i][j] = Math.max(0, map[i][j] - adjacentWater);
            }
        }
        map = temp;
    }
}
