import java.util.*;
import java.io.*;

public class Main {
    static int R, C, T;
    static int[][] map;
    static int[] airCleaner = new int[2];
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        int airCleanerIdx = 0;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    airCleaner[airCleanerIdx++] = i;
                }
            }
        }

        for (int t = 0; t < T; t++) {
            spreadDust();
            operateAirCleaner();
        }

        System.out.println(calculateDust());
    }

    static void spreadDust() {
        int[][] tempMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    int spreadAmount = map[i][j] / 5;
                    int spreadCount = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] != -1) {
                            tempMap[nx][ny] += spreadAmount;
                            spreadCount++;
                        }
                    }

                    tempMap[i][j] += map[i][j] - (spreadAmount * spreadCount);
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) {
                    tempMap[i][j] = -1;
                }
            }
        }

        map = tempMap;
    }

    static void operateAirCleaner() {
        int top = airCleaner[0];
        int bottom = airCleaner[1];

        for (int i = top - 1; i > 0; i--) map[i][0] = map[i - 1][0];
        for (int i = 0; i < C - 1; i++) map[0][i] = map[0][i + 1];
        for (int i = 0; i < top; i++) map[i][C - 1] = map[i + 1][C - 1];
        for (int i = C - 1; i > 1; i--) map[top][i] = map[top][i - 1];
        map[top][1] = 0;

        for (int i = bottom + 1; i < R - 1; i++) map[i][0] = map[i + 1][0];
        for (int i = 0; i < C - 1; i++) map[R - 1][i] = map[R - 1][i + 1];
        for (int i = R - 1; i > bottom; i--) map[i][C - 1] = map[i - 1][C - 1];
        for (int i = C - 1; i > 1; i--) map[bottom][i] = map[bottom][i - 1];
        map[bottom][1] = 0;
    }

    static int calculateDust() {
        int totalDust = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    totalDust += map[i][j];
                }
            }
        }
        return totalDust;
    }
}
