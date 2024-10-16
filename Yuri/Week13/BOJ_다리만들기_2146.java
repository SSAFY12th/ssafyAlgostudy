import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] map;
    static int min = 1000;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) map[i][j] = -1;  // 처음에 섬의 위치를 전부 -1로 저장한다.
            }
        }

        // 섬을 찾아 번호를 매긴다.
        int landNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == -1)
                    findLand(i, j, landNum++);
            }
        }

        // 현재 섬에서 다른 섬으로 이어지는 다리를 만들고 최소값을 갱신한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] > 0)
                    findBridge(i, j, map[i][j]);
            }
        }

        System.out.println(min);


    }

    // bfs를 활용하여 이어진 만큼 섬을 탐색하고 번호 매기기.
    public static void findLand(int r, int c, int landNum) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r, c});
        map[r][c] = landNum;

        while(!q.isEmpty()) {
            int[] p = q.poll();
            int newR, newC;

            for (int i = 0; i < 4; i++) {
                newR = p[0]+dr[i];
                newC = p[1]+dc[i];

                if(newR >= 0 && newC >= 0 && newR < N && newC < N && map[newR][newC] == -1) {
                    q.add(new int[] {newR, newC});
                    map[newR][newC] = landNum;
                }
            }
        }
    }

    // bfs를 활용하여 다른 섬으로 이동하는 다리 놓기.
    public static void findBridge(int r, int c, int landNum) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r, c, 0});
        visited[r][c] = true;

        while(!q.isEmpty()) {
            int[] p = q.poll();

            // 만약 현재 위치가 다른 섬이라면
            if(map[p[0]][p[1]] > 0 && map[p[0]][p[1]] != landNum) {
                min = Math.min(min, p[2]-1);    // 가장 처음에 들어온 값이 무조건 시작 위치에서의 최소값이 된다.
                return;                         // 따라서 최소값 갱신 후 바로 return으로 다리 찾기 종료.
            }

            int newR, newC;

            for (int i = 0; i < 4; i++) {
                newR = p[0] + dr[i];
                newC = p[1] + dc[i];

                if(newR >= 0 && newC >= 0 && newR < N && newC < N && !visited[newR][newC] && map[newR][newC] != landNum) {
                    q.add(new int[] {newR, newC, p[2]+1});
                    visited[newR][newC] = true;
                }
            }
        }
    }
}
