
import java.io.*;
import java.util.*;

public class 녹색옷 {

    static int[][] map;
    static int n;
    static boolean[][] visited;
    static int[][] minArr;
    static int[] dx ={0,1,-1,0};
    static int[] dy ={1,0,0,-1};

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int money;

        public Node(int x, int y, int money) {
            this.x = x;
            this.y = y;
            this.money = money;
        }

        @Override
        public int compareTo(Node o) {
            return this.money - o.money;
        }
    }

    public static void main(String[] args) throws IOException {

        int a = 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());

            if (n == 0) {
                break;
            }

            map = new int[n][n];
            visited = new boolean[n][n];
            minArr = new int[n][n];

            for (int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("Problem "+a+": "+getMinTime(0,0,n-1,n-1));
            a++;

        }

    }

    static int getMinTime(int startX, int startY, int endX, int endY) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int inf = Integer.MAX_VALUE;

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                minArr[i][j] = inf;
            }
        }

        minArr[startX][startY] = map[0][0];
        visited[startX][startY] = true;
        pq.offer(new Node(startX,startY,minArr[startX][startY]));

        while (!pq.isEmpty()){
            Node node = pq.poll();
            int x = node.x;
            int y = node.y;
            int money = node.money;

            if (x ==endX && y == endY) {
                return minArr[endX][endY];
            }

            for (int i=0; i<4; i++) {

                int nx = x+dx[i];
                int ny = y+dy[i];

                if (nx >=0 && nx <n && ny >=0 && ny < n && !visited[nx][ny]
                && minArr[nx][ny] > money + map[nx][ny]) {
                    minArr[nx][ny] = money + map[nx][ny];
                    visited[nx][ny] = true;
                    pq.offer(new Node(nx,ny,minArr[nx][ny]));
                }
            }

        }

        return -1;
    }
}
