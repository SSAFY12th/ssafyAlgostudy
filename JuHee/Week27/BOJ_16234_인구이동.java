import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1}; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(simulate());
    }

    static int simulate() {
        int days = 0;

        while (true) {
            visited = new boolean[N][N];
            boolean moved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        List<int[]> union = new ArrayList<>();
                        int sum = bfs(i, j, union);

                        if (union.size() > 1) {
                            moved = true;
                            int avg = sum / union.size();
                            for (int[] pos : union) {
                                map[pos[0]][pos[1]] = avg;
                            }
                        }
                    }
                }
            }
            if (!moved) break;
            days++;
        }
        return days;
    }

    static int bfs(int x, int y, List<int[]> union) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        union.add(new int[]{x, y});
        visited[x][y] = true;

        int sum = map[x][y];

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    int diff = Math.abs(map[cx][cy] - map[nx][ny]);
                    if (diff >= L && diff <= R) {
                        queue.add(new int[]{nx, ny});
                        union.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        sum += map[nx][ny];
                    }
                }
            }
        }
        return sum;
    }
}
