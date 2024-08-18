import java.util.*;
import java.io.*;

public class Main {

    static int k, w, h;
    static int result = Integer.MAX_VALUE;
    static int[][] map = new int[200][200];
    static boolean[][][] visited = new boolean[200][200][40];
    static StringTokenizer st;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[] jumpr = {-2, -2, -1, 1, 2, 2, 1, -1};  // 점프할 때 r의 이동
    static int[] jumpc = {-1, 1, 2, 2, 1, -1, -2, -2};  // 점프할 때 c의 이동

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        for(int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(new Point(0, 0, k, 0));

        if(result == Integer.MAX_VALUE)
            result = -1;

        System.out.println(result);
    }

    public static void bfs(Point tmpP) {
        Queue<Point> q = new LinkedList<>();
        q.add(tmpP);

        visited[0][0][tmpP.jump] = true;


        while(!q.isEmpty()) {
            Point p = q.poll();

            if(p.r == h-1 && p.c == w-1) {
                result = p.cnt;
                return;
            }

            for(int i = 0; i < 4; i++) {
                int mr = p.r+dr[i];
                int mc = p.c+dc[i];
                if(mr >= 0 && mr < h && mc >= 0 && mc < w && map[mr][mc] == 0 && !visited[mr][mc][p.jump]) {    // 일반 이동. 맵 안이면
                    visited[mr][mc][p.jump] = true;
                    q.add(new Point(mr, p.c+dc[i], p.jump, p.cnt+1));
                }
            }

            if(p.jump > 0) {
                for(int i = 0; i < 8; i++) {
                    int jr = p.r + jumpr[i];
                    int jc = p.c + jumpc[i];
                    if(jr >= 0 && jr < h && jc >= 0 && jc < w && map[jr][jc] == 0 && !visited[jr][jc][p.jump - 1]) {    // 맵 안이면
                        visited[jr][jc][p.jump-1] = true;
                        q.add(new Point(jr, jc, p.jump-1, p.cnt+1));
                    }
                }
            }
        }

    }
}

class Point {
    int r, c, jump, cnt;
    public Point(int r, int c, int jump, int cnt) {
        this.r = r;
        this.c = c;
        this.jump = jump;
        this.cnt = cnt;
    }
}
