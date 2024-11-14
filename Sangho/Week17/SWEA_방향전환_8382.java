import java.util.*;
import java.io.*;
 
public class Solution {
     
    static StringTokenizer st;
     
    static int T;
     
    static int x1, y1, x2, y2;
     
    static int BASE = 100;
     
    static int[] dx = {1, -1, 0, 0}; // 가로 방향
    static int[] dy = {0, 0, 1, -1}; // 세로 방향
     
    static boolean[][][] visited; // visited[방향][x][y]
     
    static int answer;
 
    public static void main(String[] args) throws IOException {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        st = new StringTokenizer(br.readLine());
         
        T = Integer.parseInt(st.nextToken());
         
        for(int tc = 1; tc <= T; tc++) {
            answer = Integer.MAX_VALUE;
             
            st = new StringTokenizer(br.readLine());
             
            x1 = Integer.parseInt(st.nextToken()) + BASE;
            y1 = Integer.parseInt(st.nextToken()) + BASE;
            x2 = Integer.parseInt(st.nextToken()) + BASE;
            y2 = Integer.parseInt(st.nextToken()) + BASE;
             
            visited = new boolean[2][201][201]; // [방향][x][y]
             
            BFS(x1, y1);
             
            System.out.println("#" + tc + " " + answer);
        }
    }
     
    public static void BFS(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
         
        // 초기 시작점은 가로(0)와 세로(1)로 시작할 수 있으므로 둘 다 큐에 넣음
        visited[0][x][y] = true;
        visited[1][x][y] = true;
         
        // (direction, x, y, count) -> direction 0: 가로, 1: 세로
        q.add(new int[]{0, x, y, 0});
        q.add(new int[]{1, x, y, 0});
         
        while(!q.isEmpty()) {
            int[] temp = q.poll();
            int direction = temp[0];
            int cx = temp[1];
            int cy = temp[2];
            int count = temp[3];
             
            // 목적지에 도달하면 결과 반환
            if(cx == x2 && cy == y2) {
                answer = count;
                return;
            }
             
            // 가로로 이동 중일 때
            if(direction == 0) {
                // 세로로 방향 전환 (상, 하)
                for(int i = 2; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                     
                    if(nx < 0 || ny < 0 || nx >= 201 || ny >= 201) continue;
                    if(visited[1][nx][ny]) continue; // 세로로 이미 방문했으면 skip
                     
                    visited[1][nx][ny] = true;
                    q.add(new int[] {1, nx, ny, count + 1});
                }
            } 
            // 세로로 이동 중일 때
            else if(direction == 1) {
                // 가로로 방향 전환 (좌, 우)
                for(int i = 0; i < 2; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                     
                    if(nx < 0 || ny < 0 || nx >= 201 || ny >= 201) continue;
                    if(visited[0][nx][ny]) continue; // 가로로 이미 방문했으면 skip
                     
                    visited[0][nx][ny] = true;
                    q.add(new int[] {0, nx, ny, count + 1});
                }
            }
        }
    }
}
