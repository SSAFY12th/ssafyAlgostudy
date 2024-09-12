import java.io.*;
import java.util.*;
public class Solution {
    static int N, cnt, T, maxcnt;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] v = new int[101][101];
    static List <List<pair>> holes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            maxcnt = 0;
            N = Integer.parseInt(br.readLine().trim());
            v = new int[N][N];
            holes = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                holes.add(new ArrayList<>());
            }
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    v[i][j] = Integer.parseInt(st.nextToken());
                    if (v[i][j] >= 6) {
                        pair p = new pair(i, j);
                        holes.get(v[i][j] - 6).add(p);
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (v[i][j] == 0) {
                        cnt = 0;
                        count(i, j, 0);
                        maxcnt = Math.max(maxcnt, cnt);
                        cnt = 0;
                        count(i, j, 1);
                        maxcnt = Math.max(maxcnt, cnt);
                        cnt = 0;
                        count(i, j, 2);
                        maxcnt = Math.max(maxcnt, cnt);
                        cnt = 0;
                        count(i, j, 3);
                        maxcnt = Math.max(maxcnt, cnt);
 
                    }
                }
            }
            System.out.println("#" + t + " " + maxcnt);
        }
    }
    static boolean range (int ny, int nx) {
        return ny >= 0 && nx >= 0 && ny < N && nx < N;
    }
    static void count (int starty, int startx, int startdir) {
        int cury = starty;
        int curx = startx;
        int dir = startdir;
        while (true) {
            cury = cury + dy[dir];
            curx = curx + dx[dir];
            if (!range(cury, curx)) { // 벽이면 방향 바꾸기
                if (dir == 0) dir = 1;
                else if (dir == 1) dir = 0;
                else if (dir == 2) dir = 3;
                else dir = 2;
                cnt++;
                cury = cury + dy[dir];
                curx = curx + dx[dir];
            }
            if (v[cury][curx] == -1) return; // 블랙홀 만나면 끝내기
            if (cury == starty && curx == startx) return;
 
            else if (v[cury][curx] >= 6) { // 같은 번호의 웜홀로 이동
                int holeval = v[cury][curx] - 6;
                if (holes.get(holeval).get(0).y == cury && holes.get(holeval).get(0).x == curx) {
                    cury = holes.get(holeval).get(1).y;
                    curx = holes.get(holeval).get(1).x;
                }
                else {
                    cury = holes.get(holeval).get(0).y;
                    curx = holes.get(holeval).get(0).x;
 
                }
            }
            else if (v[cury][curx] > 0 && v[cury][curx] < 6) {
                cnt++;
                if (v[cury][curx] == 1) {
                    if (dir == 0) dir = 1;
                    else if (dir == 1) dir = 3;
                    else if (dir == 2) dir = 0;
                    else dir = 2;
                }
                if (v[cury][curx] == 2) {
                    if (dir == 0) dir = 3;
                    else if (dir == 1) dir = 0;
                    else if (dir == 2) dir = 1;
                    else dir = 2;
                }
                if (v[cury][curx] == 3) {
                    if (dir == 0) dir = 2;
                    else if (dir == 1) dir = 0;
                    else if (dir == 2) dir = 3;
                    else dir = 1;
                }
                if (v[cury][curx] == 4) {
                    if (dir == 0) dir = 1;
                    else if (dir == 1) dir = 2;
                    else if (dir == 2) dir = 3;
                    else dir = 0;
                }
                if (v[cury][curx] == 5) {
                    if (dir == 0) dir = 1;
                    else if (dir == 1) dir = 0;
                    else if (dir == 2) dir = 3;
                    else dir = 2;
                }
            }
        }
    }
    static class pair {
        int y, x;
        pair (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
