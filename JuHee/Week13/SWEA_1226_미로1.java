import java.io.*;
import java.util.*;

public class Solution{

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int T;
    static String str;
    static int result;
    static int[][] map = new int[16][16];
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int tc = 1; tc <= 10; tc++){
            T = Integer.parseInt(br.readLine());
            visited = new int[16][16];
            for(int i = 0; i < 16; i++){
                str = br.readLine();
                for(int j = 0; j < 16; j++){
                    map[i][j] = str.charAt(j) - '0';
                }
            }

            for(int i = 0; i < 16; i++){
                for(int j = 0; j < 16; j++){
                    if(map[i][j] == 2 && visited[i][j] == 0){
                        result = bfs(i, j);
                    }
                }
            }
            System.out.println("#"+T+" "+result);
        }
    }

    public static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y});
        visited[x][y] = 1;

        while(!q.isEmpty()){
            int pos[] = q.poll();
            x = pos[0];
            y = pos[1];

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 1 || ny < 1 || nx >= 15 || ny >= 15) {
                    continue;
                }
                if(map[nx][ny] == 1 || visited[nx][ny] == 1) {
                    continue;
                }
                if(map[nx][ny] == 3) {
                    return 1;
                }
                q.offer(new int[]{nx,ny});
                visited[nx][ny] = 1;
            }
        }
        return 0;
    }
}
