
package baek;

import java.io.*;
import java.util.*;

public class 미세먼지안녕 {

    static int[][] map;
    static int[][] copyMap;
    static int n;
    static int m;
    static int T;
    static int[] dxUp = {0,-1,0,1};
    static int[] dyUp = {1,0,-1,0};
    static int[] dxDown = {0,1,0,-1};
    static int[] dyDown = {1,0,-1,0};
    static int d1 = 0;
    static int d2 = 0;
    static int[] robot = {0,0};
    static int result = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        copyMap = new int[n][m];

        int c = 0;
        for (int i=0; i<n; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j =0; j<m; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == -1) {
                    robot[c++] = i;
                }

            }
        }

        for (int t=1; t <= T; t++) {
            spread();
            conduct(robot[0],dxUp,dyUp,d1);
            conduct(robot[1],dxDown,dyDown,d2);

        }

        
        for (int i=0; i<n; i++) {
            for (int j =0; j<m; j++) {
                if (map[i][j] > 0){
                    result += map[i][j];
                }
            }

        }

        System.out.println(result);
        
    }

    static void spread() {

        copyMap = new int[n][m];
        copyMap[robot[0]][0] = -1;
        copyMap[robot[1]][0] = -1;

        for (int i=0; i<n; i++) {

            for (int j =0; j<m; j++) {
                if (map[i][j] > 0) {

                    int cnt = 0;

                    for (int z=0; z<4; z++) {

                        int nx = i + dxUp[z];
                        int ny = j + dyUp[z];

                        if (nx <0 || nx >= n || ny <0 || ny >=m || map[nx][ny] == -1) {
                            continue;
                        }

                        copyMap[nx][ny] += map[i][j] / 5;
                        cnt++;
                    }
                    copyMap[i][j] += (map[i][j] - ((map[i][j] / 5) * cnt));

                }
            }
        }
        
        for (int i=0; i<n; i++) {
            for (int j =0; j<m; j++) {
                map[i][j] = copyMap[i][j];
            }
        }

    }

    static void conduct(int robo, int[] dx, int[] dy, int d) {

        int x = robo;
        int y = 0;
        int temp1 = 0;
        int temp2 = 0;

        while (true) {

            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx <0 || nx >= n || ny <0 || ny >=m) {
                d = (d+1) % 4;
                continue;
            }

            if (map[nx][ny] == -1) {
                break;
            }

            temp2 = map[nx][ny];
            map[nx][ny] = temp1;
            temp1 = temp2;

            x = nx;
            y = ny;

        }

    }
}
