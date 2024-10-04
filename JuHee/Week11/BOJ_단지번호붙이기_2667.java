import java.io.*;
import java.util.*;

public class Main {
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};

    static int N;
    static int[][] map, visited;
    static ArrayList<Integer> houses = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new int[N][N];

        for(int i = 0; i < N; i++){
            str = br.readLine();
            for(int j = 0; j < N; j++){
                map[i][j] = str.charAt(j) -'0';
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 1 && visited[i][j] == 0){
                    int houseCnt = bfs(i, j);
                    houses.add(houseCnt);
                }
            }
        }
        Collections.sort(houses);
        System.out.println(houses.size());
        for(int i = 0; i < houses.size(); i++){
            System.out.println(houses.get(i));
        }
    }

    private static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x,y});
        int cnt = 1;
        visited[x][y] = 1;
        while(!q.isEmpty()){
            int[] pos = q.poll();
            x = pos[0];
            y = pos[1];
            // int x = q.poll()[0]
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 0 || visited[nx][ny] == 1 ){
                    continue;
                }
                else {
                    q.offer(new int[] {nx,ny});
                    visited[nx][ny] = 1;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
