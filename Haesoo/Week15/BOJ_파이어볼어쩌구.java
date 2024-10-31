import java.util.*;
import java.io.*;

public class BOJ_파이어볼 {
    public static void main(String[] args) throws IOException {
        int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] even = { 0, 2, 4, 6 };
        int[] odd = { 1, 3, 5, 7 };
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List <ball> balls = new ArrayList<>();
        Queue <Integer>[][] map = new Queue[50][50];
        Queue <Integer>[][] temp = new Queue[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                map[i][j] = new ArrayDeque<>();
                temp[i][j] = new ArrayDeque<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            balls.add(new ball(a, b, c, d, e));
            map[a][b].add(i);
        }
        int ans = 0;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < balls.size(); j++) {
                if (balls.get(j).mass == -1) continue;
                if (!map[balls.get(j).y][balls.get(j).x].isEmpty()) map[balls.get(j).y][balls.get(j).x].poll();
                int curdir = balls.get(j).dir;
                int ny = balls.get(j).y + dy[curdir] * balls.get(j).speed;
                int nx = balls.get(j).x + dx[curdir] * balls.get(j).speed;
                //map[ny % N][nx % N].push(balls[j]);

                ny = ny >= 0 ? ny % N : (N - Math.abs(ny));
                nx = nx >= 0 ? nx % N : (N - Math.abs(nx));

                //cout << j << "의 다음 좌표 : " << ny << ", " << nx << endl;
                map[ny][nx].add(j + 1);
                balls.get(j).y = ny;
                balls.get(j).x = nx;
            }
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    int curSize = map[j][k].size();
                    if (curSize > 1) {
                        //cout << ++cnt << "-------\n ";
                        int totalMass = 0;
                        int totalSpeed = 0;
                        int totalDir = 0;
                        while (!map[j][k].isEmpty()) {
                            int curBall = map[j][k].peek() - 1;
                            map[j][k].poll();
                            totalMass += balls.get(curBall).mass;
                            totalSpeed += balls.get(curBall).speed;
                            totalDir += balls.get(curBall).dir;
                            balls.get(curBall).mass = -1;
                            //balls.erase(balls.begin() + Cur, balls.begin() + curBall);
                        }
                        totalMass = (int)Math.ceil(totalMass / 5);

                        totalSpeed = (int)Math.ceil(totalSpeed / curSize);
                        totalDir = totalDir % 2;
                        //cout << totalMass << " " << totalSpeed << " " << totalDir << endl;
                        for (int q = 0; q < N; q++) {
                            for (int w = 0; w < N; w++) {
                                temp[q][w] = map[q][w];
                            }
                        }
                        for (int l = 0; l < 4; l++) {
                            int nextDir;
                            if (totalDir == 0) nextDir = even[l];
                            else nextDir = odd[l];
                            //int ny = j + dy[nextDir] * totalSpeed;
                            //int nx = k + dx[nextDir] * totalSpeed;

                            //ny = ny >= 0 ? ny % N : (N - abs(ny));
                            //nx = nx >= 0 ? nx % N : (N - abs(nx));
                            //cout << l << "번째 확산 파이어볼 : " << j << " " << k << endl;
                            balls.add(new ball(j, k, totalMass, totalSpeed, nextDir));
                            temp[j][k].add(balls.size() - 1);
                        }
                    }
                }
            }
            for (int z = 0; z < balls.size(); z++) {
                //cout << z << ": " << balls[z].y << " " << balls[z].x << " " << balls[z].mass << " " << balls[z].dir << endl;
            }
            for (int q = 0; q < N; q++) {
                for (int w = 0; w < N; w++) {
                    if (temp[q][w].size() != 0) {
                        map[q][w] = temp[q][w];
                    }
                    if (temp[q][w].size() != 0) {
                        //	cout << balls[temp[q][w].front()].mass << " ";
                        //ans += balls[temp[q][w].front()].mass;
                    }
                    //else cout << "0 ";
                }
                //cout << endl;
            }
        }
        for (int a = 0; a < balls.size(); a++) {
            if (balls.get(a).mass != -1) ans += balls.get(a).mass;
        }
        System.out.println(ans);


    }
    public static class ball {
        int y, x, mass, speed, dir;
        ball (int a, int b, int c, int d, int e) {
            this.y = a;
            this.x = b;
            this.mass = c;
            this.speed = d;
            this.dir = e;
        }
    }
}
