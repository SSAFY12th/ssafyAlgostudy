import java.io.*;
import java.util.*;
public class SWEA5644 {
    public static int M, A;
    public static int[] moveA, moveB;
    public static int[][] map = new int[10][10];
    public static BC[] chargers;
    public static int[] dy = {0, -1, 0, 1, 0};
    public static int[] dx = {0, 0, 1, 0, -1}; // X, 상, 우, 하, 좌
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            moveA = new int[M];
            moveB = new int[M];
            chargers = new BC[A];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                moveA[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                moveB[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int cy = Integer.parseInt(st.nextToken());
                int cx = Integer.parseInt(st.nextToken());
                int cr = Integer.parseInt(st.nextToken());
                int cp = Integer.parseInt(st.nextToken());
                BC bc = new BC(cx - 1, cy - 1, cr, cp);
                chargers[i] = bc;
            }
            // --- 입력 끝 ---
            int curAy = 0, curAx = 0;
            int curBy = 9, curBx = 9;
            int sum = 0, bSum = 0;
            int[][] distance = new int[2][A];
            for (int time = 0; time <= M; time++) {
                List <BC> posA = new ArrayList<>();
                List <BC> posB = new ArrayList<>();
                BC test = new BC(-1, -1, 0, 0);
                posA.add(test);
                posB.add(test);
                for (int j = 0; j < A; j++) {
                    distance[0][j] = Math.abs(chargers[j].y - curAy) + Math.abs(chargers[j].x - curAx);
                    if (distance[0][j] <= chargers[j].range) posA.add(chargers[j]);
                    distance[1][j] = Math.abs(chargers[j].y - curBy) + Math.abs(chargers[j].x - curBx);
                    if (distance[1][j] <= chargers[j].range) posB.add(chargers[j]);
                }
                int maxP = 0;
                Collections.sort(posA);
                Collections.sort(posB);
                if (posA.get(0) != posB.get(0)) { // 그냥 다르면 각각 가장 큰 걸로 더해준다
                    sum += posA.get(0).p;
                    sum += posB.get(0).p;
                }
                else if (posA == null) { // A에 아무 것도 없다
                    sum += posB.get(0).p;
                }
                else if (posB == null) { // B에 아무 것도 없다
                    sum += posA.get(0).p;
                }
                else {
                    if (posA.size() == 1 && posB.size() == 1 ) sum += posA.get(0).p; // 둘 다 1개씩이라면
                    else if (posA.size() > 1  && posB.size() > 1 ) { // 둘 다 2개 이상이라면
                        if (posA.get(1).p >= posB.get(1).p) {
                            sum += posA.get(1).p;
                            sum += posB.get(0).p;
                        }
                        else {
                            sum += posB.get(1).p;
                            sum += posA.get(0).p;
                        }
                    }
                    else if (posA.size() == 1 && posB.size() > 1) { // 둘 중 A는 원소가 1개
                        sum += posB.get(1).p;
                        sum += posA.get(0).p;
                    }
                    else if (posA.size() > 1 && posB.size() == 1) { // 둘 중 B는 원소가 1개
                        sum += posB.get(0).p;
                        sum += posA.get(1).p;
                    }
                }
                if (time == M) {
                    System.out.println("#" + t + " " + sum);
                    return;
                };
                curAy = curAy + dy[moveA[time]];
                curAx = curAx + dx[moveA[time]];
                curBy = curBy + dy[moveB[time]];
                curBx = curBx + dx[moveB[time]];
            }
        }
    }

    public static class BC implements Comparable<BC> {
        int y, x, range, p;
        BC(int y, int x, int range, int performance) {
            this.y = y;
            this.x = x;
            this.range = range;
            this.p = performance;
        }
        @Override
        public int compareTo(BC o) {
            if( this.p < o.p) return 1;
            return -1;
        }
    }
}
