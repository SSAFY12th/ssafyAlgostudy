import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static Pipe pipe; // 방향은 가로: 0, 세로: 1, 대각선: 2
    static int[][] map;
    static int[][] visitCnt;
    static int[] dr = {0, 1, 1};    // 오른쪽, 아래, 대각선 순
    static int[] dc = {1, 0, 1};

    static class Pipe {
        int sr, sc, dir;

        public Pipe(int sr, int sc, int dir) {
            this.sr = sr;
            this.sc = sc;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        visitCnt = new int[n][n];
        map = new int[n][n];
        pipe = new Pipe(0, 1, 0);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(pipe);

        System.out.println(visitCnt[n-1][n-1]);
    }

    public static void dfs(Pipe pi) {

        if(pi.dir != 1) {  // 세로만 아니면 가로 방향 체크
            int moveR = pi.sr + dr[0];
            int moveC = pi.sc + dc[0];
            if(moveR >= 0 && moveC >= 0 && moveR < n && moveC <n && map[moveR][moveC] == 0) { // 오른쪽으로 이동 가능하면?
                visitCnt[moveR][moveC]++;
                dfs(new Pipe(moveR, moveC, 0)); // 가로로 한 칸 이동
            }
        }
        if(pi.dir != 0) { // 가로만 아니면 세로 방향 체크
            int moveR = pi.sr + dr[1];
            int moveC = pi.sc + dc[1];
            if(moveR >= 0 && moveC >= 0 && moveR < n && moveC <n && map[moveR][moveC] == 0) { // 오른쪽으로 이동 가능하면?
                visitCnt[moveR][moveC]++;
                dfs(new Pipe(moveR, moveC, 1)); // 아래로 한 칸 이동
            }
        }

        // 대각선은 무조건 체크
        boolean canGo = true;
        for (int i = 0; i < 3; i++) {
            int moveR = pi.sr + dr[i];
            int moveC = pi.sc + dc[i];
            if(moveR >= 0 && moveC >= 0 && moveR < n && moveC <n && map[moveR][moveC] == 0) {}
            else {
                canGo = false;
                break;
            }
        }

        if(canGo) {
            int moveR = pi.sr + dr[2];
            int moveC = pi.sc + dc[2];
            visitCnt[moveR][moveC]++;
            dfs(new Pipe(moveR, moveC, 2));
        }
    }
}
