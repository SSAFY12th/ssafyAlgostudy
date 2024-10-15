

import java.io.*;
import java.util.*;

public class Baek2146 {

    static int[][] map;
    static int n;
    static int min = Integer.MAX_VALUE;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int cnt = 1;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i=0; i<n; i++) {

            for (int j=0; j<n; j++) {

                if (map[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    map[i][j] = cnt;
                    getIslandCount(i,j);
                    cnt++;
                }

            }
        }

        for (int i=0; i<n; i++) {

            for (int j=0; j<n; j++) {
                if (map[i][j] >=1) {
                    bfs(i,j);
                }
            }
        }

        System.out.println(min-1);


    }

    private static void bfs(int x, int y) {
        int count =0;

        visited = new boolean[n][n];
        visited[x][y] = true;
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{x,y,0});

        while (!queue.isEmpty()) {

            int[] node = queue.poll();
            count = node[2];

            if (map[node[0]][node[1]] != map[x][y] && map[node[0]][node[1]] >= 1) {
                min = Math.min(min,count);
                return;
            }

            if (min < count) {
                return;
            }

            for (int i=0; i<4; i++) {

                int nx = node[0] + dx[i];
                int ny = node[1] + dy[i];

                if (nx <0 || nx >=n || ny<0 || ny >=n || visited[nx][ny] || map[nx][ny] == map[x][y]) {
                    continue;
                }

                visited[nx][ny] = true;
                queue.offer(new int[]{nx,ny, count+1});
            }

        }
    }

    static void getIslandCount(int x , int y) {


        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x,y});

        while (!queue.isEmpty()){

            int[] node = queue.poll();

            x = node[0];
            y = node[1];

            for (int i=0; i<4; i++) {

                int nx = x+ dx[i];
                int ny = y+ dy[i];

                if (nx <0 || nx >=n || ny < 0 || ny >=n || visited[nx][ny]) {
                    continue;
                }

                if (map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    map[nx][ny] = cnt;
                    queue.offer(new int[]{nx,ny});
                }

            }
        }



    }
}
