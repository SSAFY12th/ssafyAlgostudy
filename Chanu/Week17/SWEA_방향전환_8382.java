

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution {
     
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[] arr = new int[4];
    static int min;
    public static void main(String[] args) throws IOException {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         
        int T = Integer.parseInt(st.nextToken());
         
        for (int tc=1; tc<=T; tc++) {
             
            map = new int[201][201];
             
            min =Integer.MAX_VALUE;
             
            st = new StringTokenizer(br.readLine());
             
            for (int i=0; i<4; i++) {    
                arr[i] = Integer.parseInt(st.nextToken()) + 100;
            }
             
             
            visited = new boolean[201][201];
            bfs(arr[0], arr[1], 0);
             
            visited = new boolean[201][201];
            bfs(arr[0], arr[1], 1);
             
            visited = new boolean[201][201];
            bfs(arr[0], arr[1], 2);
             
            visited = new boolean[201][201];
            bfs(arr[0], arr[1], 3);
             
            System.out.println("#"+tc+" "+min);
             
             
        }
    }
     
    static void bfs(int x, int y, int type) {
         
        visited[x][y] = true;
        Queue< int[] > queue = new ArrayDeque<>();
        queue.offer(new int[] {x,y,type,0});
         
        while (!queue.isEmpty()) {
             
            int[] node = queue.poll();
             
            x = node[0];
            y = node[1];
            type = node[2];
            int cnt = node[3];
             
            if (x == arr[2] && y == arr[3]) {
                min = Math.min(min, cnt);
                break;
            }
             
             
            if (type == 0 || type == 2) {
                 
                for (int i=1; i<4; i+=2) {
                     
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                     
                     
                    if (nx >= 0 && nx < 201 && ny >= 0 && ny < 201 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new int[] {nx,ny,i,cnt+1});
                         
                    }
                     
                }
            }
             
            else {
                 
                for (int i=0; i<4; i+=2) {
                     
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                     
                    if (nx >= 0 && nx < 201 && ny >= 0 && ny < 201 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new int[] {nx,ny,i,cnt+1});
                    }
                     
     
                }
                 
            }
        }
         
         
         
    }
 
}
