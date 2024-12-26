import java.util.*;

class Solution {
    int n;
    int m;
    
    int[][] map;
    boolean[][] visited;
    int[] result;
    
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};
    
    public int solution(int[][] land) {
        //init
        n = land.length;
        m = land[0].length;

        map = land;
        visited = new boolean[n][m];
        result = new int[m];
        
        //logic
        for (int j = 0; j < m; j++) {
            
            for (int i = 0; i < n; i++) {
                if (map[i][j] == 0 || visited[i][j]) continue;
                bfs(i, j);
            }
        }
        
        int answer = 0;
        for (int i = 0; i < m; i++) {
            answer = Math.max(answer, result[i]);
        }
        
        return answer;
    }
    
    void bfs(int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {r, c});
        visited[r][c] = true;
        
        int cnt = 0;
        HashSet<Integer> set = new HashSet<>();
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            
            cnt++;
            set.add(curr[1]);
            
            for (int i = 0; i < 4; i++) {
                int ny = curr[0] + dy[i];
                int nx = curr[1] + dx[i];
                
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                if (map[ny][nx] == 0 || visited[ny][nx]) continue;
                
                q.add(new int[] {ny, nx});
                visited[ny][nx] = true;
            }
        }
        
        for (int col : set) {
            result[col] += cnt;
        }
    }
    
}
