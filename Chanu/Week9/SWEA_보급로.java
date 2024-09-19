

import java.io.*;
import java.util.*;
 
public class Solution {
    static int n;
    static int[][] map;
    static int[][] visited;
    static int[] dx = {0,1,-1,0};
    static int[] dy = {1,0,0,-1};
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int T = Integer.parseInt(st.nextToken());
 
        for (int test_case=1; test_case<=T; test_case++) {
 
            st = new StringTokenizer(br.readLine());
 
            n = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            visited = new int[n][n];
 
            for (int i=0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String str = st.nextToken();
                for (int j=0; j< n; j++) {
                    map[i][j] = str.charAt(j)-'0';
                    visited[i][j] = Integer.MAX_VALUE;
                }
            }
            visited[0][0] = 0;
            bfs(0,0);
            System.out.println("#"+test_case+" "+visited[n-1][n-1]);
        }
 
    }
 
    static void bfs(int x, int y) {
 
       Queue<int[]> queue = new ArrayDeque<>();
       queue.offer(new int[]{x,y});
 
       while (!queue.isEmpty()) {
 
           int[] node = queue.poll();
 
           x = node[0];
           y = node[1];
 
           for (int i=0; i<4; i++) {
 
               int nx = x + dx[i];
               int ny = y + dy[i];
 
               if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                   continue;
               }
 
               if (visited[nx][ny] > visited[x][y] + map[nx][ny]) {
                   visited[nx][ny] = visited[x][y] + map[nx][ny];
                   queue.offer(new int[]{nx,ny});
               }
 
           }
 
 
 
       }
 
 
    }
}
