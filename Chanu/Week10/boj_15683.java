	
김찬우/ 240924 / BOJ_도시분할계획_1647 / 1664 m/351892 kb
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 감시 {

    static int n;
    static int m;
    static int[][] map = new int[8][8];
    static int[][] copyMap;
    static List<int[]> list = new ArrayList<>();
    static int[] dx ={-1,0,1,0};
    static int[] dy ={0,1,0,-1};
    static HashMap<Integer, int[]> hashMap = new HashMap<>();
    static int[] result;
    static int cnt;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        hashMap.put(1,new int[]{1});
        hashMap.put(2,new int[]{1,3});
        hashMap.put(3,new int[]{0,1});
        hashMap.put(4,new int[]{0,1,3});
        hashMap.put(5,new int[]{0,1,2,3});

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] > 0 && map[i][j] !=6 ) {
                    list.add(new int[]{i,j});

                }

            }
        }
        cnt = list.size();
        result = new int[cnt];
        back(0);
        System.out.println(min);

    }

    static void back(int depth) {

        if (depth == cnt) {

            copyMap = new int[n][m];

            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    copyMap[i][j] = map[i][j];
                }
            }

            for (int i=0; i<cnt; i++) {
                int[] node = list.get(i);
                move(node[0],node[1],i);
            }

            int check = check();
            min = Math.min(check,min);
            return;
        }

        for (int i=0; i<4; i++) {
            result[depth] = i;
            back(depth+1);
        }

    }

    static void move(int x, int y, int count) {

        int node = map[x][y];

        int[] direct = hashMap.get(node);
        int d = result[count];

        int x1 = x;
        int y1 = y;

        for (int i = 0; i <direct.length; i++ ) {
            int dir = (direct[i] + d) % 4;

            while (true) {

                int nx = x1 + dx[dir];
                int ny = y1 + dy[dir];

                if (nx >= n || nx < 0 || ny >= m || ny < 0 || copyMap[nx][ny] > 5) {
                    break;
                }

                if (copyMap[nx][ny] == 0) {
                    copyMap[nx][ny] = -1;
                }

                x1 = nx;
                y1 = ny;

            }

            x1=x;
            y1=y;

        }

    }

    static int check() {
        int k =0;

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (copyMap[i][j] == 0) {
                    k++;
                }
            }
        }
        return k;
    }
}

