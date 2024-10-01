import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int result = 0;
    static int[][] map;
    static int[] parents;
    static int landNum = 1;
    static List<Edge> edges = new ArrayList<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};


    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from, to, weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) map[i][j] = -1;  // 맵은 전부 -1로 저장
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == -1) {
                    findLand(i, j, landNum++);  // 섬에 번호 매기기
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] > 0) {
                    makeBridge(i, j);   // 연결 가능한 다리 만들기
                }
            }
        }

        make();

        Collections.sort(edges);
        for (Edge e : edges) {  // 크루스칼
            if(union(e.from, e.to)) {
                result += e.weight;
            }
        }

        int last = find(1);
        for (int i = 2; i < parents.length; i++) {
            if(last != find(i)) {
                result = -1;
                break;
            }
        }

        System.out.println(result);

    }

    public static void makeBridge(int r, int c) {
        // 상하좌우로 움직이면서 0의 개수세기.

        for (int i = 0; i < 4; i++) {
            // 벽에 부딫치거나, 다른 섬을 만날 때까지.
            int newR = r;
            int newC = c;
            int cnt = 0;
            while(true) {
                newR += dr[i];
                newC += dc[i];
                if(newR >= 0 && newC >= 0 && newR < N && newC < M) { // 맵 안에 있을 때
                    if(map[newR][newC] == 0)
                        cnt++;
                    else {
                        if(cnt > 1)
                            edges.add(new Edge(map[r][c], map[newR][newC], cnt));
                        break;
                    }
                } else break;
            }
        }
    }

    public static void findLand(int r, int c, int index) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));
        map[r][c] = index;

        // 연결되어있는 섬 찾기
        while(!q.isEmpty()) {
           Point p = q.poll();

            int newR, newC;
            for (int i = 0; i < 4; i++) {
                newR = p.r+dr[i];
                newC = p.c+dc[i];

                if(newR >= 0 && newC >= 0 && newR < N && newC < M && map[newR][newC] == -1) {
                    q.add(new Point(newR, newC));
                    map[newR][newC] = index;
                }
            }
        }
    }

    public static void make() {
        parents = new int[landNum];
        for (int i = 0; i < landNum; i++) {
            parents[i] = i;
        }
    }

    public static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
}
