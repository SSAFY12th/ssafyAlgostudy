
import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;
    static int[] toDx = {0,1,0,-1};
    static int[] toDy = {-1,0,1,0};
    static int d = 0;
    static int n;
    static int result =0;
    static HashMap<Integer,int[][]> moveType = new HashMap<>();
    static double[] col = {0.05,0.1,0.07,0.02,0.01,0.1,0.07,0.02,0.01};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[][] d1 = {{0,-2},{-1,-1},{-1,0},{-2,0},{-1,1},{1,-1},{1,0},{2,0},{1,1}};
        int[][] d2 = {{2,0},{1,-1},{0,-1},{0,-2},{-1,-1},{1,1},{0,1},{0,2},{-1,1}};
        int[][] d3 = {{0,2},{-1,1},{-1,0},{-2,0},{-1,-1},{1,1},{1,0},{2,0},{1,-1}};
        int[][] d4 = {{-2,0},{-1,1},{0,1},{0,2},{1,1},{-1,-1},{0,-1},{0,-2},{1,-1}};

        moveType.put(0,d1);
        moveType.put(1,d2);
        moveType.put(2,d3);
        moveType.put(3,d4);

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        spin();
        System.out.println(result);

    }

    private static void spin() {

        int x = n/2;
        int y = n/2;

        int count = 0;
        int max = 1;
        int sp = 0;

        while (true) {

            if (x == 0 && y == 0) {
                return;
            }

            x += toDx[d];
            y += toDy[d];
            count++;

            calculate(x,y);

            if(count == max) {
                d = (d+1) % 4;
                sp++;
                count = 0;
            }

            if(sp == 2) {
                max++;
                sp = 0;
            }
        }

    }

    static void calculate(int x, int y) {

        int[][] arr = moveType.get(d);
        int cnt = 0;

        for (int i=0; i<arr.length; i++) {

            int nx = x + arr[i][0];
            int ny = y + arr[i][1];


            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                result += (int)(map[x][y] * col[i]);

            }else {
                map[nx][ny] += (int)(map[x][y] * col[i]);
            }

            cnt += (int)(map[x][y] * col[i]);

        }

        int nx = x + toDx[d];
        int ny = y + toDy[d];

        if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
           result += (map[x][y] - cnt);
        }else {
            map[nx][ny] += (map[x][y] - cnt);
            map[x][y] = 0;
        }

    }

}
