import java.util.*;
import java.io.*;

public class Solution {

    static int t, n, k;
    static int[][] map = new int[8][8];
    static boolean[][] visited = new boolean[8][8];
    static int result = 0;
    static int maxHight = 0;
    static StringTokenizer st;
    static List<Point> startPoint = new ArrayList<>();
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= t; test_case++) {

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            result = 0;
            maxHight = 0;
            startPoint = new ArrayList<>();

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxHight = Math.max(maxHight, map[i][j]);
                }
            }

            // 가장 높은 지점들을 startPoint에 모아 저장.
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    if(map[i][j] == maxHight)
                        startPoint.add(new Point(i, j, false));

            // 각 지점에서부터 dfs 출발.
            for(Point p : startPoint) {
                visited[p.r][p.c] = true;
                dfs(p, 1);
                visited[p.r][p.c] = false;
            }

            System.out.println("#"+test_case+" "+result);

        }
    }

    public static void dfs(Point p, int cnt) {
        int r, c;
        for(int i = 0; i < 4; i++) {
            if(p.r+dr[i] >= 0 && p.r+dr[i] < n && p.c+dc[i] >= 0 && p.c+dc[i] < n) {    // 맵 내부일 때

                r = p.r+dr[i];
                c = p.c+dc[i];

                if(!visited[r][c]) {    // 방문 안한 경우에만
                    if(map[r][c] < map[p.r][p.c]) { // 원래보다 작은 경우
                        visited[r][c] = true;
                        dfs(new Point(r, c, p.change), cnt+1);
                        visited[r][c] = false;
                    }
                    else if(!p.change) {    // 원래보다 크지만 아직 높이를 조정하지 않은 경우.
                        int cal = (map[r][c]-(map[p.r][p.c]-1));
                        if(cal <= k) {    // 높이 조절로 원래보다 작아질 수 있는 경우
                            map[r][c] -= cal;
                            visited[r][c] = true;
                            dfs(new Point(r, c, true), cnt+1);
                            visited[r][c] = false;
                            map[r][c] += cal;
                        }
                    }
                }
            }
        }
        result = Math.max(result, cnt);
    }
}

class Point {
    int r, c;
    boolean change;
    public Point(int r, int c, boolean change) {
        this.r = r;
        this.c = c;
        this.change = change;
    }
}
