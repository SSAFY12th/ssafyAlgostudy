import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};

    static class State {
        int rx, ry, bx, by, count;

        State(int rx, int ry, int bx, int by, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        
        int rx = 0, ry = 0, bx = 0, by = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if (map[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        System.out.println(bfs(rx, ry, bx, by));
    }

    private static int bfs(int rx, int ry, int bx, int by) {
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.count >= 10) {
                return 0; // 10번 넘게 움직인 경우 실패
            }

            for (int i = 0; i < 4; i++) {
                int[] red = move(cur.rx, cur.ry, dx[i], dy[i]);
                int[] blue = move(cur.bx, cur.by, dx[i], dy[i]);

                int nrx = red[0], nry = red[1];
                int nbx = blue[0], nby = blue[1];

                // 파란 구슬이 구멍에 빠지면 실패
                if (map[nbx][nby] == 'O') continue;

                // 빨간 구슬만 구멍에 빠지면 성공
                if (map[nrx][nry] == 'O') return 1;

                // 두 구슬이 같은 위치에 있으면 더 많이 움직인 구슬을 한 칸 뒤로
                if (nrx == nbx && nry == nby) {
                    if (red[2] > blue[2]) {
                        nrx -= dx[i];
                        nry -= dy[i];
                    } else {
                        nbx -= dx[i];
                        nby -= dy[i];
                    }
                }

                // 방문하지 않은 상태라면 큐에 추가
                if (!visited[nrx][nry][nbx][nby]) {
                    visited[nrx][nry][nbx][nby] = true;
                    queue.add(new State(nrx, nry, nbx, nby, cur.count + 1));
                }
            }
        }

        return 0; // 탈출할 수 없는 경우 실패
    }

    // 벽에 부딪히거나 구멍에 빠질 때까지 구슬 이동
    private static int[] move(int x, int y, int dx, int dy) {
        int distance = 0;
        while (map[x + dx][y + dy] != '#' && map[x][y] != 'O') {
            x += dx;
            y += dy;
            distance++;
        }
        return new int[]{x, y, distance};
    }
}
