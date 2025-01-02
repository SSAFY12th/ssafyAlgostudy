import java.util.*;
import java.io.*;

public class Main {

    static int h, w;
    static char[][] map;
    static boolean[][] visited;
    static StringBuilder finalRoute = new StringBuilder();

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};
    static char[] startDir = {'<', 'v', '>', '^'};

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        visited = new boolean[h][w];

        map = new char[h][w];

        for (int i = 0; i < h; i++) {
            String s = br.readLine();
            for (int j = 0; j < w; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == '#') count++;
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if(map[i][j] == '#') {
                    for (int k = 0; k < 4; k++) {
                        visited[i][j] = true;

                        StringBuilder route = new StringBuilder();
                        route.append(i+1).append(" ").append(j+1).append("\n");
                        route.append(startDir[k]).append("\n");

                        dfs(1, i, j, k, route, 0);

                        visited[i][j] = false;

                    }
                }
            }
        }

        // 출력
        System.out.println(finalRoute.toString());
    }

    public static void dfs(int cnt, int r, int c, int dir, StringBuilder route, int turn) {
        if (finalRoute.length() != 0 && finalRoute.length() <= route.length()) {
            return;
        }

        if(turn >= 3)
            return;

        if(cnt >= count) {
            // 경로 최적화
            finalRoute = route;
            return;
        }

        StringBuilder tmp;

        if(canA(r, c, dir)) {
            // 직진하고 dfs 또 호출할 것.
            // visited 주의.
            int newR = r+dr[dir]*2;
            int newC = c+dc[dir]*2;

            visited[r+dr[dir]][c+dc[dir]] = true;
            visited[newR][newC] = true;

            tmp = new StringBuilder(route.toString());
            tmp.append('A');

            dfs(cnt+2, newR, newC, dir, tmp, 0);

            visited[r+dr[dir]][c+dc[dir]] = false;
            visited[newR][newC] = false;
        }

        // L
        int newDir = (dir+1) % 4;
        tmp = new StringBuilder(route.toString());
        tmp.append('L');
        dfs(cnt, r, c, newDir, tmp, turn+1);

        // R
        newDir = (dir+3) % 4;
        tmp = new StringBuilder(route.toString());
        tmp.append('R');
        dfs(cnt, r, c, newDir, tmp, turn+1);



    }

    public static boolean canA(int r, int c, int dir) {
        int newR = r;
        int newC = c;

        for (int i = 0; i < 2; i++) {
            newR += dr[dir];
            newC += dc[dir];

            if(newR >= 0 && newC >= 0 && newR < h && newC < w && map[newR][newC] == '#' && !visited[newR][newC])
                continue;
            else
                return false;
        }

        return true;

    }

}
