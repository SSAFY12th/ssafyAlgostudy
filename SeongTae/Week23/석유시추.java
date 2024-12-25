import java.util.*;
class Solution {
    static class Point{
        int r;
        int c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n, m;
    public int solution(int[][] land) {
        int answer = 0;
        n = land.length;
        m = land[0].length;
        boolean[][] visited = new boolean[n][m];
        int[] capacities = new int[m]; // 각 열마다 뽑을 수 있는 석유량
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(land[i][j] == 1 && !visited[i][j]){
                    Set<Integer> cols = new HashSet<>();
                    int capacity = BFS(i, j, land, visited, cols);
                    
                    for(int col : cols){
                        capacities[col] += capacity;
                    }
                }
            }
        }
        Arrays.sort(capacities);
        answer = capacities[m - 1];
        return answer;
    }
    
    public static int BFS(int i, int j, int[][] land, boolean[][] visited, Set<Integer> cols){ 
        int cnt = 1;
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.offer(new Point(i,j));
        visited[i][j] = true;
        cols.add(j);
        
        while(!q.isEmpty()){
            Point p = q.poll();
            int r = p.r;
            int c = p.c;
            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(check(nr,nc) && !visited[nr][nc] && land[nr][nc] == 1){
                    q.offer(new Point(nr,nc));
                    visited[nr][nc] = true;
                    cols.add(nc);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    public static boolean check(int r, int c){
        return r >=0 && r < n && c >= 0 && c < m;
    }
}
