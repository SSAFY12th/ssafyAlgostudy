import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503 {
    static int N;
    static int M;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int startx, starty, dir;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(br.readLine());
        startx = Integer.parseInt(st.nextToken());
        starty = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if (map[startx][starty] == 0){
            map[startx][starty] = -1;
            ans++;
        }
        dfs(startx, starty, dir);
        System.out.println(ans);
    }

    static void dfs(int x, int y, int direction) {
        if (map[x][y] == 0){
            map[x][y] = 2;
            ans++;
        }
      
        int flag = 0;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] == 0) flag = 1;
        }
        if (flag == 1) {
            //direction = direction < 3 ? direction + 1 : 0;
            direction = (direction + 3) % 4;
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            while (map[nx][ny] != 0){
                direction = (direction + 3) % 4;
                nx = x + dx[direction];
                ny = y + dy[direction];
            }

            if (0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] == 0){
                map[nx][ny] = 2;
                ans++;
                dfs(nx, ny, direction);
            }
            } 
            else {
              int nx = x - dx[direction];
              int ny = y - dy[direction];
              if (0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] != 1){
                  dfs(nx, ny, direction);
            }
        }
    }
}
