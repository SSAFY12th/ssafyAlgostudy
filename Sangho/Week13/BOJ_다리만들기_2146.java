import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int N;
    static int[][] map;
    static List<List<Point>> pointList;
    static int islandCount = 0;
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];
        map = new int[N][N];
        pointList = new ArrayList<>();

        // 섬 입력 받기
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 구분하기
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 1 && !visited[r][c]) {
                    BFS(r, c);
                    islandCount++;
                }
            }
        }

        // 각 섬끼리 다리 거리 재며 최단거리 구하기
        for (int i = 0; i < islandCount; i++) {
            for (Point point : pointList.get(i)) {
                int distance = calculateBridgeLength(point, i);
                answer = Math.min(answer, distance);
            }
        }

        System.out.println(answer);
    }

    // 섬 구분하며, 좌표 저장하기
    public static void BFS(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visited[x][y] = true;
        List<Point> currentIsland = new ArrayList<>();
        currentIsland.add(new Point(x, y));

        while (!q.isEmpty()) {
            Point p = q.poll();
            int px = p.x;
            int py = p.y;

            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny] || map[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                q.add(new Point(nx, ny));
                currentIsland.add(new Point(nx, ny));
            }
        }

        pointList.add(currentIsland);
    }

    // 다리 길이 계산
    public static int calculateBridgeLength(Point start, int islandIndex) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start.x, start.y});
        visited[start.x][start.y] = true;
        int distance = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] temp = q.poll();
                int cx = temp[0];
                int cy = temp[1];

                for (int j = 0; j < 4; j++) {
                    int nx = cx + dx[j];
                    int ny = cy + dy[j];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (visited[nx][ny]) continue;

                    if (map[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    } else if (map[nx][ny] == 1) {
                        // 다른 섬에 도달하면 다리의 길이 반환
                        if (islandIndex != findIsland(nx, ny)) {
                            return distance;
                        }
                    }
                }
            }
            distance++;
        }
        return Integer.MAX_VALUE; // 다른 섬에 도달하지 못할 경우
    }

    // 섬을 찾는 메서드 인덱스 반환
    public static int findIsland(int x, int y) {
        for (int i = 0; i < islandCount; i++) {
            for (Point point : pointList.get(i)) {
                if (point.x == x && point.y == y) {
                    return i;
                }
            }
        }
        return -1; // 섬을 찾지 못한 경우
    }
}
