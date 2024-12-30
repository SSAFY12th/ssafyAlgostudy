class Solution {
    
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, computers, n);
                answer++;
            }
        }
        return answer;
    }
    
    public void dfs(int v, int[][] computers, int n) {
        visited[v] = true;
        for (int j = 0; j < n; j++) {
            if (computers[v][j] == 1 && !visited[j]) {
                dfs(j, computers, n);
            }
        }
    }
}
