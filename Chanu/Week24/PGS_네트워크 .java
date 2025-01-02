	

import java.util.*;

class Solution {
    
    static boolean[] visited;
    static int count = 0;
    static int[][] computers;
    
    public int solution(int n, int[][] computers) {
        
        this.computers = computers;
        visited = new boolean[n];
        
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                count++;
                bfs(i);
            }
            
        }
        
        return count;
    }
    
    static void bfs (int node) {
        
        Queue<Integer> queue = new ArrayDeque<>();
        visited[node] = true;
        queue.offer(node);
        
        while (!queue.isEmpty()) {
            int nowNode = queue.poll();
            
            for (int i=0; i<computers[nowNode].length; i++) {
                
                if (computers[nowNode][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                    
                }
            }
        }
        
    }
}
