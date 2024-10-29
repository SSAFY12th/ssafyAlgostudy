import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;
    static List<FireBall>[][] map;
    static List<FireBall>[][] newMap;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    static class FireBall {
        int m, s, d;
        boolean moving;
        public FireBall(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
            moving = false;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map[r][c].add(new FireBall(m, s, d));
        }

        for (int i = 0; i < K; i++) {
            moveFireBall(); // 파이어볼 이동하기.
            happen(); // 2개 이상 있는 곳에서 일이 일어나기
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                while(!map[i][j].isEmpty()) {
                    // 파이어볼이 존재한다면 이동!
                    FireBall b = map[i][j].remove(0);
                    sum += b.m;
                }
            }
        }

        System.out.println(sum);

    }

    public static void moveFireBall() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                while(!map[i][j].isEmpty() && !map[i][j].get(0).moving) {
                    // 파이어볼이 존재한다면 이동!
                    FireBall b = map[i][j].remove(0);

                    int moveR = (i + dr[b.d] * b.s % N + N) % N;
                    int moveC = (j + dc[b.d] * b.s % N + N) % N;

                    b.moving = true;

                    map[moveR][moveC].add(b);
                }
            }
        }
    }

    public static void happen() {

        newMap = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j].size() > 0) {
                    if(map[i][j].size() > 1) {
                        // 2개 이상의 파이어볼이 있는 경우.
                        // 파이어볼은 먼저 합쳐져야 한다.
                        FireBall newFireBall = sumFireBall(i, j);
                        // 방향대로 나누어진다. (이동하는 것이 아님에 주의)
                        spread(i, j, newFireBall);
                    }
                    else {
                        newMap[i][j].add(map[i][j].get(0));
                        newMap[i][j].get(0).moving = false;
                    }
                }
            }
        }

        map = newMap;
    }

    public static FireBall sumFireBall(int r, int c) {
        FireBall newFireBall = new FireBall(0, 0, 0);
        int CNT = map[r][c].size();
        while(!map[r][c].isEmpty()) {
            FireBall b = map[r][c].remove(0);
            newFireBall.m += b.m;
            newFireBall.s += b.s;
            newFireBall.d += b.d % 2;
        }

        newFireBall.m = newFireBall.m/5;
        newFireBall.s = newFireBall.s/CNT;

        if(newFireBall.d % CNT == 0)   // 방향이 모두 짝수 혹은 홀수로 같음
            newFireBall.d = 0;
        else newFireBall.d = 1;

        return newFireBall;
    }

    public static void spread(int r, int c, FireBall fireBall) {
        if(fireBall.m == 0) return;

        int dir = fireBall.d;
        for (int i = 0; i < 4; i++) {
            newMap[r][c].add(new FireBall(fireBall.m, fireBall.s, dir));
            dir += 2;
        }
    }
}
