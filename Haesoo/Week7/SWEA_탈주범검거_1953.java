import java.util.*;
import java.io.*;
public class SWEA1953 {
    public static int N, M, R, C, L;
    public static int[] dy = {0, 0, -1, 1};
    public static int[] dx = {1, -1, 0, 0};
    public static int cnt = 0;
    public static Queue <pair> q;
    public static boolean[][] visit;
    public static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            pair coord = new pair(R, C);
            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int a = Integer.parseInt(st.nextToken());
                    if (a != 0) map[i][j] = a;
                }
            }
            visit = new boolean[N][M];
            q = new LinkedList<>();
            cnt = 0;
            bfs(coord);
            System.out.println("#" + t + " " + cnt);
        }
    }
    public static boolean range (int ny, int nx) {
        return ny >= 0 && nx >= 0 && ny < N && nx < M;
    }
    public static void bfs (pair start) {
        int hour = 0;
        q.offer(start);
        while (!q.isEmpty()) {
            int qlen = q.size();
            cnt += qlen;
            for (int i = 0; i < qlen; i++) {
                int y = q.peek().y;
                int x = q.peek().x;
                visit[y][x] = true;
                q.poll();
                if (map[y][x] == 1) { // 상하좌우
                    for (int j = 0; j < 4; j++) {
                        int ny = y + dy[j];
                        int nx = x + dx[j];
                        if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) {
                            if (j == 0) right(ny, nx);
                            else if (j == 1) left(ny, nx);
                            else if (j == 2) up(ny, nx);
                            else if (j == 3) down(ny, nx);
                        }
                    }
                }
                else if (map[y][x] == 2) {
                    for (int j = 2; j < 4; j++) {
                        int ny = y + dy[j];
                        int nx = x + dx[j];
                        if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) {
                            if (j == 2) up(ny, nx);
                            else if (j == 3) down(ny, nx);
                        }
                    }
                }
                else if (map[y][x] == 3) {
                    for (int j = 0; j < 2; j++) {
                        int ny = y + dy[j];
                        int nx = x + dx[j];
                        if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) {
                            if (j == 0) right(ny, nx);
                            else if (j == 1) left(ny, nx);
                        }
                    }
                }
                else if (map[y][x] == 4) {
                    int ny = y + dy[0];
                    int nx = x + dx[0];
                    if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) right(ny, nx);
                    ny = y + dy[2];
                    nx = x + dx[2];
                    if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) up(ny, nx);

                }
                else if (map[y][x] == 5) {
                    int ny = y + dy[0];
                    int nx = x + dx[0];
                    if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) right(ny, nx);
                    ny = y + dy[3];
                    nx = x + dx[3];
                    if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) down(ny, nx);

                }
                else if (map[y][x] == 6) {
                    int ny = y + dy[3];
                    int nx = x + dx[3];
                    if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) down(ny, nx);
                    ny = y + dy[1];
                    nx = x + dx[1];
                    if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) left(ny, nx);
                }
                else if (map[y][x] == 7) {
                    int ny = y + dy[1];
                    int nx = x + dx[1];
                    if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) left(ny, nx);

                    ny = y + dy[2];
                    nx = x + dx[2];
                    if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) up(ny, nx);
                }
            }
            hour++;
            if (hour == L) {
                return;
            }
        }
    }
    public static void up(int ny, int nx) { // 1,2,5,6
        int val = map[ny][nx];
        if (val == 1 || val == 2 || val == 5 || val == 6) {
            pair p = new pair (ny, nx);
            q.offer(p);
            visit[ny][nx] = true;
        }
        else return;
    }
    public static void down(int ny, int nx) { // 1,2,4,7
        int val = map[ny][nx];
        if (val == 1 || val == 2 || val == 4 || val == 7) {
            pair p = new pair (ny, nx);
            q.offer(p);
            visit[ny][nx] = true;
        }
        else return;
    }
    public static void right(int ny, int nx) { // 1,3,6,7
        int val = map[ny][nx];
        if (val == 1 || val == 3 || val == 6 || val == 7) {
            pair p = new pair (ny, nx);
            q.offer(p);
            visit[ny][nx] = true;
        }
        else return;
    }
    public static void left(int ny, int nx) { // 1,3,4,5
        int val = map[ny][nx];
        if (val == 1 || val == 3 || val == 4 || val == 5) {
            pair p = new pair (ny, nx);
            q.offer(p);
            visit[ny][nx] = true;
        }
        else return;
    }
    public static class pair {
        int y;
        int x;
        pair (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
