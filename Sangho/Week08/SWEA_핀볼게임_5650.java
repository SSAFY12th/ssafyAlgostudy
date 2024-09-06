package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PinBall {

    static StringTokenizer st;

    static int T;
    static int N;
    static int[][] board;
    static List<int[]> wormHoleList; // 웜홀 리스트
    static List<int[]> blackHoleList;

    // 상 하 좌 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            answer = Integer.MIN_VALUE;

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());

            board = new int[N][N];
            wormHoleList = new ArrayList<>(); // 웜홀 리스트 초기화
            blackHoleList = new ArrayList<>(); // 블랙홀 리스트 초기화

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    board[r][c] = Integer.parseInt(st.nextToken());
                    // 블랙홀 좌표 기록
                    if (board[r][c] == -1) {
                        blackHoleList.add(new int[]{r,c});
                    } else if (board[r][c] >= 6 && board[r][c] <= 10) {
                        // 웜홀 좌표 기록
                        wormHoleList.add(new int[]{r, c, board[r][c]});
                    }
                }
            }

            // 0인 지점에서 상하좌우 핀볼 다 쏴봄
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (board[r][c] == 0) {
                        for (int i = 0; i < 4; i++) {
                            simulation(r, c, i);
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    public static void simulation(int x, int y, int direction) {

        int nx = x;
        int ny = y;
        int score = 0;

        while (true) {
            nx += dx[direction];
            ny += dy[direction];

            boolean isBlackHole = false;

            // 현재 위치가 블랙홀인지 검증
            for(int[] blackHole : blackHoleList){
                if (blackHole[0] == nx && blackHole[1] == ny){
                    isBlackHole = true;
                    break;
                }
            }

            // 블랙홀 이면 그만
            if (isBlackHole) break;

            // 초기 위치로 돌아오면 그만
            if (nx == x && ny == y) break;


            // 벽에 튕기는 경우 처리
            // 왼쪽 벽에 튕기면
            if (ny < 0) {
                score++;
                // 우 방향으로 변환
                direction = 3;
                continue;
            } else if (ny >= N) {
                score++;
                direction = 2;
                continue;
            } else if (nx < 0) {
                score++;
                direction = 1;
                continue;
            } else if (nx >= N) {
                score++;
                direction = 0;
                continue;
            }

            // 블록에 튕기는 경우 처리
            if (board[nx][ny] >= 1 && board[nx][ny] <= 5) {
                switch (board[nx][ny]) {
                    // 1번 블록을 만났을때
                    case 1:
                        // 상 -> 하
                        if (direction == 0) direction = 1;
                        // 하 -> 우
                        else if (direction == 1) direction = 3;
                        // 좌 -> 상
                        else if (direction == 2) direction = 0;
                        // 우 -> 좌
                        else if (direction == 3) direction = 2;
                        break;
                    // 2번 블록을 만났을때
                    case 2:
                        // 상 -> 우
                        if (direction == 0) direction = 3;
                        // 하 -> 상
                        else if (direction == 1) direction = 0;
                        // 좌 -> 하
                        else if (direction == 2) direction = 1;
                        // 우 -> 좌
                        else if (direction == 3) direction = 2;
                        break;
                    // 3번 블록을 만났을때
                    case 3:
                        // 상 -> 좌
                        if (direction == 0) direction = 2;
                        // 하 -> 상
                        else if (direction == 1) direction = 0;
                        // 좌 -> 우
                        else if (direction == 2) direction = 3;
                        // 우 -> 하
                        else if (direction == 3) direction = 1;
                        break;
                    // 4번 블록을 만났을때
                    case 4:
                        // 상 -> 하
                        if (direction == 0) direction = 1;
                        // 하 -> 좌
                        else if (direction == 1) direction = 2;
                        // 좌 -> 우
                        else if (direction == 2) direction = 3;
                        // 우 -> 상
                        else if (direction == 3) direction = 0;
                        break;
                    // 5번 블록을 만났을때
                    case 5:
                        // 상 -> 하
                        if (direction == 0) direction = 1;
                        // 하 -> 상
                        else if (direction == 1) direction = 0;
                        // 좌 -> 우
                        else if (direction == 2) direction = 3;
                        // 우 -> 좌
                        else if (direction == 3) direction = 2;
                        break;
                }
                score++;
                continue;
            }

            // 웜홀에 빠지는 경우 처리
            if (board[nx][ny] >= 6 && board[nx][ny] <= 10) {
                for (int[] warmHall : wormHoleList) {
                    // 웜홀 번호는 같은데 좌표는 다른 웜홀 쌍을 찾는 과정임
                    if (warmHall[2] == board[nx][ny] && (nx != warmHall[0] || ny != warmHall[1])) {
                        nx = warmHall[0];
                        ny = warmHall[1];
                        break;
                    }
                }
            }
        }

        // 점수 갱신
        if (score > answer) {
            answer = score;
        }
    }
}
