	
import java.io.*;
import java.util.*;

public class 탈주범검거 {
    static int n,m,startX,startY,t;
    static int[][] map;
    static boolean[][] visited;
    static int cnt;
    static HashMap<Integer, int[][]> pipe = new HashMap<>();


    public static void main(String[] args) throws IOException {

        int[][] arr1 = {{-1,0},{0,1},{1,0},{0,-1}};
        int[][] arr2 = {{-1,0},{1,0}};
        int[][] arr3 = {{0,1},{0,-1}};
        int[][] arr4 = {{-1,0},{0,1}};
        int[][] arr5 = {{0,1},{1,0}};
        int[][] arr6 = {{1,0},{0,-1}};
        int[][] arr7 = {{-1,0},{0,-1}};

        pipe.put(1,arr1);
        pipe.put(2,arr2);
        pipe.put(3,arr3);
        pipe.put(4,arr4);
        pipe.put(5,arr5);
        pipe.put(6,arr6);
        pipe.put(7,arr7);


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int test_case =1; test_case <=T; test_case ++) {

            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            cnt = 1;

            map = new int[n][m];
            visited = new boolean[n][m];

            for (int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<m; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());


                }
            }
            bfs(startX,startY);
            System.out.println("#"+test_case+" "+cnt);

        }

    }

    static void bfs(int x, int y) {

        int tim = 1;
        visited[x][y] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {

            if (tim == t) {
                break;
            }

            int len = queue.size();

            for (int i = 0; i < len; i++) {

                int[] node = queue.poll();
                x = node[0];
                y = node[1];
                int num = map[x][y];
                int[][] pipeNum = pipe.get(num);

                for (int j=0; j<pipeNum.length; j++) {

                    int nx = x + pipeNum[j][0];
                    int ny = y + pipeNum[j][1];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == 0) {
                        continue;
                    }

                    int linkPipe = map[nx][ny];
                    int[][] linkedPipe = pipe.get(linkPipe);

                    for (int z = 0; z < linkedPipe.length; z++) {

                        if (pipeNum[j][0] * -1 == linkedPipe[z][0] && pipeNum[j][1] * -1 == linkedPipe[z][1] && !visited[nx][ny] && map[nx][ny] !=0) {

                            visited[nx][ny] = true;
                            cnt++;
                            queue.offer(new int[]{nx, ny});
                            break;
                        }

                    }

                }

            }
            tim++;

        }

    }
}
