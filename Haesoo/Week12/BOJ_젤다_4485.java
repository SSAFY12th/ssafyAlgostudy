import java.io.*;
import java.util.*;
public class Main {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = 0;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            t++;
            int [][] cave = new int[N][N];
            int [][] cost = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(cost[i], 10000000);
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            pair curNode = new pair(0, 0, cave[0][0]);
            PriorityQueue<pair> pq = new PriorityQueue<>(new Comparator<pair>() {
                @Override
                public int compare(pair o1, pair o2) {
                    return o1.val - o2.val;
                }
            });
            cost[curNode.y][curNode.x] = curNode.val;
            pq.add(curNode);
            while(!pq.isEmpty()) {
                curNode = new pair(pq.peek().y, pq.peek().x, pq.peek().val);
                pq.poll();
                if (curNode.val > cost[curNode.y][curNode.x]) continue;
                for (int i = 0; i < 4; i++) {
                    int nextY = curNode.y + dy[i];
                    int nextX = curNode.x + dx[i];
                    if (range(nextY, nextX)) {
                        int nextCost = cave[nextY][nextX];
                        if (cost[nextY][nextX] > nextCost + curNode.val) {
                            cost[nextY][nextX] = nextCost + curNode.val;
                            pq.add(new pair(nextY, nextX, cost[nextY][nextX]));
                        }
                    }
                }
            }
            sb.append("Problem ").append(t).append(": ").append(cost[N - 1][N - 1]).append("\n");
        }
        System.out.println(sb);
    }

    public static boolean range (int ny, int nx) {
        return ny >= 0 && nx >= 0 && ny < N && nx < N;
    }

    public static class pair {
        int y, x, val;
        pair (int y, int x, int val) {
            this.y = y;
            this.x = x;
            this.val = val;
        }
    }
}
