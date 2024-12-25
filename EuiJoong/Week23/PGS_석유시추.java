import java.util.*;

class Solution {
    
    static class Point {
        int r, c;
        
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] oil;
    
    public int solution(int[][] land) {
        
        int N = land.length;
        int M = land[0].length;
        oil = new int[M];
        
        int ans = 0;
        
        //열을 움직이며 밑으로 뚫어야 함.
        boolean[][] visited = new boolean[N][M];
        int rockOil = 0;
        for (int c = 0; c < M; c++) {
            for (int r = 0; r < N; r++) {
                if (land[r][c] == 1 && !visited[r][c]) {
                    bfs(r, c, visited, land);
                }
            }
        }
        for (int o : oil) {
            ans = Math.max(ans, o);
        }
        
        return ans;
    }
    
    private static void bfs(int r, int c, boolean[][] visited, int[][] land) {
        Queue<Point> q = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        int N = land.length;
        int M = land[0].length;
        q.offer(new Point(r, c));
        visited[r][c] = true;
        int res = 1;
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            int cur_r = p.r;
            int cur_c = p.c;
            set.add(cur_c);
            
            for (int i = 0; i < 4; i++) {
                int nr = cur_r + dr[i];
                int nc = cur_c + dc[i];
                
                if (0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc] && land[nr][nc] == 1) {
                    res += 1;
                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc));
                }
            }
        }
        
        for (int s : set) {
            oil[s] += res;
        }
        
    }
}
