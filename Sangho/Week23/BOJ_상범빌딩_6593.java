import java.util.*;
import java.io.*;

public class Main {

    static int L, R, C; // 빌딩의 층 수, 행, 열
    static char[][][] map; // 빌딩의 구조
    static boolean[][][] visited; // 방문 여부
    static int[] dx = {1, -1, 0, 0, 0, 0}; // x 방향 (위, 아래)
    static int[] dy = {0, 0, 1, -1, 0, 0}; // y 방향 (동, 서)
    static int[] dz = {0, 0, 0, 0, 1, -1}; // z 방향 (앞, 뒤)
    static int startx, starty, startz; // 시작 좌표
    static int distance; // 탈출까지 걸린 시간
    static StringBuilder sb = new StringBuilder(); // 결과 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = br.readLine();
            if (line == null || line.trim().isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            // 종료 조건
            if (L == 0 && R == 0 && C == 0) {
                System.out.print(sb);
                break;
            }

            // 초기화
            map = new char[L][R][C];
            visited = new boolean[L][R][C];
            distance = Integer.MAX_VALUE;

            // 빌딩 정보 입력
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String str = br.readLine();
                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = str.charAt(k);
                        if (map[i][j][k] == 'S') {
                            startx = i;
                            starty = j;
                            startz = k;
                        }
                    }
                }
                if (i < L - 1) br.readLine(); // 층 사이 빈 줄 처리
            }

            // BFS 수행
            BFS();

            // 결과 저장
            if (distance == Integer.MAX_VALUE) {
                sb.append("Trapped!\n");
            } else {
                sb.append("Escaped in ").append(distance).append(" minute(s).\n");
            }
        }
    }

    public static void BFS() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startx, starty, startz, 0}); // 시작점 추가
        visited[startx][starty][startz] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int cx = temp[0];
            int cy = temp[1];
            int cz = temp[2];
            int dist = temp[3];

            // 출구에 도달한 경우
            if (map[cx][cy][cz] == 'E') {
                distance = dist; // 최단 거리 저장
                return;
            }

            // 6방향 탐색
            for (int i = 0; i < 6; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                int nz = cz + dz[i];

                // 범위 체크 및 조건 검사
                if (nx < 0 || nx >= L || ny < 0 || ny >= R || nz < 0 || nz >= C) continue;
                if (map[nx][ny][nz] == '#' || visited[nx][ny][nz]) continue;

                // 방문 처리 및 큐에 추가
                visited[nx][ny][nz] = true;
                q.add(new int[]{nx, ny, nz, dist + 1});
            }
        }
    }
}
