import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int result = 20;
    static char[][] map;
    static int[] dr = {-1, 1, 0 ,0};
    static int[] dc = {0, 0, -1, 1};

    static class Ball {
        int r, c;
        public Ball(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Status {
        Ball Blue, Red;
        int cnt;
        public Status(Ball R, Ball B, int cnt) {
            Blue = B;
            Red = R;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        Ball B = new Ball(0, 0);
        Ball R = new Ball(0, 0);

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'B') {
                    B.r = i;
                    B.c = j;
                }
                else if(map[i][j] == 'R') {
                    R.r = i;
                    R.c = j;
                }
            }
        }

        map[R.r][R.c] = '.';
        map[B.r][B.c] = '.';

        bfs(new Status(R, B, 0));
        
        if(result > 10) result = 0;
        else result = 1;

        System.out.println(result);

    }

    public static void bfs(Status status) {
        Queue<Status> q = new LinkedList<>();
        q.add(status);
        
        while(!q.isEmpty()) {
            Status st = q.poll();

            if(st.cnt == 10) continue;

            for (int i = 0; i < 4; i++) {
                int redR = st.Red.r;
                int redC = st.Red.c;
                int blueR = st.Blue.r;
                int blueC = st.Blue.c;

                boolean redFall = false;
                boolean blueFall = false;

                while(true) {
                    int nr = redR + dr[i];
                    int nc = redC + dc[i];

                    if(map[nr][nc] == '#')
                        break;
                    else if(map[nr][nc] == 'O') {
                        redFall = true;
                        break;
                    }

                    redR = nr;
                    redC = nc;

                }

                while(true) {
                    int nr = blueR + dr[i];
                    int nc = blueC + dc[i];
                    if(map[blueR][blueC] == '#') {
                        blueR -= dr[i];
                        blueC -= dc[i];
                        break;
                    }
                    else if(map[blueR][blueC] == 'O') {
                        blueFall = true;
                        break;
                    }

                    blueR = nr;
                    blueC = nc;
                }

                if(blueFall) continue;
                else if(redFall) {
                    result = st.cnt + 1;
                    return;
                }

                if(redR == st.Red.r && redC == st.Red.c && blueR == st.Blue.r && blueC == st.Blue.c) continue;

                if(redR == blueR && redC == blueC) {
                    if(st.Red.r > st.Blue.r) {  // 열이 같고 빨간 공이 더 아래에 있었을 때
                        if(i == 0)  redR++;
                        else if(i == 1) blueR--;
                    }
                    else if(st.Red.r < st.Blue.r) {
                        if(i == 0)  blueR++;
                        else if(i == 1) redR--;
                    }
                    else if(st.Red.c > st.Blue.c) { // 행이 같고 빨간 공이 더 오른쪽에 있었을 때
                        if(i == 2) redC++;
                        else if(i == 3) blueC--;
                    }
                    else {
                        if(i == 2) blueC++;
                        else if(i == 3) redC--;
                    }
                }

                q.add(new Status(new Ball(redR, redC), new Ball(blueR, blueC), st.cnt+1));

            }
        }
    }
}
