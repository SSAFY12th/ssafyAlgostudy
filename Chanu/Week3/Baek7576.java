import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr = new int[1000][1000];
    static ArrayList<int[]> list = new ArrayList<>();
    static int[] dx= {0,1,0,-1};
    static int[] dy= {1,0,-1,0};
    static int day = 0;
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        for (int i=0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {

                int num = Integer.parseInt(st.nextToken());

                if (num == 1) {
                    list.add(new int[] {i,j});
                }

                arr[i][j] = num;

            }
        }

        bfs();

        for (int i=0; i<n; i++) {

            for (int j=0; j <m; j++) {
                if (arr[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(day-1);


    }
    static void bfs () {

        Queue<int[]> queue =  new ArrayDeque<>();
        queue.addAll(list);
        int len;

        while (!queue.isEmpty()) {

            len = queue.size();

            for (int i=0; i<len; i++) {

                int[] node = queue.poll();

                for (int j=0; j<4; j++) {

                    int nx = node[0] + dx[j];
                    int ny = node[1] + dy[j];

                    if (nx < 0 || nx >=n || ny <0 || ny >=m) {
                        continue;
                    }

                    if (arr[nx][ny] == 0) {
                        arr[nx][ny] = 1;
                        queue.offer(new int[]{nx,ny});
                    }

                }

            }

            day++;



        }
    }
}




