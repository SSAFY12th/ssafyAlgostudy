import java.util.*;
import java.io.*;
public class Solution {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N;
    static int[][] maps;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            maps = new int[N][N];
            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < N; j++) {
                    maps[i][j] = s.charAt(j) - '0';
                }
            }
            System.out.println("#" + t + " " + bfs(0, 0));
        }
    }
    static int bfs (int starty, int startx) {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = 100000;
            }
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{starty, startx});
        temp[0][0] = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (range(ny, nx)) {
                    int tempval = temp[y][x] + maps[ny][nx];
                    if (tempval < temp[ny][nx]) {
                        temp[ny][nx] = tempval;
                        q.offer(new int[]{ny, nx});
                    }
                }
            }
        }
        return temp[N - 1][N - 1];
    }
    static boolean range (int ny, int nx) {
        return ny >= 0 && nx >= 0 && ny < N && nx < N;
    }
}
