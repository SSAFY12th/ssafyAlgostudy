import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int R, C, T;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        List<Integer> coord = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1) coord.add(i);
            }
        }
        int[][] temp1 = new int[R][C];
        for (int i = 0; i < T; i++) {
            temp1 = map.clone();
            int[][] temp2 = new int[R][C];
            spread(temp1, temp2);
            up_rotate(coord.get(0));
            down_rotate(coord.get(1));
        }
        int dustcnt = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != 0) dustcnt += map[i][j];
            }
        }
        System.out.println(dustcnt + 2);
    }
    public static boolean isRange(int ny, int nx) {
        return ny >= 0 && nx >= 0 && ny < R && nx < C;
    }
    public static void spread(int[][] temp1, int[][] temp2) {
        // temp1은 해당 칸에 남을 먼지 양
        // temp2는 인접 칸들에 뿌려질 양
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                int dust = (map[y][x] / 5);
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int ny = y + dy[k];
                    int nx = x + dx[k];
                    if (isRange(ny, nx) && map[ny][nx] != -1) {
                        cnt++;
                        temp2[ny][nx] += dust;
                    }
                } // 확산 가능한 칸 세기
                temp1[y][x] = temp1[y][x] - dust * cnt;
            }
        }

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                map[y][x] = temp1[y][x] + temp2[y][x];
            }
        }
    }

    public static void up_rotate(int row) {
        int tempval = map[row][C - 1];
        for (int i = 0; i < C - 2; i++) { // →
            map[row][C - 1 - i] = map[row][C - 1 - i - 1];
        }
        map[row][1] = 0;
        int tempval2 = map[0][C - 1];
        for (int i = 0; i < row; i++) { // ↑
            map[i][C - 1] = map[i + 1][C - 1];
        }
        map[row - 1][C - 1] = tempval;
        tempval = map[0][0];
        for (int i = 0; i < C - 2; i++) { // ←
            map[0][i] = map[0][i + 1];
        }
        map[0][C - 2] = tempval2;
        for (int i = 1; i < row; i++) { // ↓
            map[row - i][0] = map[row - i - 1][0];
        }
        map[1][0] = tempval;
        map[row][0] = -1;
    }

    public static void down_rotate(int row) {
        int tempval = map[row][C - 1];
        for (int i = 0; i < C - 2; i++) { // →
            map[row][C - 1 - i] = map[row][C - 1 - i - 1];
        }
        map[row][1] = 0;
        int tempval2 = map[R - 1][C - 1];
        for (int i = 0; i < R - row - 1; i++) { // ↓
            map[R - 1 - i][C - 1] = map[R - 1 - i - 1][C - 1];
        }
        map[row + 1][C - 1] = tempval;
        tempval = map[R - 1][0];
        for (int i = 0; i < C - 2; i++) { // ←
            map[R - 1][i] = map[R - 1][i + 1];
        }
        map[R - 1][C - 2] = tempval2;

        for (int i = 1; i < R - row - 1; i++) { // ↑
            map[row + i][0] = map[row + i + 1][0];
        }
        map[R - 2][0] = tempval;
        map[row][0] = -1;
    }
}
