import java.util.*;
import java.io.*;

public class Main {
  
    static int N, M, G, R, maxFlowers;
    static int[][] garden;
    static List<int[]> fertileLand;
    static List<int[]> green;
    static List<int[]> red;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        garden = new int[N][M];

        fertileLand = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                garden[i][j] = Integer.parseInt(st.nextToken());
                // 배양 할 수 있는 좌표 저장
                if (garden[i][j] == 2) {
                    fertileLand.add(new int[] {i, j});
                }
            }
        }

        maxFlowers = 0;

        green = new ArrayList<>();
        red = new ArrayList<>();

        chooseLiquidSpots(0);

        System.out.println(maxFlowers);
    }

    static void chooseLiquidSpots(int index) {
        // 초록색 배양액과 빨간색 배양액을 모두 뽑으면 시뮬레이션
        if (green.size() == G && red.size() == R) {
            simulate();
            return;
        }
        if (index >= fertileLand.size()) return;

        int[] pos = fertileLand.get(index);

        if (green.size() < G) {
            green.add(pos);
            chooseLiquidSpots(index + 1);
            green.remove(green.size() - 1);
        }

        if (red.size() < R) {
            red.add(pos);
            chooseLiquidSpots(index + 1);
            red.remove(red.size() - 1);
        }

        chooseLiquidSpots(index + 1);
    }

    static void simulate() {
        // 시간 저장 맵
        int[][] time = new int[N][M];

        // 색상 저장 맵
        int[][] color = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();

        // 초록색 배양액 뿌리기
        for (int[] g : green) {
            // {x, y, time, color}
            queue.add(new int[] {g[0], g[1], 0, 1});
            color[g[0]][g[1]] = 1;
        }
        for (int[] r : red) {
            // {x, y, time, color}
            queue.add(new int[] {r[0], r[1], 0, 2});
            color[r[0]][r[1]] = 2;
        }

        // 꽃의 개수
        int flowers = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], t = cur[2], col = cur[3];

            if (color[x][y] == 3) continue;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (garden[nx][ny] == 0 || color[nx][ny] == 3) continue;

                if (color[nx][ny] == 0) {
                    color[nx][ny] = col;
                    time[nx][ny] = t + 1;
                    queue.add(new int[] {nx, ny, t + 1, col});
                // 색이 다르고 도착 시간이 같으면
                } else if (color[nx][ny] != col && time[nx][ny] == t + 1) {
                    // 꽃 피우기
                    color[nx][ny] = 3;
                    flowers++;
                }
            }
        }

        maxFlowers = Math.max(maxFlowers, flowers);
    }
}
