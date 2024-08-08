import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static int N, M, d;
    static int[][] graph;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int ans = 0;
    static boolean cleanChk;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clean(r, c);
        System.out.println(ans + 1);

    }
    public static void clean(int r, int c) {
        graph[r][c] = -1;
        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4;
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M && graph[nr][nc] == 0) {
                ans++;
                clean(nr, nc);
                return;
            }
        }

        int back_d = (d + 2) % 4;
        int br = r + dr[back_d];
        int bc = c + dc[back_d];
        if (br >= 0 && br < N && bc >= 0 && bc < M && graph[br][bc] != 1) {
            clean(br, bc);
        }

    }
}