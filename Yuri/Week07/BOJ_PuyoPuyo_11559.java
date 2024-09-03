import java.io.*;
import java.util.*;

public class Main {

    static final int R = 12;
    static final int C = 6;
    static char[][] map = new char[R][C];
    static boolean[][] visited;
    static int boomCnt = 0;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1 ,1};
    static List<Point> puyo = new ArrayList<>();

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        while(boom()) {}

        System.out.println(boomCnt);
    }

    public static boolean boom() {
        visited = new boolean[R][C];
        boolean doBoom = false;

        // 밑에서부터 탐색.
        // 이전에 탐색한 색깔은 저장해 둘 필요 없음.
        // 색깔 하나로 사방탐색을 계속 해가며 visited에 표시.
        // 만약 연결된 것이 4개가 넘어가면 연쇄로 터트린다. - 리스트에 넣어서 확인하자.
        // 색깔이 바뀔 때마다 puyo를 clear하는 것을 잊으면 안됨.

        for (int i = R-1; i >= 0 ; i--) {
            for (int j = C-1; j >= 0 ; j--) {
                if(!visited[i][j] && map[i][j] != '.')
                    if(bfs(new Point(i, j), map[i][j]))
                        doBoom = true;
            }
        }

        if(doBoom) {
            boomCnt++;
            down();
        }

        return doBoom;
    }

    public static boolean bfs(Point s, char color) {
        Queue<Point> q = new LinkedList<>();
        puyo.clear();
        q.add(s);
        puyo.add(s);
        visited[s.r][s.c] = true;

        while(!q.isEmpty()) {
            Point p = q.poll();
            int moveR, moveC;

            for (int i = 0; i < 4; i++) {
                moveR = p.r+dr[i];
                moveC = p.c+dc[i];

                if(moveR >= 0 && moveC >= 0 && moveR < R && moveC < C && map[moveR][moveC] == color && !visited[moveR][moveC]) {
                    Point newP = new Point(moveR, moveC);
                    q.add(newP);
                    puyo.add(newP);
                    visited[moveR][moveC] = true;
                }
            }
        }

        if(puyo.size() > 3) {   // 4개 이상의 블럭이 붙어있으면 터트리기
            for (Point p : puyo)
                map[p.r][p.c] = '.';
            return true;    // 터트리는 것에 성공.
        }

        return false;       // 터트리는 것에 실패.
    }

    // 열마다 내리기
    public static void down() {
        for (int i = 0; i < C; i++) 
            down(i);
    }

    // 투 포인터를 활용하여 down.
    public static void down(int c) {
        int index = R-1;

        for (int i = R-1; i >= 0 ; i--) {
            if(map[i][c] != '.')
                map[index--][c] = map[i][c];
        }

        while(index >= 0) {
            map[index--][c] = '.';
        }
    }
}
