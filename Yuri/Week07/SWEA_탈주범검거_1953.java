import java.util.*;
import java.io.*;

public class Solution {

    static int[][] map;
    static boolean[][] visitied;
    static int n, m, r, c, l, result;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<Point> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());   // 행의 개수
            m = Integer.parseInt(st.nextToken());   // 열의 개수
            r = Integer.parseInt(st.nextToken());   // 맨홀 위치 r
            c = Integer.parseInt(st.nextToken());   // 맨홀 위치 c
            l = Integer.parseInt(st.nextToken());   // 탈출 후 소요시간 l
            result = 0;                             // 갈 수 있는 파이프 개수
            map = new int[n][m];
            visitied = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs(new Point(r, c, 0));
            System.out.println("#"+test_case+" "+result);

        }
    }

    public static void bfs(Point start) {
        q.clear();
        q.add(start);
        visitied[start.r][start.c] = true;

        while(!q.isEmpty()) {
            Point p = q.poll();

            if(p.cnt == l)  // 현재 위치까지 오는데 소요된 시간이 l과 같다면 break;
                break;

            result++;

            moveDir(p, map[p.r][p.c]);  // 파이프 모양에 따라 움직일 수 있는 방향 탐색

        }
    }

    public static boolean canGo(int moveR, int moveC) {
        return moveR >= 0 && moveC >= 0 && moveR < n && moveC < m && map[moveR][moveC] != 0 && !visitied[moveR][moveC];
    }

    public static void moveDir(Point p, int pipe) {
        switch (pipe) {
            case 1: // 십자모양 파이프
                move(p, 0); // 상하좌우 전부 이동 가능.
                move(p, 1);
                move(p, 2);
                move(p, 3);
                return;
            case 2: // 세로 막대 모양 파이프
                move(p, 0);
                move(p, 1);
                return;
            case 3: // 가로 막대 모양 파이프
                move(p, 2);
                move(p, 3);
                return;
            case 4: // ㄴ 모양 파이프
                move(p, 0);
                move(p, 3);
                return;
            case 5: // 하, 우에 연결되는 모양 파이프
                move(p, 1);
                move(p, 3);
                return;
            case 6: // ㄱ 모양 파이프
                move(p, 1);
                move(p, 2);
                return;
            case 7: // 상, 좌에 연결되는 모양 파이프
                move(p, 0);
                move(p, 2);
                return;
        }
    }

    public static void move(Point p, int dir) {
        // 방향에 맞춰 이동
        int moveR = p.r+dr[dir];
        int moveC = p.c+dc[dir];
        // 이동하려는 곳이 갈 수 있고, 해당 파이프 모양이 방향과 맞다면
        if(canGo(moveR, moveC) && pipeDirCheck(dir, map[moveR][moveC])) {
            // 큐에 추가.
            q.add(new Point(moveR, moveC, p.cnt+1));
            visitied[moveR][moveC] = true;
        }
    }

    public static boolean pipeDirCheck(int nowDir, int newPipe) {
        switch (nowDir) {
            case 0: // 위로 이동할 때 가능한 파이프 모양 1, 2, 5, 6
                if(newPipe == 1 || newPipe == 2 || newPipe == 5 || newPipe == 6)
                    return true;
                break;
            case 1: // 아래로 이동할 때 가능한 파이프 모양 1, 2, 4, 7
                if(newPipe == 1 || newPipe == 2 || newPipe == 4 || newPipe == 7)
                    return true;
                break;
            case 2: // 왼쪽으로 이동할 때 가능한 파이프 모양 1, 3, 4, 5
                if(newPipe == 1 || newPipe == 3 || newPipe == 4 || newPipe == 5)
                    return true;
                break;
            case 3: // 오른쪽으로 이동할 때 가능한 파이프 모양 1, 3, 6, 7
                if(newPipe == 1 || newPipe == 3 || newPipe == 6 || newPipe == 7)
                    return true;
                break;
        }
        return false;
    }
}

class Point {
    int r, c, cnt;
    public Point(int r, int c, int cnt) {
        this.r = r;
        this.c = c;
        this.cnt = cnt;
    }
}
