import java.util.*;

class Solution {
    
    // 정점 방문 여부 체크
    static boolean[] visited;
    
    // 네트워크 개수
    static int network;
    
    // 큐
    static Queue<Integer> q;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        network = 0;
        
        // 방문 배열 초기화
        visited = new boolean[n];  
        
        // 모든 노드를 돌며 방문하지 않은 노드에서 BFS 시작
        for(int i = 0; i < n; i++) {
            if(!visited[i]){
                BFS(i, computers);
                answer++; // 네트워크 개수 증가
            }
        }
        
        return answer;
    }
    
    // BFS 탐색.
    public static void BFS(int start, int[][] computers) {
        q = new LinkedList<>();
        
        // 시작 정점 방문 처리
        visited[start] = true;
        
        // 큐에 시작 정점 추가
        q.add(start);
        
        // 큐가 빌 때까지 탐색
        while(!q.isEmpty()) {
            int current = q.poll();
            
            // 현재 정점과 연결된 다른 정점들 탐색
            for(int i = 0; i < computers[current].length; i++) {
                if(computers[current][i] == 1 && !visited[i]) { // 연결되어 있고 아직 방문하지 않았다면
                    q.add(i);          // 큐에 추가
                    visited[i] = true; // 방문 처리
                }
            }
        }
    }
}
