import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

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

        int cnt = 0;

        while (true) {
            visited = new boolean[N][N];
            boolean changed = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        // 각 bfs 호출에서 연합을 구성하고 처리
                        List<Point> union = new ArrayList<>();
                        int unionSum = bfs(i, j, union);

                        if (union.size() > 1) {
                            changed = true;
                            int avg = unionSum / union.size();
                            for (Point p : union) {
                                map[p.r][p.c] = avg;
                            }
                        }
                    }
                }
            }

            if (!changed) break; // 더 이상 열릴 연합이 없다면 종료
            cnt++;
        }

        System.out.println(cnt);
    }

    private static int bfs(int r, int c, List<Point> union) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));
        visited[r][c] = true;

        int unionSum = 0;
        union.add(new Point(r, c));
        unionSum += map[r][c];

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                    int diff = Math.abs(map[p.r][p.c] - map[nr][nc]);
                    if (diff >= L && diff <= R) {
                        visited[nr][nc] = true;
                        q.offer(new Point(nr, nc));
                        union.add(new Point(nr, nc));
                        unionSum += map[nr][nc];
                    }
                }
            }
        }

        return unionSum; // 연합의 총 인구수 반환
    }
}
