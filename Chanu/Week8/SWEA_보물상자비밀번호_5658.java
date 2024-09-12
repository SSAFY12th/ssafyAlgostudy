
import java.io.*;
import java.util.*;

public class 핀볼게임 {

    static int[][] map;
    static int n;
    static HashMap<Integer, List<Integer>> wormHall = new HashMap<>();
    static HashMap<Integer, int[]> diagram = new HashMap<>();
    static int cnt = 0;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int startX  = -1;
    static int startY = -1;
    static int d = -1;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        diagram.put(0,new int[]{-1,2,1,3,2,2});
        diagram.put(1,new int[]{-1,3,3,2,0,3});
        diagram.put(2,new int[]{-1,1,0,0,3,0});
        diagram.put(3,new int[]{-1,0,2,1,1,1});

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int test_case=1; test_case<=T; test_case++) {

            wormHall.clear();

            for (int i=6; i<=10; i++) {
                wormHall.put(i,new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine().trim());
            n = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            max = Integer.MIN_VALUE;
            startX  = -1;
            startY = -1;


            for (int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if (6 <= map[i][j] &&  map[i][j] <= 10) {
                        List<Integer> value = wormHall.get(map[i][j]);
                        value.add(i);
                        value.add(j);
                    }

                }
            }


            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {

                    if (map[i][j] != 0) {
                        continue;
                    }

                    for (int z = 0; z < 4; z++) {
                        cnt = 0;
                        d = z;
                        move(i,j);
                        max = Math.max(cnt,max);

                    }

                }
            }

            System.out.println("#"+test_case+" "+max);


        }
    }

    static void move(int xp, int yp) {

        startX = xp;
        startY = yp;

        while (true) {

            int nx = startX + dx[d];
            int ny = startY + dy[d];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                cnt++;
                d = ((d+2) % 4);
                startX = nx;
                startY = ny;
                continue;

            }else if (0 < map[nx][ny] && map[nx][ny] <= 5) {

                cnt++;
                d = diagram.get(d)[map[nx][ny]];
                startX = nx;
                startY = ny;
            }else if (6 <= map[nx][ny]) {

                List<Integer> point = wormHall.get(map[nx][ny]);

                if (nx == point.get(0) && ny == point.get(1)) {
                    startX = point.get(2);
                    startY = point.get(3);

                }else if (nx == point.get(2) && ny == point.get(3)) {
                    startX = point.get(0);
                    startY = point.get(1);
                }
            }else {

                startX = nx;
                startY = ny;
            }


            if (map[startX][startY] == -1 || (startX == xp && startY == yp)) {
                break;
            }


        }


    }

}
