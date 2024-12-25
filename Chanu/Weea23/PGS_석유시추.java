
import java.util.*;
import java.lang.Math;


class Solution {
    
    static boolean[][] visited;
    static int[][] land;
    static int cnt;
    static int total = 2;
    static HashMap<Integer, Integer> map = new HashMap<>();;
    static HashSet<Integer> set = new HashSet<>();
    static int[] dx ={-1,0,1,0};
    static int[] dy ={0,1,0,-1};
    static int max = Integer.MIN_VALUE;
    
    public int solution(int[][] land) {
        
        this.land = land;
        visited = new boolean[land.length][land[0].length];
        
        for (int i=0; i<land.length; i++) {
            for (int j=0; j<land[0].length; j++) {
                if (!visited[i][j] && land[i][j] == 1) {
                    cnt = 1;
                    bfs(i,j);
                    map.put(total,cnt);
                    total++;
                }
            }
        }
        
        for (int i=0; i < land[0].length; i++) {
            
            set.clear();
            int s = 0;
            
            for (int j=0; j<land.length; j++) {
                if (land[j][i] >= 2) {
                    set.add(land[j][i]);
                }
            }
            
            for (int k : set) {
                s += map.get(k);
            }
            
            max = Math.max(max,s);
              
        }
        
        return max;
    }
    
    static void bfs(int x , int y) {
        
        visited[x][y] = true;
        land[x][y] = total;
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x,y});
        
        while (!queue.isEmpty()) {
           int[] node =  queue.poll();
            x = node[0];
            y = node[1];
            
            for (int i=0; i< 4; i++) {
                
                int nx = x +dx[i];
                int ny = y +dy[i];
                
                if (nx < 0 || nx >= land.length || ny < 0 || ny >= land[0].length || visited[nx][ny] || land[nx][ny] != 1) {
                    continue;
                }
                
                visited[nx][ny] = true;
                land[nx][ny] = total;
                cnt++;
                queue.offer(new int[]{nx,ny});
            }
        }
        
    }
}
