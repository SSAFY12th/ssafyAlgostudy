import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int N, M;
    static int[][] board;
    static boolean[][] visited;

    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    static List<Edge> edgeList;
    static int[] parent;

    static List<List<Point>> pointList;

    static int nodeCount;

    // 간선 클래스 (MST 탐색용)
    static class Edge implements Comparable<Edge> {
        int start;
        int destination;
        int weight;

        Edge(int start, int destination, int weight) {
            this.start = start;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    // 좌표 저장을 위한 클래스
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];
        pointList = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        nodeCount = 0; // 섬 개수
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] == 1 && !visited[r][c]) {
                    BFS(r, c);
                    nodeCount++;
                }
            }
        }

        parent = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            parent[i] = i;
        }

        edgeList = new ArrayList<>();

        // 섬들 간의 최소 거리를 구함
        for (int i = 0; i < nodeCount; i++) {
            for (Point point : pointList.get(i)) {
                for (int j = 0; j < nodeCount; j++) {
                    if (i == j) continue;
                    int minValue = Integer.MAX_VALUE;

                    for (Point point2 : pointList.get(j)) {
                        if (point.x == point2.x || point.y == point2.y) {
                            int distance = getDistance(point, point2);
                            if (distance >= 2) {
                                minValue = Math.min(minValue, distance);
                            }
                        }
                    }

                    if (minValue != Integer.MAX_VALUE) {
                        edgeList.add(new Edge(i, j, minValue));
                    }
                }
            }
        }

        System.out.println(kruskal());
    }

    // BFS를 통해 섬의 좌표들을 저장하는 함수
    public static void BFS(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { x, y });
        visited[x][y] = true;

        List<Point> currentIsland = new ArrayList<>();
        currentIsland.add(new Point(x, y));

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int r = temp[0];
            int c = temp[1];

            for (int i = 0; i < 4; i++) {
                int nx = r + dx[i];
                int ny = c + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny] || board[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                q.add(new int[] { nx, ny });
                currentIsland.add(new Point(nx, ny));
            }
        }

        pointList.add(currentIsland); // 섬의 좌표 리스트 추가
    }

    // 두 점 사이의 거리 계산
    public static int getDistance(Point start, Point end) {
        if (start.x == end.x) {
            // x가 같을 때 y축 거리 계산
            int minY = Math.min(start.y, end.y);
            int maxY = Math.max(start.y, end.y);
            for (int i = minY + 1; i < maxY; i++) {
                if (board[start.x][i] == 1) {
                    return -1; // 중간에 섬이 있으면 다리 불가
                }
            }
            return maxY - minY - 1;
        } else if (start.y == end.y) {
            // y가 같을 때 x축 거리 계산
            int minX = Math.min(start.x, end.x);
            int maxX = Math.max(start.x, end.x);
            for (int i = minX + 1; i < maxX; i++) {
                if (board[i][start.y] == 1) {
                    return -1; // 중간에 섬이 있으면 다리 불가
                }
            }
            return maxX - minX - 1;
        }
        return -1; // 직선이 아니면 다리 불가
    }

    // Kruskal 알고리즘을 통한 최소 스패닝 트리 계산
    public static int kruskal() {
        Collections.sort(edgeList);

        int answer = 0;
        int edgeUsed = 0;

        for (Edge edge : edgeList) {
            int x = find(edge.start);
            int y = find(edge.destination);

            if (x != y) {
                answer += edge.weight;
                union(x, y);
                edgeUsed++;
                if (edgeUsed == nodeCount - 1) break; // 모든 섬이 연결되면 종료
            }
        }

        return edgeUsed == nodeCount - 1 ? answer : -1; // 모든 섬이 연결되지 않으면 -1 반환
    }

    // Find 함수 (경로 압축)
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // Union 함수
    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }
}
