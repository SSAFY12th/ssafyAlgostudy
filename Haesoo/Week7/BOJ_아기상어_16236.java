import java.io.*;
import java.util.*;

public class BOJ16236 {
    public static pair start;
    public static int N, eatY, eatX, sharkSize = 2, fishCnt = 2; // 처음에 먹어야 하는 먹이 수도 2로 초기화
    public static int[][] map;
    public static boolean[][] visited;
    public static int[][] distance;
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    start = new pair(i, j);
                    map[i][j] = 0; // 아기 상어가 있던 곳 0으로 만든다
                }
            }
        }
        int time = 0;
        while (true) {
            visited = new boolean[N][N];
            distance = new int[N][N];
            bfs(); // 상어가 먹을 수 있는 각 먹이마다 거리를 계산해서 distance 채우는 함수
            int minDistance = 1000;
            boolean checkFeed = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != 0 && map[i][j] < sharkSize) { // 먹이 칸이고, 해당 값이 상어 크기보다 작으면 먹을 수 있음
                        checkFeed = true; // 뭐라도 먹었다
                        if (distance[i][j] != 0 && distance[i][j] < minDistance) { // 거리가 0이 아니고, 최소 거리보다 작은 경우
                            minDistance = distance[i][j];
                            eatY = i;
                            eatX = j; // 여러 먹이들 중 가장 작은 애 좌표를 계속 갱신 (걔부터 먹으러 갈거니까)
                        }
                    }
                }
            }
            if (!checkFeed || minDistance == 1000) break;  // 먹을 수 있는 먹이가 없을 때 종료
            fishCnt--;
            if (fishCnt == 0) { // 다 먹으면 클 수 있다
                sharkSize++; // 커짐
                fishCnt = sharkSize; // 커진 몸집만큼 또 먹어야 댐
            }
            time += minDistance; // 제일 작은 애로 가서 먹었으니까 그 거리만큼 시간 세기
            start = new pair(eatY, eatX);
            map[eatY][eatX] = 0; // 다시 또 이동해야 하니까, 출발 위치 갱신하고 먹은 곳은 0으로 만든다
        }
        System.out.println(time);
    }

    public static boolean range(int ny, int nx) {
        return ny >= 0 && nx >= 0 && ny < N && nx < N;
    }
    public static void bfs() {
        Queue <pair> q = new LinkedList<>();
        q.offer(start);
        visited[start.y][start.x] = true;
        while (!q.isEmpty()) {
            int y = q.peek().y;
            int x = q.peek().x;
            q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (range(ny, nx) && !visited[ny][nx] && map[ny][nx] <= sharkSize) { // 먹이 크기랑 상어 크기 비교할 때 반드시 등호 넣기. 먹진 못해도 통과할 순 있잖아 !!
                    pair idx = new pair(ny, nx);
                    q.offer(idx);
                    distance[ny][nx] = distance[y][x] + 1;
                    visited[ny][nx] = true;
                }
            }
        }
    }
    public static class pair {
        int y, x;
        pair (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
