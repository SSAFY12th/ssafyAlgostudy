import java.io.*;
import java.util.*;

public class Main {

    static int H, W, N;
    static char[][] map;
    static int[] dao, dizini;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int[][] dir;
    static int[] result = null;
    static int resultCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];

        // 맵을 받으면서 다오와 디지니 위치를 저장한 뒤, 그 위치는 .으로 변경
        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'D') {
                    dao = new int[]{i, j};
                    map[i][j] = '.';
                } else if(map[i][j] == 'Z') {
                    dizini = new int[]{i, j};
                    map[i][j] = '.';
                }
            }
        }

        N = Integer.parseInt(br.readLine());
        dir = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // 제한사항이 여기 들어온다.
            String dir1 = st.nextToken();
            String dir2 = st.nextToken();

            dir[i][0] = returnDir(dir1);
            dir[i][1] = returnDir(dir2);
        }

        int[] path = new int[N];
        dfs(0, dao, path);

        if(result != null) {
            System.out.println("YES");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < resultCnt; i++)
                sb.append(returnDir(result[i]));
            System.out.println(sb.toString());
        } else {
            System.out.println("NO");
        }

    }

    public static void dfs(int cnt, int[] dao, int[] path) {
        for (int i = 0; i < 2; i++) {

            // 가지치기를 해보자...
            if(result != null) return;  // 경로를 이미 찾았으면 return
            if(dao[0] == dizini[0] && dao[1] == dizini[1]) {    // 디지니와 만났으면 경로를 저장하고 return
                result = path;
                resultCnt = cnt;
                return;
            }
            if(cnt == N) return;    // 움직일 수 있는만큼 움직였으면 return

            // 제한사항에 따라 다오를 움직인다.
            int[] newDao = new int[2];
            newDao[0] = dao[0] + dr[dir[cnt][i]];
            newDao[1] = dao[1] + dc[dir[cnt][i]];

            // 다오가 맵 안에 있으면서 움직이려는 곳이 장애물이 아닌경우
            if(newDao[0] >= 0 && newDao[1] >= 0 && newDao[0] < H && newDao[1] < W && map[newDao[0]][newDao[1]] == '.') {
                path[cnt] = dir[cnt][i];
                dfs(cnt + 1, newDao, path);
            }
        }

    }

    public static int returnDir(String dir) {
        if(dir.equals("W")) return 0;
        if(dir.equals("S")) return 1;
        if(dir.equals("A")) return 2;
        return 3;
    }

    public static char returnDir(int dir) {
        if(dir == 0) return 'W';
        if(dir == 1) return 'S';
        if(dir == 2) return 'A';
        return 'D';
    }

}
