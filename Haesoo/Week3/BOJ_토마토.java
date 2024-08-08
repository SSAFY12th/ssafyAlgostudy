import java.util.*;
import java.io.*;

public class BOJ7576 {
    static int N, M;
    public static int[] dy = {0, 0, -1, 1};
    public static int[] dx = {-1, 1, 0, 0};
    public static int[][] map;
    //public static List <int[]> tomato = new ArrayList<>();
    public static Queue<int[]> tomato = new LinkedList<>();
    public static int[] pair = new int [2];
    public static boolean range (int y, int x) {
        return y >= 0 && x >= 0 && y < M && x < N;
    }
    static int day = 0;
    public static void bfs() {
        while (!tomato.isEmpty()) {
            int[]idx = tomato.poll();
            int y = idx[0];
            int x = idx[1];
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + y;
                int nx = dx[i] + x;
                if (range(ny, nx) && map[ny][nx] == 0) {
                    map[ny][nx] = map[y][x] + 1;
                    tomato.offer(new int[] {ny, nx});
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int [M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) tomato.offer(new int[]{i, j});
            }
        } // 입력
        if (tomato.size() == N * M) {
            System.out.println("0");
            return;
        }
        bfs ();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    System.out.println("-1");
                    return;
                }
                day = Math.max(day, map[i][j]);
            }
        }
        System.out.println(day - 1);
    }
}
