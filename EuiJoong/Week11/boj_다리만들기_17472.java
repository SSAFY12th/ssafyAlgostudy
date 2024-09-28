import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ")";
        }
    }

    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static List<Point>[] islands;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    //mst에 필요한 변수들
    static int[] p;
    static List<int[]> edgeList;
    static boolean[] checked;
    static int ans;


    public static void main(String[] args) throws IOException {

        //입력
        input();
        //번호부여
        int num = numbering();
        //연결성 확인
        edgeList = new ArrayList<>(); // 간선 그래프
        checked = new boolean[num];
        isConnected(num); // 연결성 확인되면 간선 그래프에 담기

        //MST
        if (edgeList.isEmpty()) { //간선이 없으면 -1
            ans = -1;
        } else {
            Collections.sort(edgeList, (o1, o2) -> o1[2] - o2[2]);
            makeSet(num);
            ans = kruskal();
        }

        System.out.println(ans);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int numbering() {

        islands = new ArrayList[7];

        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j] && map[i][j] != 0) {
                    islands[num] = new ArrayList<>();
                    bfs(i, j, num);
                    num++;
                }
            }
        }

        return num;
    }

    private static void bfs(int r, int c, int number) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c));
        map[r][c] = number;
        visit[r][c] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();
            r = point.r;
            c = point.c;
            islands[number].add(new Point(r, c));
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (0 > nr || nr >= N || 0 > nc || nc >= M || map[nr][nc] == 0 || visit[nr][nc]) continue;

                visit[nr][nc] = true;
                map[nr][nc] = number;
                q.offer(new Point(nr, nc));
            }
        }
    }

    private static void isConnected(int num) {
        for (int i = 1; i < num; i++) {
            for (int j = 0; j < islands[i].size(); j++) {
                Point point = islands[i].get(j);
                for (int d = 0; d < 4; d++) {
                    dfs(point.r, point.c, i, d, -1);
                }
            }
        }
    }

    private static void dfs(int r, int c, int number, int dir, int length) {

        if (map[r][c] != 0 && map[r][c] != number) {
            if (length >= 2) {
                edgeList.add(new int[] {number, map[r][c], length});
            }
            return;
        }

        int nr = r + dr[dir];
        int nc = c + dc[dir];

        if (0 > nr || nr >= N || 0 > nc || nc >= M || map[nr][nc] == number) return;

        dfs(nr, nc, number, dir, length + 1);
    }

    private static void makeSet(int num) {
        p = new int[num];
        for (int i = 1; i < num; i++) {
            p[i] = i;
        }
    }

    private static int find(int x) {
        if (p[x] == x) return x;

        return p[x] = find(p[x]);
    }

    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }

        if (rootX < rootY) {
            p[rootY] = rootX;
        }else {
            p[rootX] = rootY;
        }
    }

    private static int kruskal() {
        int cost = 0;
        int cnt = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            if (find(edgeList.get(i)[0]) != find(edgeList.get(i)[1])) {
                cost += edgeList.get(i)[2];
                union(edgeList.get(i)[0], edgeList.get(i)[1]);
                cnt++;
            }
        }

        if (cnt == p.length - 2) return cost;
        else return -1; // 정점 - 1 만큼 이어져 있지 않다면 -1 리턴
    }
}