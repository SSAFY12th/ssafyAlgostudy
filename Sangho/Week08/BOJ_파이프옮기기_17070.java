import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static int N;
    static int[][] board;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new int[N + 1][N + 1]; // 1-based index 사용
        answer = 0;

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 1,2 좌표에서 가로로 시작 (mode 0: 가로, 1: 세로, 2: 대각선)
        DFS(1, 2, 0);

        System.out.println(answer);
    }

    public static void DFS(int x, int y, int mode) {
        // 도착 지점에 도달했을 때
        if (x == N && y == N) {
            answer++;
            return;
        }

        // 가로 모드
        if (mode == 0) {
            // 가로 -> 가로
            if (canMove(x, y + 1) && board[x][y + 1] != 1) DFS(x, y + 1, 0);
            // 가로 -> 대각선
            if (canMove(x + 1, y + 1) && canMove(x + 1, y) && canMove(x, y + 1)
                    && board[x + 1][y] != 1 && board[x][y + 1] != 1 && board[x + 1][y + 1] != 1)
                DFS(x + 1, y + 1, 2);
        }

        // 세로 모드
        else if (mode == 1) {
            // 세로 -> 세로
            if (canMove(x + 1, y) && board[x + 1][y] != 1) DFS(x + 1, y, 1);
            // 세로 -> 대각선
            if (canMove(x + 1, y + 1) && canMove(x + 1, y) && canMove(x, y + 1)
                    && board[x + 1][y] != 1 && board[x][y + 1] != 1 && board[x + 1][y + 1] != 1)
                DFS(x + 1, y + 1, 2);
        }

        // 대각 모드
        else if (mode == 2) {
            // 대각 -> 가로
            if (canMove(x, y + 1) && board[x][y + 1] != 1) DFS(x, y + 1, 0);
            // 대각 -> 세로
            if (canMove(x + 1, y) && board[x + 1][y] != 1) DFS(x + 1, y, 1);
            // 대각 -> 대각선
            if (canMove(x + 1, y + 1) && canMove(x + 1, y) && canMove(x, y + 1)
                    && board[x + 1][y] != 1 && board[x][y + 1] != 1 && board[x + 1][y + 1] != 1)
                DFS(x + 1, y + 1, 2);
        }
    }

    // 배열의 범위를 벗어나지 않는지 확인하는 메서드
    public static boolean canMove(int x, int y) {
        return x >= 1 && y >= 1 && x <= N && y <= N;
    }
}
