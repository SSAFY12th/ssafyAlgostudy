package algo.bfs;

import java.io.*;
import java.util.*;

public class Baek2468 {

    static int n;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx= {1,-1,0,0};
    static int[] dy= {0,0,1,-1};
    static Queue<int[]> queue = new ArrayDeque<>();
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for (int j=0; j<n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        for (int h=0; h<=100; h++) {

            visited = new boolean[n][n];
            queue.clear();

            int sum = 0;

            for (int i=0; i<n; i++) {

                for (int j=0; j<n; j++) {

                    if (arr[i][j] > h && !visited[i][j]) {
                        visited[i][j] = true;
                        bfs(i,j,h);
                        sum++;

                    }

                }

            }

            max = Math.max(max,sum);

        }

        System.out.println(max);


    }
    static void bfs(int x, int y, int height) {



        queue.offer(new int[]{x,y});

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            x = node[0];
            y = node[1];

            for (int i=0; i<4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >=n) {
                    continue;
                }

                if (!visited[nx][ny] && arr[nx][ny] > height ) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx,ny});
                }
            }
        }

    }
}
