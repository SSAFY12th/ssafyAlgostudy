import java.util.*;

public class Main {
    static int N, M;
    static int[][] room;
    static boolean[][] cleaned;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        int r = scanner.nextInt();
        int c = scanner.nextInt(); 
        int d = scanner.nextInt(); 

        room = new int[N][M];
        cleaned = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                room[i][j] = scanner.nextInt();
            }
        }

        clean(r, c, d);
        int cleanedCount = countCleanedCells();
        System.out.println(cleanedCount);
    }

    static void clean(int r, int c, int d) {
        cleaned[r][c] = true;
        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4;
            int nr = r + dx[d];
            int nc = c + dy[d];

            if (isValid(nr, nc) && !cleaned[nr][nc]) {
                clean(nr, nc, d);
                return;
            }
        }

        int backDir = (d + 2) % 4;
        int br = r + dx[backDir];
        int bc = c + dy[backDir];

        if (isValid(br, bc)) {
            clean(br, bc, d);
        }
    }

    static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M && room[r][c] == 0; // 방의 경계 및 벽 확인
    }

    static int countCleanedCells() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cleaned[i][j]) count++;
            }
        }
        return count;
    }
}
