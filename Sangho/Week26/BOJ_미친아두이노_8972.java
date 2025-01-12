package algorithm;

import java.io.*;
import java.util.*;

public class Main {
    static int R, C; // 보드 크기
    static char[][] board; // 보드 상태
    static int[] moves; // 종수의 명령
    static int[][] directions = {
            {0, 0}, // 제자리 (0번 명령)
            {1, -1}, {1, 0}, {1, 1}, // 1~3번 명령
            {0, -1}, {0, 0}, {0, 1}, // 4~6번 명령
            {-1, -1}, {-1, 0}, {-1, 1} // 7~9번 명령
    };
    static int jongSuX, jongSuY; // 종수의 위치
    static List<int[]> crazyArduinos = new ArrayList<>(); // 미친 아두이노 위치 목록

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 보드 입력 받기
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'I') {
                    jongSuX = i;
                    jongSuY = j;
                } else if (board[i][j] == 'R') {
                    crazyArduinos.add(new int[]{i, j});
                }
            }
        }

        // 종수 명령 입력 받기
        String commands = br.readLine();
        moves = new int[commands.length()];
        for (int i = 0; i < commands.length(); i++) {
            moves[i] = commands.charAt(i) - '0';
        }

        // 종수 명령 만큼 반복
        for (int turn = 0; turn < moves.length; turn++) {

            int move = moves[turn];
            jongSuX += directions[move][0];
            jongSuY += directions[move][1];

            // 종수가 아두이노 밟으면 끝
            if (board[jongSuX][jongSuY] == 'R') {
                System.out.println("kraj " + (turn + 1));
                return;
            }

            board = new char[R][C];
            board[jongSuX][jongSuY] = 'I';
            Map<String, Integer> newPositions = new HashMap<>();
            List<int[]> newCrazyArduinos = new ArrayList<>();

            // 미친 아두이노들 순회
            for (int[] crazy : crazyArduinos) {
                int cx = crazy[0];
                int cy = crazy[1];
                int bestX = cx, bestY = cy, minDist = Integer.MAX_VALUE;

                // 종수랑 제일 가까운 커맨드 찾기
                for (int d = 1; d <= 9; d++) {
                    int nx = cx + directions[d][0];
                    int ny = cy + directions[d][1];
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

                    int dist = Math.abs(nx - jongSuX) + Math.abs(ny - jongSuY);
                    if (dist < minDist) {
                        bestX = nx;
                        bestY = ny;
                        minDist = dist;
                    }
                }

                // 게임이 끝나면 출력 하는 거
                if (bestX == jongSuX && bestY == jongSuY) {
                    System.out.println("kraj " + (turn + 1));
                    return;
                }

                String key = bestX + "," + bestY;
                newPositions.put(key, newPositions.getOrDefault(key, 0) + 1);
                newCrazyArduinos.add(new int[]{bestX, bestY});
            }

            crazyArduinos.clear();
            for (int[] crazy : newCrazyArduinos) {
                String key = crazy[0] + "," + crazy[1];
                if (newPositions.get(key) == 1) {
                    crazyArduinos.add(crazy);
                    board[crazy[0]][crazy[1]] = 'R';
                }
            }
        }

        // 맵 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != 'I' && board[i][j] != 'R') {
                    sb.append('.');
                } else {
                    sb.append(board[i][j]);
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
