import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1};

    static int N,M;
    static int result, cnt;
    static int[][] map, visited;
    static String str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];

        for(int i = 0; i < N; i++){
            str = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        result = bfs(0,0);

        System.out.println(result);
    }

    public static int bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {i, j, 1});
        visited[i][j] = 1;

        while(!q.isEmpty()){
            int[] pos = q.poll();
            int x = pos[0];
            int y = pos[1];
            int distance = pos[2];

            for(int k = 0; k < 4; k++){
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] == 1 || map[nx][ny] == 0) {
                    continue;
                }

                if(nx == N-1 && ny == M-1){
                    return distance + 1;
                }
                
                q.offer(new int[] {nx,ny, distance+1});
                visited[nx][ny] = 1;
            }
        }
        return 0;
    }
}
