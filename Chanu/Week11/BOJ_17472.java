
import java.io.*;
import java.util.*;

public class Baek17472 {

    static int[] parents;
    static int n;
    static int m;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] dx ={0,1,0,-1};
    static int[] dy ={1,0,-1,0};
    static int v;
    static int[][] map;
    static boolean[][] visited;
    static int num = 1;

    static class Node implements Comparable<Node>{
        int to;
        int from;
        int weight;

        public Node(int to, int from, int weight) {
            this.to = to;
            this.from = from;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "to=" + to +
                    ", from=" + from +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                int a =  Integer.parseInt(st.nextToken());
                if (a == 1) {
                    map[i][j] =-1;
                }
            }

        }

        int island = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (map[i][j] == -1) {
                    visited[i][j] = true;
                    map[i][j] = num;
                    bfs(i,j);
                    num++;
                    island++;
                }
            }

        }

        v = island;
        insertQueue();
        init();

        long result =0;
        int c = 0;

        while (!pq.isEmpty()) {

            Node node = pq.poll();
            int a = node.to;
            int b = node.from;
            int w = node.weight;

            if (union(a,b)) {

                result += w;
                c++;

                if (c == v-1) {
                    System.out.println(result);
                    return;
                }
            }


        }
        System.out.println(-1);
    }


    static void init() {
        parents = new int[v+1];
        for (int i=1; i<=v; i++) {
            parents[i] = i;
        }
    }

    static int findSet(int node) {
        if (node == parents[node]) {
            return node;
        }
        return parents[node] = findSet(parents[node]);
    }

    static boolean union(int a, int b) {
        int node1 = findSet(a);
        int node2 = findSet(b);

        if (node2 == node1) {
            return false;
        }

        parents[node2] = node1;
        return true;
    }


    static void bfs (int x , int y) {

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x,y});

        while (!queue.isEmpty()){
            int[] node = queue.poll();

            x = node[0];
            y = node[1];

            for (int i=0; i<4; i++) {

                int nx = x+dx[i];
                int ny = y+dy[i];

                if (nx >= n || nx < 0 || ny >=m || ny < 0 || visited[nx][ny] || map[nx][ny] == 0 ) {
                    continue;
                }

                visited[nx][ny] = true;
                map[nx][ny] = num;
                queue.offer(new int[]{nx,ny});

            }
        }
    }

    static void insertQueue() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {

                if (map[i][j] != 0) {
                    for (int z =0; z<4; z++) {
                        check(i,j,z);
                    }
                }

            }

        }

    }

    static void check (int i, int j, int d) {

        int self = map[i][j];
        int cnt = 0;

        int x = i;
        int y = j;

        while (true) {

            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx <0 || nx >=n || ny < 0 || ny >=m) {
                return;
            }

            if (map[nx][ny] == self) {
                return;
            }

            if (map[nx][ny] != self && map[nx][ny] != 0) {

                if (cnt >= 2) {
                    pq.offer(new Node(self,map[nx][ny],cnt));
                    return;
                }else {
                    return;
                }
            }

            x = nx;
            y = ny;
            cnt++;
        }

    }


}
