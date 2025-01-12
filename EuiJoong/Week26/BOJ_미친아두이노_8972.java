import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class CrazyArduino {
        int r;
        int c;

        public CrazyArduino(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class MyArduino {
        int r;
        int c;

        public MyArduino(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int R, C;
    static char[][] board;
    static int[][] dir = {
            {},
            {1, -1}, // 1번 좌하
            {1, 0}, // 2번 하
            {1, 1}, // 3번 우하
            {0, -1}, // 4번 좌
            {0, 0}, // 5번 제자리
            {0, 1}, // 6번 우
            {-1, -1}, // 7번 좌상
            {-1, 0}, // 8번 상
            {-1, 1} // 9번 우상
    };
    static List<Character>[][] tmpBoard;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        String command = br.readLine();
        boolean chk = true;
        int cnt = 0;
        for (int i = 0; i < command.length(); i++) {
            int number = command.charAt(i) - '0';
            MyArduino myArduino = findMyArduino();
            /**
             * 여기서 시뮬 들어가야 함
             * 1. 종수 아두이노는 number에 따라 이동 (미친아두이노 만나면 펑)
             * 2. 미친 아두이노는 종수 아두이노랑 가까워 지는 방향으로 이동 (종수아두이노 만나면 평)
             * 3. 이동 후 아두이노가 뭉쳐 있는곳 확인.
             */
            cnt++;
            chk = simulation(number, myArduino);
            if (!chk) {
                System.out.println("kraj " + cnt);
                break;
            }
        }
        if (chk) {
            writeAnswer();
        }
    }

    private static boolean simulation(int command, MyArduino myArduino) {
        tmpBoard = new List[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                tmpBoard[i][j] = new ArrayList<>();
            }
        }

        List<CrazyArduino> crazyArduinoList = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'R') {
                    crazyArduinoList.add(new CrazyArduino(i, j));
                }
            }
        }

        // 1. 종수 아두이노 이동
        // 검증해줘야 함. 미친아두이노 없는 칸인지
        int nr = myArduino.r + dir[command][0];
        int nc = myArduino.c + dir[command][1];

        for (CrazyArduino crazyArduino : crazyArduinoList) {
            if (nr == crazyArduino.r && nc == crazyArduino.c) {
                return false; //종수 아두이노가 미친 아두이노로 가면 펑!
            }
        }

        tmpBoard[nr][nc].add('I');

        // 2. 미친 아두이노 이동
        for (CrazyArduino crazyArduino : crazyArduinoList) {
            int finalDistance = Integer.MAX_VALUE;
            int r = 0;
            int c = 0;
            for (int i = 1; i <= 9; i++) {
                if (i == 5) continue;
                int nr_crazy = crazyArduino.r + dir[i][0];
                int nc_crazy = crazyArduino.c + dir[i][1];
                int distance = operateDistance(nr, nc, nr_crazy, nc_crazy);
                if (nr_crazy >= 0 && nr_crazy < R && nc_crazy >= 0 && nc_crazy < C) {
                    if (distance <= finalDistance) {
                        finalDistance = distance;
                        r = nr_crazy;
                        c = nc_crazy;
                    }
                }
            }
            if (nr == r && nc == c) {
                return false; //미친 아두이노가 종수 아두이노로 가면 펑!
            }
            tmpBoard[r][c].add('R');
        }

        // 3. 미친 아두이노 뭉쳐있는 곳 확인하여 제거햐주기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (tmpBoard[i][j].size() > 1) {
                    tmpBoard[i][j].clear();
                }
            }
        }

        //원본에 복사를 해줘야 함
        copyBoard();
        return true;
    }

    private static int operateDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    private static MyArduino findMyArduino() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'I') {
                    return new MyArduino(i, j);
                }
            }
        }
        return null;
    }

    private static void copyBoard() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (tmpBoard[i][j].size() == 1) {
                    board[i][j] = tmpBoard[i][j].get(0);
                } else {
                    board[i][j] = '.';
                }
            }
        }
    }

    private static void writeAnswer() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
