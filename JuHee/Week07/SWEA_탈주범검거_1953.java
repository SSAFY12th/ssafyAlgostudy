import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, R, C, L;
    static int[][] map;
    static boolean[][] visited;

    // 터널 별 이동 가능한 방향 (상, 하, 좌, 우)
    static int[][][] directions = {
            {}, // 0: 빈칸
            {{-1, 0}, {1, 0}, {0, -1}, {0, 1}},    // 1: 상, 하, 좌, 우
            {{-1, 0}, {1, 0}},                     // 2: 상, 하
            {{0, -1}, {0, 1}},                     // 3: 좌, 우
            {{-1, 0}, {0, 1}},                     // 4: 상, 우
            {{1, 0}, {0, 1}},                      // 5: 하, 우
            {{1, 0}, {0, -1}},                     // 6: 하, 좌
            {{-1, 0}, {0, -1}}                     // 7: 상, 좌
    };

    static int[] reverse = {1, 0, 3, 2};

    // 터널끼리 연결될 수 있는지 확인
    static boolean canMove(int x, int y, int nx, int ny) {
        int nextTunnel = map[nx][ny];
        if (nextTunnel == 0) return false; // 다음 칸이 빈칸이면 이동 불가
        for (int d = 0; d < directions[nextTunnel].length; d++) {
            int revX = nx + directions[nextTunnel][d][0];
            int revY = ny + directions[nextTunnel][d][1];
            if (revX == x && revY == y) return true; // 연결 가능
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 지도 세로 크기
            M = Integer.parseInt(st.nextToken()); // 지도 가로 크기
            R = Integer.parseInt(st.nextToken()); // 맨홀 위치의 세로 좌표
            C = Integer.parseInt(st.nextToken()); // 맨홀 위치의 가로 좌표
            L = Integer.parseInt(st.nextToken()); // 경과 시간

            map = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = bfs(R, C);
            System.out.printf("#%d %d\n", t, answer);
        }
    }

    static int bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY, 1}); // x, y, 경과 시간
        visited[startX][startY] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int time = cur[2];

            if (time >= L) continue; // 경과 시간이 L보다 크면 더 이상 탐색하지 않음

            int tunnelType = map[x][y];
            for (int d = 0; d < directions[tunnelType].length; d++) {
                int nx = x + directions[tunnelType][d][0];
                int ny = y + directions[tunnelType][d][1];

                // 범위를 벗어나거나 이미 방문한 경우
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;

                // 다음 터널이 현재 터널과 연결될 수 있는지 확인
                if (canMove(x, y, nx, ny)) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, time + 1});
                    count++;
                }
            }
        }
        return count;
    }
}
