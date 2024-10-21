import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static char[][] map;
    static int result = 11;
    static boolean finish = false;

    static class Ball {
        int r, c;
        char me;
        boolean goal;
        public Ball(int r, int c, char me, boolean goal) {
            this.r = r;
            this.c = c;
            this.me = me;
            this.goal = goal;
        }
        public Ball(Ball b) {
            this.r = b.r;
            this.c = b.c;
            this.me = b.me;
            goal = b.goal;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        Ball R = null;
        Ball B = null;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'R') R = new Ball(i, j, 'R', false);
                if(map[i][j] == 'B') B = new Ball(i, j, 'B', false);
            }
        }

        dfs(0, map, R, B);

        if(result > 10) result = 0;
        else result = 1;
        System.out.println(result);

    }

    public static void dfs(int cnt, char[][] map, Ball R, Ball B) {
        if(finish)
            return;
        if(cnt >= result)
            return;
        if(R.goal) {
            if(!B.goal) {
                result = Math.min(result, cnt);
                finish = true;
            }
            else result = Math.min(result, 11);
            return;
        }
        char[][] copyMap = new char[N][M];
        Ball copyR = null;
        Ball copyB = null;

        for (int i = 0; i < N; i++)
            copyMap[i] = map[i].clone();
        copyR = new Ball(R);
        copyB = new Ball(B);


        if(copyR.c > copyB.c) {
            right(copyR, copyMap);
            right(copyB, copyMap);
        }
        else {
            right(copyB, copyMap);
            right(copyR, copyMap);
        }
        dfs(cnt+1, copyMap, copyR, copyB);

        for (int i = 0; i < N; i++)
            copyMap[i] = map[i].clone();
        copyR = new Ball(R);
        copyB = new Ball(B);

        if(copyR.c < copyB.c) {
            left(copyR, copyMap);
            left(copyB, copyMap);
        }
        else {
            left(copyB, copyMap);
            left(copyR, copyMap);
        }
        dfs(cnt+1, copyMap, copyR, copyB);

        for (int i = 0; i < N; i++)
            copyMap[i] = map[i].clone();
        copyR = new Ball(R);
        copyB = new Ball(B);

        if(copyR.r < copyB.r) {
            up(copyR, copyMap);
            up(copyB, copyMap);
        }
        else {
            up(copyB, copyMap);
            up(copyR, copyMap);
        }
        dfs(cnt+1, copyMap, copyR, copyB);

        for (int i = 0; i < N; i++)
            copyMap[i] = map[i].clone();
        copyR = new Ball(R);
        copyB = new Ball(B);

        if(copyR.r > copyB.r) {
            down(copyR, copyMap);
            down(copyB, copyMap);
        }
        else {
            down(copyB, copyMap);
            down(copyR, copyMap);
        }
        dfs(cnt+1, copyMap, copyR, copyB);
    }


    public static void up(Ball b, char[][] copyMap) {
        copyMap[b.r][b.c] = '.';
        for (int i = b.r-1; i >= 0 ; i--) {
            if(copyMap[i][b.c] == 'O') {
                b.goal = true;
                break;
            }
            else if(copyMap[i][b.c] != '.') {
                b.r = i+1;
                copyMap[b.r][b.c] = b.me;
                break;
            }
        }
    }

    public static void down(Ball b, char[][] copyMap) {
        copyMap[b.r][b.c] = '.';
        for (int i = b.r +1; i < N; i++) {
            if (copyMap[i][b.c] == 'O') {
                b.goal = true;
                break;
            } else if (copyMap[i][b.c] != '.') {
                b.r = i - 1;
                copyMap[b.r][b.c] = b.me;
                break;
            }
        }
    }

    public static void left(Ball b, char[][] copyMap) {
        copyMap[b.r][b.c] = '.';
        for (int i = b.c-1; i >= 0 ; i--) {
            if(copyMap[b.r][i] == 'O') {
                b.goal = true;
                break;
            }
            else if(copyMap[b.r][i] != '.') {
                b.c = i+1;
                copyMap[b.r][b.c] = b.me;
                break;
            }
        }
    }

    public static void right(Ball b, char[][] copyMap) {
        copyMap[b.r][b.c] = '.';
        for (int i = b.c+1; i < M ; i++) {
            if(copyMap[b.r][i] == 'O') {
                b.goal = true;
                break;
            }
            else if(copyMap[b.r][i] != '.') {
                b.c = i-1;
                copyMap[b.r][i-1] = b.me;
                break;
            }
        }
    }

    public static void printMap(char[][] map, String mes, int cnt) {
        System.out.println(mes+": "+" cnt: "+cnt);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
