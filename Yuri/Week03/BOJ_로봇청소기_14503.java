import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] map = new int[50][50];
    static int r, c, d;
    static int result = 0;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    static boolean cleaning;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        clean();

        System.out.println(result);
    }

    public static void clean() {
        while(true) {
            cleaning = false;
            // 현재 칸을 청소.
            if(map[r][c] == 0) {
                result++;
                map[r][c] = 2;
            }

            // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
            for(int i = 0; i < 4; i++) {
                // 탐색해볼 칸이 맵 이내인지 체크
                if(canGo(i)) {
                    // 만약 그 칸이 0이라면
                    if(isBlanck(i)) {
                        cleaning = true;    // 청소하는 행위를 했음을 표시
                        // 반시계 방향으로 90도 회전
                        d = (d-1+4)%4;
                        // 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
                        if(canGo(d) && isBlanck(d)) {
                            r+=dr[d];
                            c+=dc[d];
                        }
                        break;
                    }
                }
            }

            // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
            if(!cleaning) {
                if(canGo((d-2+4)%4) && !isWall((d-2+4)%4)) {  // 후진이 가능하다면
                    r+=dr[(d-2+4)%4];
                    c+=dc[(d-2+4)%4];
                }
                else {
                    break;
                }
            }
        }

    }

    public static boolean canGo(int direction) {    // 이동하려는 위치가 맵 내인지 확인하는 메서드
        if(r+dr[direction] >= 0 && r+dr[direction] < n && c+dc[direction] >= 0 && c+dc[direction] < m)
            return true;
        else
            return false;
    }

    public static boolean isBlanck(int direction) { // 이동하려는 위치가 청소를 하지 않은 빈공간인지 확인하는 메서드
        if(map[r+dr[direction]][c+dc[direction]] == 0)
            return true;
        else
            return false;
    }

    public static boolean isWall(int direction) {   // 이동하려는 위치가 벽인지 확인하는 메서드
        if(map[r+dr[direction]][c+dc[direction]] == 1)
            return true;
        else
            return false;
    }

}
