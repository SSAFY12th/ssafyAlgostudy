import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static StringTokenizer st;

    static int T;
    static int N, M, R, C, L;
    static int[][] map;

    // 상 하 좌 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int[][] visited;

    static Queue<int[]> q;

    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visited = new int[N][M];

            answer = 0;

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            BFS(R, C, map[R][C]);

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (visited[r][c] != 0 && visited[r][c] <= L) {
                        answer++;
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    public static void BFS(int r, int c, int t) {
        q = new LinkedList<>();

        // 좌표, 터널 구조물 타입, 현재까지의 거리
        q.add(new int[]{r, c, t, 1});
        visited[r][c] = 1;  // 시작 위치는 1로 초기화 (시간 경과)

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];
            int type = temp[2];
            int distance = temp[3];

            if (distance >= L) continue;  // 이동 시간이 L을 초과하면 더 이상 탐색하지 않음

            switch (type) {
                case 1: // 상하좌우
                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] != 0 || map[nx][ny] == 0) continue;

                        // 다음 파이프와 연결 가능 여부 체크
                        if (canMove(i, map[nx][ny])) {
                            visited[nx][ny] = distance + 1;
                            q.add(new int[]{nx, ny, map[nx][ny], distance + 1});
                        }
                    }
                    break;
                case 2: // 상하
                    for (int i = 0; i < 2; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] != 0 || map[nx][ny] == 0) continue;

                        // 다음 파이프와 연결 가능 여부 체크
                        if (canMove(i, map[nx][ny])) {
                            visited[nx][ny] = distance + 1;
                            q.add(new int[]{nx, ny, map[nx][ny], distance + 1});
                        }
                    }
                    break;
                case 3: // 좌우
                    for (int i = 2; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] != 0 || map[nx][ny] == 0) continue;

                        // 다음 파이프와 연결 가능 여부 체크
                        if (canMove(i, map[nx][ny])) {
                            visited[nx][ny] = distance + 1;
                            q.add(new int[]{nx, ny, map[nx][ny], distance + 1});
                        }
                    }
                    break;
                case 4: // 상우
                    for (int i = 0; i < 4; i++) {
                        if (i == 0 || i == 3) {  // 상, 우
                            int nx = x + dx[i];
                            int ny = y + dy[i];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] != 0 || map[nx][ny] == 0) continue;

                            // 다음 파이프와 연결 가능 여부 체크
                            if (canMove(i, map[nx][ny])) {
                                visited[nx][ny] = distance + 1;
                                q.add(new int[]{nx, ny, map[nx][ny], distance + 1});
                            }
                        }
                    }
                    break;
                case 5: // 하우
                    for (int i = 0; i < 4; i++) {
                        if (i == 1 || i == 3) {  // 하, 우
                            int nx = x + dx[i];
                            int ny = y + dy[i];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] != 0 || map[nx][ny] == 0) continue;

                            // 다음 파이프와 연결 가능 여부 체크
                            if (canMove(i, map[nx][ny])) {
                                visited[nx][ny] = distance + 1;
                                q.add(new int[]{nx, ny, map[nx][ny], distance + 1});
                            }
                        }
                    }
                    break;
                case 6: // 하좌
                    for (int i = 0; i < 4; i++) {
                        if (i == 1 || i == 2) {  // 하, 좌
                            int nx = x + dx[i];
                            int ny = y + dy[i];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] != 0 || map[nx][ny] == 0) continue;

                            // 다음 파이프와 연결 가능 여부 체크
                            if (canMove(i, map[nx][ny])) {
                                visited[nx][ny] = distance + 1;
                                q.add(new int[]{nx, ny, map[nx][ny], distance + 1});
                            }
                        }
                    }
                    break;
                case 7: // 상좌
                    for (int i = 0; i < 4; i++) {
                        if (i == 0 || i == 2) {  // 상, 좌
                            int nx = x + dx[i];
                            int ny = y + dy[i];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] != 0 || map[nx][ny] == 0) continue;

                            // 다음 파이프와 연결 가능 여부 체크
                            if (canMove(i, map[nx][ny])) {
                                visited[nx][ny] = distance + 1;
                                q.add(new int[]{nx, ny, map[nx][ny], distance + 1});
                            }
                        }
                    }
                    break;
            }
        }
    }

    // 방향과 다음 터널 타입에 따라 이동 가능한지 확인
    public static boolean canMove(int direction, int nextType) {
        switch (direction) {
            case 0: // 상
                return nextType == 1 || nextType == 2 || nextType == 5 || nextType == 6;
            case 1: // 하
                return nextType == 1 || nextType == 2 || nextType == 4 || nextType == 7;
            case 2: // 좌
                return nextType == 1 || nextType == 3 || nextType == 4 || nextType == 5;
            case 3: // 우
                return nextType == 1 || nextType == 3 || nextType == 6 || nextType == 7;
            default:
                return false;
        }
    }
}
