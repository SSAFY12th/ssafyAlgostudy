

import java.io.*;
import java.util.*;

public class Main {

    static char[][][] map;
    static int[][][] visited;
    static int[] dx = {0, 0, -1, 0, 1, 0};
    static int[] dy = {0, 0, 0, 1, 0, -1};
    static int[] dz = {-1, 1, 0, 0, 0, 0};
    static int[] start = new int[]{0,0,0};
    static int[] end = new int[]{0,0,0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {

            start = new int[]{0,0,0};
            end = new int[]{0,0,0};

            st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(l==0 && r==0 && c==0){
                break;
            }

            map = new char[l][r][c];
            visited = new int[l][r][c];

            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    String str = br.readLine();
                    for (int k = 0; k < c; k++) {

                        map[i][j][k] = str.charAt(k);

                        if (map[i][j][k] == 'S') {
                            start[0] = i;
                            start[1] = j;
                            start[2] = k;
                        }else if (map[i][j][k] == 'E') {

                             end[0] = i;
                             end[1] = j;
                             end[2] = k;
                        }
                    }
                }
                br.readLine();
            }

            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{start[0], start[1], start[2]});

            while (!q.isEmpty()) {
                int[] node = q.poll();
                int z = node[0];
                int x = node[1];
                int y = node[2];

                for (int i = 0; i < 6; i++) {
                    int nz = z + dz[i];
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nz < 0 || nx >= r || ny >= c || nz >= l) {
                        continue;
                    }

                    if (map[nz][nx][ny] == '#' || visited[nz][nx][ny] != 0) {
                        continue;
                    }

                    q.offer(new int[]{nz, nx, ny});
                    visited[nz][nx][ny] = visited[z][x][y] + 1;
                }

            }

            int result = visited[end[0]][end[1]][end[2]];

            if(result == 0)
                System.out.println("Trapped!");
            else{
                System.out.println("Escaped in " + result + " minute(s).");
            }

        }

    }
}
