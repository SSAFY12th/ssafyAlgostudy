import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m;                // 맵의 크기
    static char[][] map;
    static boolean[][] visited;     // 방문 체크
    static int enemy = 0;           // 적 위력
    static int army = 0;            // 아군 위력
    static int sum;                 // 병사의 수를 세기 위한 변수
    static int[] dr = {-1, 0, 1, 0};// 쉽게 상하좌우로 이동하기 위한 배열
    static int[] dc = {0, 1, 0, -1};
    static char type;               // 현재 탐색하고 있는 병사 타입이 아군인지 적군인지 표시하기 위한 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[m][n];
        visited = new boolean[m][n];

        // 맵 입력받기
        for(int i = 0; i < m; i++) {
            String s = br.readLine();
            for(int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        // 맵 전체돌기
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j]) {        // 방문 안했다면
                    sum = 0;                // 병사 수 초기화
                    type = map[i][j];       // 현재 탐색하려는 병사 타입 저장
                    dfs(i, j);              // dfs
                    if(type == 'W') {       // 탐색이 끝난 후 병사 타입에 따라 위력 계산
                        army += sum*sum;
                    }
                    else {
                        enemy += sum*sum;
                    }
                }
            }
        }

        System.out.println(army+" "+enemy);

    }

    public static void dfs(int r, int c) {
        sum++;
        visited[r][c] = true;   // 방문 표시

        // 만약 이동하였을 때, 맵을 벗어나지 않으면서, 아직 방문하지 않았고, 현재 탐색중인 병사 타입과 같다면 dfs호출.
        for(int i = 0; i < 4; i++) {
            if(r+dr[i] >= 0 && r+dr[i] < m && c+dc[i] >= 0 && c+dc[i] < n && !visited[r+dr[i]][c+dc[i]] &&(map[r+dr[i]][c+dc[i]] == type)) {
                dfs(r+dr[i], c+dc[i]);
            }
        }
    }
}
