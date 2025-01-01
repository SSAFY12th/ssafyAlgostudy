import java.util.*;
import java.io.*;

public class Main {

    static int H, W;
    static char[][] map;

    // 방향 배열: 북, 동, 남, 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[] directions = {'^', '>', 'v', '<'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[H][W];

        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 시작 지점 탐색
        int startX = -1, startY = -1;
        int startDir = -1;

        for (int i = 0; i < H && startX == -1; i++) {
            for (int j = 0; j < W && startX == -1; j++) {
                if (map[i][j] == '#' && isValidStart(i, j)) {
                    startX = i;
                    startY = j;
                    startDir = findInitialDirection(i, j);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append((startX + 1)).append(" ").append((startY + 1)).append("\n");
        result.append(directions[startDir]).append("\n");

        // 경로 탐색
        StringBuilder path = new StringBuilder();
        dfs(startX, startY, startDir, path);

        result.append(path);
        System.out.println(result);
    }

    static boolean isValidStart(int x, int y) {
        int count = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (isInBounds(nx, ny) && map[nx][ny] == '#') {
                count++;
            }
        }
        return count == 1;
    }

    static int findInitialDirection(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (isInBounds(nx, ny) && map[nx][ny] == '#') {
                return d;
            }
        }
        return -1;
    }

    static void dfs(int x, int y, int dir, StringBuilder path) {
        // 현재 위치를 지나간 것으로 처리
        map[x][y] = '.';

        // 직진 가능한 경우
        if (canMoveTwoSteps(x, y, dir)) {
            moveTwoSteps(x, y, dir);
            x += dx[dir] * 2;
            y += dy[dir] * 2;
            path.append("A");
            dfs(x, y, dir, path);
        } else {
            // 좌회전 또는 우회전 가능한 경우
            int leftDir = (dir + 3) % 4;
            int rightDir = (dir + 1) % 4;

            if (canMoveTwoSteps(x, y, leftDir)) {
                path.append("L");
                dfs(x, y, leftDir, path);
            } else if (canMoveTwoSteps(x, y, rightDir)) {
                path.append("R");
                dfs(x, y, rightDir, path);
            }
        }
    }

    static boolean canMoveTwoSteps(int x, int y, int dir) {
        int nx1 = x + dx[dir];
        int ny1 = y + dy[dir];
        int nx2 = x + dx[dir] * 2;
        int ny2 = y + dy[dir] * 2;
        return isInBounds(nx1, ny1) && isInBounds(nx2, ny2) && map[nx1][ny1] == '#' && map[nx2][ny2] == '#';
    }

    static void moveTwoSteps(int x, int y, int dir) {
        for (int i = 0; i < 3; i++) {
            map[x][y] = '.';
            x += dx[dir];
            y += dy[dir];
        }
    }

    static boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < H && y < W;
    }
}
