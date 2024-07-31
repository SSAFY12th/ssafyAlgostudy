
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n,m;
    static int a,b;
    static char[][] arr;

    static boolean[][] visited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        visited = new boolean[n][m];

        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j=0; j<m; j++) {
                arr[i][j] = str.charAt(j);

            }
        }


        for (int i=0; i < n; i++) {
            for (int j=0; j<m; j++) {

                if (!visited[i][j] && arr[i][j] == 'W'){
                    visited[i][j] = true;
                    int sum = bfs(i, j, 'W',1);
                    a += (sum*sum);

                }else if (!visited[i][j] && arr[i][j] == 'B') {
                    visited[i][j] = true;
                    int sum = bfs(i, j, 'B',1);
                    b += (sum*sum);
                }

            }
        }

        System.out.println(a+" "+b);


    }

    static int bfs (int x, int y, char word, int cnt) {

        Queue<int[]> queue =  new LinkedList<>();

        queue.offer(new int[]{x,y});

        while (!queue.isEmpty()) {

            int[] node = queue.poll();

            x = node[0];
            y = node[1];

            for (int i=0; i<4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >=n || ny >= m) {
                    continue;
                }

                if (!visited[nx][ny] && arr[nx][ny] == word) {
                    visited[nx][ny] = true;
                    cnt++;
                    queue.offer(new int[]{nx,ny});
                }
            }
        }

        return cnt;
    }
}
