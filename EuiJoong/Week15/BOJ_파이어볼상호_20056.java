import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Fireball {

        int r;
        int c;
        int m;
        int s;
        int d;

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Fireball{" +
                    "r=" + r +
                    ", c=" + c +
                    ", m=" + m +
                    ", s=" + s +
                    ", d=" + d +
                    '}';
        }
    }

    static int[][] dir = {
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1}
    };

    static int N, M, K;
    static List<Fireball>[][] map, tmpMap;
    static int[] evenNum = {0, 2, 4, 6};
    static int[] oddNum = {1, 3, 5, 7};
    static int ans;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];
        tmpMap = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
                tmpMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[r][c].add(new Fireball(r, c, m, s, d));
        }

        for (int game = 0; game < K; game++) {
            //System.out.println("====" + (game + 1) + " 번째 턴====");

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tmpMap[i][j] = new ArrayList<>();
                }
            }

            //1이동
            moveFireBall();
            //2.파이어볼 합친후 분열
            spreadFireBall();
            //3.맵 복사
            copyMap();

        }

//        System.out.println("======게임이 끝나고 난 후 맵");
//        print(map);

        //파이어볼 질량 합치기
        ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (!map[i][j].isEmpty()) {

                    for (Fireball fireball : map[i][j]) {
                        ans += fireball.m;
                    }
                }
            }
        }

        System.out.println(ans);

    }

    //이동
    private static void moveFireBall() {

//        System.out.println("===현재 맵===");
//        print(map);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (!map[i][j].isEmpty()) {

                    for (Fireball fireball : map[i][j]) {

                        int nd = fireball.d;
                        int nm = fireball.m;
                        int ns = fireball.s;

                        int nr = (fireball.r + dir[nd][0] * ns) % N;
                        int nc = (fireball.c + dir[nd][1] * ns) % N;

                        if (nr < 0) nr += N;
                        if (nc < 0) nc += N;

                        tmpMap[nr][nc].add(new Fireball(nr, nc, nm, ns, nd));

                    }
                }
            }
        }

//        System.out.println("==== 파이어볼 이동 후 맵====");
//        print(tmpMap);

    }

    private static void spreadFireBall() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (tmpMap[i][j].size() >= 2) {

                    int sum_m = 0;
                    int sum_s = 0;

                    boolean oddNumber = true;
                    boolean evenNumber = true;

                    for (Fireball fireball : tmpMap[i][j]) {
                        sum_m += fireball.m;
                        sum_s += fireball.s;

                        if (fireball.d % 2 != 0) {
                            evenNumber = false;
                        }
                        if (fireball.d % 2 != 1) {
                            oddNumber = false;
                        }
                    }

                    int m = sum_m / 5;
                    int s = sum_s / tmpMap[i][j].size();

                    tmpMap[i][j].clear();

                    if (m == 0) {
                        continue;
                    }

                    if (oddNumber || evenNumber) {
                        for (int div = 0; div < 4; div++) {
                            tmpMap[i][j].add(new Fireball(i, j, m, s, evenNum[div]));
                        }
                    } else {
                        for (int div = 0; div < 4; div++) {
                            tmpMap[i][j].add(new Fireball(i, j, m, s, oddNum[div]));
                        }
                    }
                }
            }
        }
//        System.out.println("==분열 후 맵==");
//        print(tmpMap);
//        System.out.println();
    }

    private static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>(tmpMap[i][j]);
            }
        }
    }

    private static void print(List<Fireball>[][] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
