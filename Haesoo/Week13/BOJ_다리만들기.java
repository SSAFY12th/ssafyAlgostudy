import java.io.*;
import java.util.*;
public class BOJ2146 {
    public static int N, idx = 1, minAns = 10000;
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1 , 1};
    public static int[][] map = new int[101][101];
    public static int[][] tempMap = new int[101][101];
    public static boolean[][] visited = new boolean[101][101];
    public static Queue<pair> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Queue<pair>[] queues = new Queue[10000];
        for (int i = 0; i < 10000; i++) {
            queues[i] = new LinkedList<>();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    q = new LinkedList<>();
                    tempMap[i][j] = idx;
                    labeling(i, j);
                    queues[idx] = q;
                    idx++;
                }
            }
        }
        for (int i = 1; i < idx; i++) {
            visited = new boolean[N][N];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (tempMap[j][k] == i) visited[j][k] = true;
                }
            }
            int curmin = findMin(queues[i], i);
            minAns = Math.min(minAns, curmin);
        }
        System.out.println(minAns);
    }
    public static void labeling(int y, int x) {
        q.offer(new pair(y, x));
        tempMap[y][x] = idx;
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (range(ny, nx))
                if (!visited[ny][nx] && map[ny][nx] == 1) labeling(ny, nx);
        }
    }
    public static int findMin(Queue<pair> q, int idx) {
        int distance = 0;
        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                int cy = q.peek().y;
                int cx = q.peek().x;
                q.poll();
                for (int j = 0; j < 4; j++) {
                    int ny = cy + dy[j];
                    int nx = cx + dx[j];
                    if (range(ny, nx)) {
                        if (!visited[ny][nx]) {
                            if (tempMap[ny][nx] == 0) {
                                visited[ny][nx] = true;
                                q.offer(new pair(ny, nx));
                            }
                            if (map[ny][nx] == 1 && tempMap[ny][nx] != idx) return distance;
                        }
                    }
                }
            }
            distance++;
        }
        return distance;
    }
    public static boolean range(int ny, int nx) {
        return ny >= 0 && nx >= 0 && ny < N && nx < N;
    }
    static class pair {
        int y, x;
        pair (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
