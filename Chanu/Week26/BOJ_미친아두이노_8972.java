

import java.io.*;
import java.util.*;

public class Baek8972 {

    static int n;
    static int m;
    static int moveCnt = 0;

    static Node[][] map;
    static int[] jongsu = new int[2];
    static List<int[]> robot = new ArrayList<>();

    static int[] dx = {0,1,1,1,0,0,0,-1,-1,-1};
    static int[] dy = {0,-1,0,1,-1,0,1,-1,0,1};

    static class Node{
        char type;
        int count;

        public Node(char type, int count) {
            this.type = type;
            this.count = count;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new Node[n][m];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j=0; j<m; j++) {
                char w = str.charAt(j);

                if (w == 'I') {
                    jongsu[0] = i;
                    jongsu[1] = j;
                    map[i][j] = new Node('I',1);

                }else if (w == 'R') {
                    robot.add(new int[]{i,j});
                    map[i][j] = new Node('R',1);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        String str = st.nextToken();

        String answer ="";
        boolean check = true;
        for (int i=0; i<str.length(); i++) {
            int d = str.charAt(i) - '0';

            if (!moveJongsu(d)) {
                answer = "kraj "+ moveCnt;
                check = false;
                break;
            }

            if (!moveR()){
                answer = "kraj "+ moveCnt;
                check = false;

                break;
            }
        }

        if(!check ) {
            System.out.println(answer);
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (map[i][j] == null) {
                    sb.append(".");
                }else {
                    sb.append(map[i][j].type);
                }

            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static boolean moveJongsu (int d) {

        moveCnt++;
        int x = jongsu[0];
        int y = jongsu[1];

        int nx = x + dx[d];
        int ny = y + dy[d];

        if (map[nx][ny] != null && map[nx][ny].type == 'R') {
            return false;
        }

        map[nx][ny] = new Node('I',1);

        if (x != nx || y != ny) {
            map[x][y] = null;
        }

        jongsu[0] = nx;
        jongsu[1] = ny;

        return true;

    }

    static boolean moveR() {

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                map[i][j] = null;
            }
        }

        map[jongsu[0]][jongsu[1]] = new Node('I',1);

        for (int[] ro : robot) {

            int min = Integer.MAX_VALUE;

            int x = ro[0];
            int y = ro[1];

            int rx = -1;
            int ry = -1;

            for (int i=1; i<=9; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= n || ny <0 || ny >= m) {
                    continue;
                }

                int dis = Math.abs(jongsu[0] - nx) + Math.abs(jongsu[1] - ny);

                if (min > dis) {
                    min = dis;
                    rx = nx;
                    ry = ny;
                }

            }

            if (map[rx][ry] != null) {
                if (map[rx][ry].type == 'I') {
                    return false;
                }else {
                    map[rx][ry].count++;
                }

            }else {
                map[rx][ry] = new Node('R',1);
            }
        }

        robot.clear();

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {

                if (map[i][j] == null) {
                    continue;
                }

                if (map[i][j].count == 1 && map[i][j].type == 'R') {
                    robot.add(new int[]{i,j});
                    continue;
                }

                if (map[i][j].count > 1) {
                    map[i][j] = null;
                }

            }
        }

        return true;
    }
}
