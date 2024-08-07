
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n,m,d,nd;
    static int startX;
    static int startY;
    static int[][] arr;


    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};


    static int cnt = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i=0; i <n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j < m; j++) {
                arr[i][j] =Integer.parseInt(st.nextToken());
            }
        }


        while (true) {

            if (arr[startX][startY] == 0) {
                arr[startX][startY] = 2;
                cnt++;
                continue;
            }

            if (check()) {

                nd = d;

                for (int i=0; i<4; i++) {

                    nd = (nd + 3) % 4;
                    int nx = startX + dx[nd];
                    int ny = startY + dy[nd];

                    if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                        if (arr[nx][ny] == 0) {
                            startX = nx;
                            startY = ny;
                            d = nd;
                            break;
                        }
                    }

                }


            }else {

                int nx = startX + dx[(d+2)%4];
                int ny = startY + dy[(d+2)%4];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m ) {
                    if (arr[nx][ny] == 2) {
                        startX = nx;
                        startY = ny;

                    }else if (arr[nx][ny] == 1) {
                        break;
                    }

                }
            }

        }


        System.out.println(cnt);


    }

    static boolean check() {

        boolean check = false;

        for (int i=0; i<4; i++) {

            int nx = startX + dx[i];
            int ny = startY + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m || arr[nx][ny] == 1 || arr[nx][ny] == 2) {
                continue;
            }

            if (arr[nx][ny] == 0) {
                check = true;
                break;
            }

        }

        return check;
    }
}



