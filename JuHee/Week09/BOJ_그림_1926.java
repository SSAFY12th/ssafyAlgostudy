import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {

    static int N, M;
    static int cnt;
    static int[][] page;
    static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        page = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                page[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ccnt = 0;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(visited[i][j] == false && page[i][j] == 1 ) {
                    ccnt++;
                    ans = Math.max(bfs(i, j),ans);
                }
            }

        }
        System.out.println(ccnt);
        System.out.println(ans);
    }


    public static int bfs(int i, int j) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{i, j});
        visited[i][j] = true;
        int result = 1;
        while (!que.isEmpty()) {
            //1차원 배열로 받는법
            int[] tmp = que.poll();
            int x = tmp[0];
            int y = tmp[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) {
                    continue;
                }

                if (visited[nx][ny] == false && page[nx][ny] == 1) {
                    que.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    result+=1;
                }
            }

        }
        return result;

    }
}
