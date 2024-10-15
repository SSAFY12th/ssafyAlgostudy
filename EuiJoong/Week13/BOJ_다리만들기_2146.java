import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point {
        int r;
        int c;
        int length;

        public Point(int r, int c, int length) {
            this.r = r;
            this.c = c;
            this.length = length;
        }
    }

    static int N;
    static int[][] map;
    static boolean[][] visit;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static List<Point>[] island;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int [N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 섬 번호 부여
        int num = numbering();
        // 섬 번호당 좌표 리스트로 관리
        addList(num);
        // 다리 이어주기
        ans = Integer.MAX_VALUE;
        for (int i = 1; i < island.length; i++) {
            for (int j = 0; j < island[i].size(); j++) {
                Point point = island[i].get(j);
                int r = point.r;
                int c = point.c;
                connectBridge(r, c, i);

            }
        }
        System.out.println(ans);
    }

    private static int numbering() {
        visit = new boolean[N][N];
        int number = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j] && map[i][j] != 0) {
                    bfs(i, j, number++);
                }
            }
        }
        return number;
    }

    private static void bfs(int r, int c, int number) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c, 0));
        map[r][c] = number;
        visit[r][c] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            r = p.r;
            c = p.c;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (0 > nr || nr >= N || 0 > nc || nc >= N || visit[nr][nc] || map[nr][nc] == 0) continue;
                map[nr][nc] = number;
                visit[nr][nc] = true;
                q.offer(new Point(nr, nc, 0));
            }
        }
    }

    private static void addList(int num) {
        island = new ArrayList[num];
        for (int i = 0; i < num; i++) {
            island[i] = new ArrayList<>();
        }

        for (int i = 1; i < island.length; i++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] == i) {
                        island[i].add(new Point(r, c, 0));
                    }
                }
            }
        }
    }

    private static void connectBridge(int r, int c, int currentNumber) {
        Queue<Point> q = new LinkedList<>();
        visit = new boolean[N][N];
        q.offer(new Point(r, c, 0));
        visit[r][c] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            r = p.r;
            c = p.c;
            int bridgeLength = p.length;

            if (map[r][c] != 0 && map[r][c] != currentNumber) {
                ans = Math.min(ans, bridgeLength - 1);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (0 > nr || nr >= N || 0 > nc || nc >= N || visit[nr][nc]) continue;
                if (map[nr][nc] == currentNumber) continue;

                visit[nr][nc] = true;
                q.offer(new Point(nr, nc, bridgeLength + 1));
            }
        }
    }
}