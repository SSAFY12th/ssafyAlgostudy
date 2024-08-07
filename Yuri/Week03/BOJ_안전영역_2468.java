import java.io.*;
import java.util.*;

public class Main {

    static int[][] map = new int[100][100];
    static boolean[][] visited;  // 방문확인
    static int n;
    static int maxSafeZone = 0;  
    static int waterHight = 0;
    static int sum;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                waterHight = Math.max(waterHight, map[i][j]);
            }
        }

        while(waterHight > 0) {
            /*
             물의 높이를 1씩 줄여가며 새롭게 탐색을 해야한다.
             초기화해야할 항목:
             물의 높이(--)
             visited(계속 새로 만들어야함-완전초기화)
             해당 물의 높이 때 안전지대가 몇 개 나왔는지 파악할 변수

             물의 높이를 줄이고 나서 맵 전체를 탐색한다. 방문한 적 없는 영역이 나타나면... dfs를 이용하여 그 근처 이어진 영역을 전부 방문으로 변경.
             그리고 안전지대 개수를 하나 올린다.
             맵 탐색 시 반드시 동서남북 방향으로만 움직여야한다.
             */

            waterHight--;
            visited = new boolean[n][n];
            sum = 0;

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(!visited[i][j] && map[i][j] > waterHight) {
                        sum++;
                        dfs(i, j);
                    }
                }
            }

            maxSafeZone = Math.max(maxSafeZone, sum);
        }
        System.out.println(maxSafeZone);
    }

    public static void dfs(int r, int c) {
        // 현재 위치 방문처리
        visited[r][c] = true;
        for(int i = 0; i < 4; i++) {  
            if(canGo(r, c, i))  // 4방향 중 갈 수 있는 곳이 있다면
                dfs(r+dr[i], c+dc[i]);
        }
    }

    public static boolean canGo(int r, int c, int d) {
        // 이동했을 때 맵을 벗어나는지 확인.
        // 이동할 위치에 방문을 했었는지 확인
        // 이동할 위치가 물의 높이보다 높은지 확인
        if(r+dr[d] >= 0 && r+dr[d] < n && c+dc[d] >= 0 && c+dc[d] < n) {
            if(!visited[r+dr[d]][c+dc[d]] && map[r+dr[d]][c+dc[d]] > waterHight) {
                    return true;
            }
        }
        return false;
    }

}
