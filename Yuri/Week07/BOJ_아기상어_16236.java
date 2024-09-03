import java.io.*;
import java.util.*;

public class Main {

    static int n, map[][], time, sharkSize, fishNum, eatCnt;
    static boolean visited[][];
    static Point shark;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    static class Point implements Comparable<Point> {
        int r, c, cnt;
        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            if(cnt != o.cnt)
                return this.cnt - o.cnt;
            else if(r != o.r)
                return this.r - o.r;
            else
                return this.c - o.c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        fishNum = 0;
        time = 0;
        sharkSize = 2;
        eatCnt = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9)
                    shark = new Point(i, j, 0);
                if(map[i][j] > 0)
                    fishNum++;
            }
        }

        if(fishNum > 0)
            while(bfs()) {}  // 물고기를 먹었을 때만 반복.

        // 처음에 물고기가 아예 없으면 그냥 0이 출력됨.
        System.out.println(time);

    }

    public static boolean bfs() {
        visited = new boolean[n][n];
        Queue<Point> q = new LinkedList<>();
        q.add(shark);
        visited[shark.r][shark.c] = true;

        int max = 300;
        PriorityQueue<Point> pq = new PriorityQueue<>();

        while(!q.isEmpty()) {
            Point p = q.poll();

            if(p.cnt > max)
                break;

            // 거리가 같은 경우 위치에 따른 먹는 조건을 충족시키기 위해, 우선 물고기를 pq에 넣어 정렬.
            if(map[p.r][p.c] > 0 && map[p.r][p.c] < sharkSize) {
                pq.add(p);
                max = Math.min(max, p.cnt);
            }

            int moveR, moveC;
            for (int i = 0; i < 4; i++) {
                moveR = p.r + dr[i];
                moveC = p.c + dc[i];

                if (moveR >= 0 && moveC >= 0 && moveR < n && moveC < n && !visited[moveR][moveC] && map[moveR][moveC] <= sharkSize) {
                    // 맵 안이고, 방문한 적 없고, 물고기가 있는데 상어 사이즈보다 작거나 같으면
                    visited[moveR][moveC] = true;
                    q.add(new Point(moveR, moveC, p.cnt + 1));
                }
            }
        }

        if(!pq.isEmpty()) { // 상어가 먹을 수 있는 물고기가 있는 경우.
            // 물고기의 위치로 상어가 가고, 상어가 있던 자리는 0으로 바꿔야함.
            // shark 객체를 변경해야함.
            // 먹은 횟수를 증가시켜야함.
            // 만약 먹은 횟수가 현재 상어 크기와 같다면, 상어 크기를 +1하고 먹은 횟수를 초기화.
            // fishNum을 -1해야함. (하나 먹어서)
            // 시간을 업데이트 해야함.
            Point p = pq.poll();
            map[p.r][p.c] = 9;
            map[shark.r][shark.c] = 0;
            shark = new Point(p.r, p.c, 0);
            eatCnt++;
            time += p.cnt;
            if(eatCnt == sharkSize) {
                sharkSize++;
                eatCnt = 0;
            }
            fishNum--;
            return true;
        }

        return false;
    }
}
