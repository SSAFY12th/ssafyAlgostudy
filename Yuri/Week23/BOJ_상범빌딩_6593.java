import java.io.*;
import java.util.*;

public class Main {

    static int L, R, C;
    static char[][][] building;
    static boolean[][][] visited;
    static int result;
    static int[] dr = {1, -1, 0, 0, 0, 0};
    static int[] dc = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    static class Point {
        int z, r, c, cnt;
        public Point(int z, int r, int c, int cnt) {
            this.z = z;
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            String s;
            while ((s = br.readLine()).isEmpty()); // 빈 줄은 건너뛰도록 한다.
            st = new StringTokenizer(s);
            
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

          // 0 0 0이 입력되면 끝낸다.
            if(L == 0 && R == 0 && C == 0) break;

            result = 0;

            building = new char[L][R][C];
            Point start = null;

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    while ((s = br.readLine()).isEmpty());
                    for (int k = 0; k < C; k++) {
                        building[i][j][k] = s.charAt(k);
                        if(building[i][j][k] == 'S') start = new Point(i, j, k, 0);  // 시작 지점 저장
                    }
                }
            }

            bfs(start);

            if(result == 0) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + result + " minute(s).");
            }
        }
    }

    public static void bfs(Point start) {
        visited = new boolean[L][R][C];
        Queue<Point> q = new LinkedList<>();
        q.add(start);

        while(!q.isEmpty()) {
            Point now = q.poll();
            int newZ, newR, newC;

            for (int i = 0; i < 6; i++) {
                newZ = now.z + dz[i];
                newR = now.r + dr[i];
                newC = now.c + dc[i];

                if(newZ >= 0 && newR >= 0 && newC >= 0 && newZ < L && newR < R && newC < C && building[newZ][newR][newC] != '#' && !visited[newZ][newR][newC]) {
                    if(building[newZ][newR][newC] == 'E') {  // 끝나는 지점에 result를 업데이트 하고 bfs 종료
                        result = now.cnt+1;
                        return;
                    }
                    visited[newZ][newR][newC] = true;
                    q.add(new Point(newZ, newR, newC, now.cnt+1));
                }
            }
        }
    }
}
