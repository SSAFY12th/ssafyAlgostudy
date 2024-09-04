
import java.io.*;
import java.util.*;

public class PuyoPuyo {

    static char[][] map = new char[12][6];
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static boolean[][] visited;
    static Stack<Character> stack = new Stack<>();
    static int cnt = 0;
    static List<int[]> list = new ArrayList<>();
    static int t = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = 0;

        for (int i=0;i<12; i++) {
            String str = br.readLine();
            for (int j=0;j<6;j++) {
                map[i][j] = str.charAt(j);
            }
        }

        while (true) {

            visited = new boolean[12][6];
            boolean check = false;

            for (int i=0; i<12; i++) {
                for (int j=0;j<6;j++) {
                    if (!visited[i][j] && map[i][j] != '.') {
                        list.clear();
                        list.add(new int[]{i,j});
                        cnt = 1;
                        bfs(i,j,map[i][j]);

                        if (cnt >=4) {
                            check = true;
                            for (int[] ints : list) {
                                map[ints[0]][ints[1]] = '.';
                            }
                        }

                    }

                }
            }

            if (!check) {
                break;
            }

            t++;
            gravity();

        }

        System.out.println(t);


    }

    static void gravity() {

        for (int j=0; j<6; j++) {

            stack.clear();

            for (int i=0; i<12; i++) {

                if (map[i][j] != '.') {
                    stack.push(map[i][j]);
                }
            }

            int index = 11;
            while (!stack.isEmpty()) {
                map[index][j] = stack.pop();
                index--;
            }
            for (int i = index; i >=0; i--) {
                map[i][j] = '.';
            }

        }


    }

    static void bfs(int x ,int y, char type) {

        visited[x][y] = true;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x,y});


        while (!queue.isEmpty()) {

            int[] node = queue.poll();
            x = node[0];
            y = node[1];

            for (int i=0; i<4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >=12 || ny < 0 || ny >= 6) {
                    continue;
                }

                if (!visited[nx][ny] && map[nx][ny] == type) {
                    cnt++;
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx,ny});
                    list.add(new int[]{nx,ny});
                }

            }

        }



    }

    static void pr() {
        for (int i=0; i<12; i++) {
            for (int j=0;j<6;j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
