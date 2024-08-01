import java.io.*;
import java.util.*;

public class Main {
    public static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static Queue<Point> queue = new LinkedList<>();
    public static int N;
    public static int M;
    public static char[][] arr;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static int find(int r, int c, char alpha) {
        int cnt = 1;
        queue.offer(new Point(r, c));
        arr[r][c] = '0'; // 여기서만 arr[r][c]를 0으로 설정
        while (!queue.isEmpty()) {
            Point temp = queue.poll();
            int x = temp.r;
            int y = temp.c;
            for (int u = 0; u < 4; u++) {
                int nx = x + dx[u];
                int ny = y + dy[u];
                if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                    if (arr[nx][ny] == alpha) {
                        arr[nx][ny] = '0';
                        cnt++;
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }
        cnt = cnt * cnt;

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[M][N];
        int sumW = 0;
        int sumB = 0;

        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = line.charAt(j);
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 'W') {
                    sumW += find(i, j, 'W');
                } else if (arr[i][j] == 'B') {
                    sumB += find(i, j, 'B');
                }
            }
        }
        System.out.println(sumW + " " + sumB);
    }
}
