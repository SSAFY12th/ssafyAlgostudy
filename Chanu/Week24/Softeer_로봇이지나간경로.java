

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Softeer로봇이지나간자리 {

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static char[][] map;
    static int n;
    static int m;
    static ArrayList<int[]> list;


    public static void main(String[] args) throws IOException {

        // 로봇의 명령
        //  L: 로봇이 왼쪽으로 90도 회전하며, 이에 따라 바라보는 방향이 바뀐다.
        // R: 로봇이 오른쪽으로 90도 회전하며, 이에 따라 바라보는 방향이 바뀐다.
        // A: 로봇이 바라보는 방향으로 두 칸 전진한다. 단, 이 과정에서 로봇이 격자판 바깥을 나간다면 로봇은 이 명령을 수행할 수 없다.

        // 같은곳 방문 불가


        //결과
        //1. 처음 로봇을 어떤 칸에, 어떤 방향(동서남북 중 하나)으로 두어야 하는가?
        //2. 이후 로봇에 어떤 명령어를 어떤 순서대로 입력해야 하는가?


        //방법
        // 1. # 이 있는 부분만 찾아서 시작 위치를 정한다 *  4 방향의 시작
        // 2. 각각 bfs를 돌아 -> 기존 맵이 # 인 애들만


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        list = new ArrayList<>();

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j<m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i=0; i<n; i++) {
            for (int j = 0; j<m; j++) {
                if (map[i][j] == '#' && checkStart(i,j)){
                    list.add(new int[] {i,j});
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int[] startPoint : list) {
            for (int d =0; d < 4; d++) {

                if (!checkMove(startPoint[0],startPoint[1],d)) {
                    continue;
                }

                StringBuilder result = new StringBuilder();

                dfs(startPoint[0],startPoint[1],d,result);

                sb.append(startPoint[0]+1).append(" ").append(startPoint[1]+1).append("\n");

                char resultD = '.';
                switch (d) {
                    case 0:
                        resultD = '^';
                        break;
                    case 1:
                        resultD = '>';
                        break;
                    case 2:
                        resultD = 'v';
                        break;
                    case 3:
                        resultD = '<';
                        break;
                }

                sb.append(resultD).append("\n");
                sb.append(result);


            }
        }

        System.out.println(sb);


    }

    static boolean checkStart(int x,int y) {

        int cnt = 0;
        for (int d = 0; d < 4; d++){

            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >=0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == '#'){
                cnt++;
            }
        }

        if (cnt == 1) {
            return true;
        }

        return false;
    }

    static boolean checkMove(int x, int y, int d) {

        int nx = x;
        int ny = y;

        for (int i=0; i<2; i++) {

            nx += dx[d];
            ny += dy[d];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] != '#'){
                return false;
            }
        }

        return true;

    }


    static void dfs (int x, int y, int d, StringBuilder result) {

        int nx = x + dx[d]*2;
        int ny = y + dy[d]*2;

        map[x][y] = '.';
        for(int i=0; i<2; i++){
            x = x + dx[d];
            y = y + dy[d];
            map[x][y] = '.';
        }

        result.append('A');

        //왼쪽

        int leftD = 0;
        if (d == 0) {
            leftD = 3;
        }else {
            leftD = d-1;
        }

        if (checkMove(nx, ny, leftD)) {
            result.append('L');
            dfs(nx, ny,  leftD, result);
            return;
        }

        int rightD = (d + 1) % 4;

        if (checkMove(nx, ny, rightD)) {
            result.append('R');
            dfs(nx, ny, rightD, result);
            return;
        }

        if (checkMove(nx, ny, d)) {
            dfs(nx, ny, d, result);
            return;
        }
    }

}

