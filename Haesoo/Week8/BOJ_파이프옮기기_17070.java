import java.io.*;
import java.util.*;

public class BOJ17070 {
    public static int cnt = 0;
    public static int N;
    public static int[] dy = {0, 1, 1};
    public static int[] dx = {1, 0, 1}; // 가로, 세로, 대각선
    public static int[][] map;
    public static boolean[][] visited;
    public static pair[] pipe = new pair[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        /* 처음 시작 좌표 2개를 파이프 배열에 담음 & 방문처리 */
        visited[0][0] = true;
        visited[0][1] = true;
        for (int i = 0; i < 1; i++) {
            pair a = new pair(0, i);
            pipe[i] = a;
        }
        dfs(0, 1, 0);
        System.out.println(cnt);
    }
    public static void dfs(int y, int x, int curdir) {
        if (y == N - 1 && x == N - 1) {
            cnt++;
            return;
        }
        if (curdir == 0) { // 가로
            pair temp1 = pipe[1];
            int ny = y + dy[0];
            int nx = x + dx[0];
            if (range(ny, nx)) {
                if (map[ny][nx] == 0 && !visited[ny][nx]) {
                    pipe[0] = pipe[1];
                    pair p = new pair(ny, nx);
                    pipe[1] = p;
                    visited[ny][nx] = true;
                    dfs(ny, nx, 0);
                    visited[ny][nx] = false;
                }
            }
            ny = y + dy[2];
            nx = x + dx[2];
            if (range(ny, nx)) {
                if (map[ny][nx] == 0 && map[y][nx] == 0 && map[ny][x] == 0 && !visited[ny][nx]) {
                    pipe[0] = temp1;
                    pair p = new pair(ny, nx);
                    pipe[1] = p;
                    visited[ny][nx] = true;
                    dfs(ny, nx, 2);
                    visited[ny][nx] = false;
                }
            }
        }
        else if (curdir == 1) {
            pair temp1 = pipe[1];
            int ny = y + dy[1];
            int nx = x + dx[1];
            if (range(ny, nx)) {
                if (map[ny][nx] == 0 && !visited[ny][nx]) {
                    pipe[0] = pipe[1];
                    pair p = new pair(ny, nx);
                    pipe[1] = p;
                    visited[ny][nx] = true;
                    dfs(ny, nx, 1);
                    visited[ny][nx] = false;
                }
            }
            ny = y + dy[2];
            nx = x + dx[2];
            if (range(ny, nx)) {
                if (map[ny][nx] == 0 && map[y][nx] == 0 && map[ny][x] == 0 && !visited[ny][nx]) {
                    pipe[0] = temp1;
                    pair p = new pair(ny, nx);
                    pipe[1] = p;
                    visited[ny][nx] = true;
                    dfs(ny, nx,  2);
                    visited[ny][nx] = false;
                }
            }
        }
        else if (curdir == 2) {
            pair temp1 = pipe[1];
            int ny = y + dy[0];
            int nx = x + dx[0];
            if (range(ny, nx)) {
                if (map[ny][nx] == 0 && !visited[ny][nx]) {
                    pipe[0] = pipe[1];
                    pair p = new pair(ny, nx);
                    pipe[1] = p;
                    visited[ny][nx] = true;
                    dfs(ny, nx, 0);
                    visited[ny][nx] = false;
                }
            }
            ny = y + dy[1];
            nx = x + dx[1];
            if (range(ny, nx)) {
                if (map[ny][nx] == 0 && !visited[ny][nx]) {
                    pipe[0] = temp1;
                    pair p = new pair(ny, nx);
                    pipe[1] = p;
                    visited[ny][nx] = true;
                    dfs(ny, nx, 1);
                    visited[ny][nx] = false;
                }
            }
            ny = y + dy[2];
            nx = x + dx[2];
            if (range(ny, nx)) {
                if (map[ny][nx] == 0 && map[y][nx] == 0 && map[ny][x] == 0 && !visited[ny][nx]) {
                    pipe[0] = temp1;
                    pair p = new pair(ny, nx);
                    pipe[1] = p;
                    visited[ny][nx] = true;
                    dfs(ny, nx,  2);
                    visited[ny][nx] = false;
                }
            }
        }
    }
    public static boolean range(int ny, int nx) {
        return ny >= 0 && nx >= 0 && ny < N && nx < N;
    }

    public static class pair {
        int y, x;
        pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
