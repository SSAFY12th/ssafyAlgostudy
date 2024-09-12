package swea;

import java.util.*;
import java.io.*;

public class Num5650 {

    static int n, score, startR, startC;
    static int[][] map;
    static List<WarmHall> warmHalls = new ArrayList<>();

    static class WarmHall {
        int num, r, c;

        public WarmHall(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
        }

        public boolean sameNum(WarmHall o) {
            if(o.num == num) {
                if(r != o.r || c != o.c)
                    return true;
            }
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine().trim());
            map = new int[n][n];
            warmHalls.clear();
            score = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] > 5 && map[i][j] < 11) {
                        warmHalls.add(new WarmHall(map[i][j], i, j));
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(map[i][j] == 0) {
                        startR = i;
                        startC = j;
                        //상하좌우 출발
                        go(startR - 1, startC, -1, 0);   // 상
                        go(startR + 1, startC, 1, 0);   // 하
                        go(startR, startC - 1, 0, -1);   // 좌
                        go(startR, startC + 1, 0, 1);   // 우
                    }
                }
            }

            System.out.println("#"+t+" "+score);

        }
    }

    // 전부 다 돌아야돼
    public static void go(int nowR, int nowC, int dr, int dc) {

        int tmpScore = 0;
        int moveR = nowR;
        int moveC = nowC;

        while(true) {

            if(moveR == startR && moveC == startC) {
                score = Math.max(score, tmpScore);
                return;
            }

            if(moveR >= 0 && moveC >= 0 && moveR < n && moveC < n) { // 맵 내부이면
                // 앞에 아무것도 없으면 now 좌표를 이동.
                // 앞에 웜홀이 있으면 now 좌표를 다른 웜홀로 이동.
                if(map[moveR][moveC] > 5 && map[moveR][moveC] < 11) {
                    WarmHall w = new WarmHall(map[moveR][moveC], moveR, moveC);
                    for (WarmHall ww : warmHalls) {
                        if(w.sameNum(ww)) {
                            nowR = ww.r;
                            nowC = ww.c;
                            break;
                        }
                    }
                }
                // 앞에 5번이 있으면 score++, 방향을 반대로 변경
                else if(map[moveR][moveC] == 5) {
                    tmpScore++;
                    dr *= -1;
                    dc *= -1;
                }
                // 1, 2, 3, 4번이 있는 경우,,, score++, 현재 방향과 비교하여 방향 변경.
                else if(map[moveR][moveC] > 0 && map[moveR][moveC] < 5) {
                    tmpScore++;
                    // 방향 변경.
                    switch (map[moveR][moveC]) {
                        case 1:
                            // 방향이 오른쪽 위쪽이면 반대방향.
                            if(dc == 1 || dr == -1) {
                                dr *= -1;
                                dc *= -1;
                            }
                            // 방향이 아래쪽이면 오른쪽으로 변경.
                            else if(dr == 1) {
                                dr = 0;
                                dc = 1;
                            }
                            // 방향이 왼쪽이면 위로.
                            else if(dc == -1) {
                                dr = -1;
                                dc = 0;
                            }
                            break;
                        case 2:
                            // 방향이 오른쪽 아래쪽이면 반대방향.
                            if(dc == 1 || dr == 1) {
                                dr *= -1;
                                dc *= -1;
                            }
                            // 방향이 왼쪽이면 아래쪽으로 변경.
                            else if(dc == -1) {
                                dr = 1;
                                dc = 0;
                            }
                            // 방향이 위쪽이면 오른쪽으로.
                            else {
                                dr = 0;
                                dc = 1;
                            }
                            break;
                        case 3:
                            // 방향이 왼쪽 아래쪽이면 반대방향.
                            if(dc == -1 || dr == 1) {
                                dr *= -1;
                                dc *= -1;
                            }
                            // 방향이 오른쪽이면 아래쪽으로 변경.
                            else if(dc == 1) {
                                dr = 1;
                                dc = 0;
                            }
                            // 방향이 위쪽이면 왼쪽으로.
                            else {
                                dr = 0;
                                dc = -1;
                            }
                            break;
                        case 4:
                            // 방향이 왼쪽 위쪽이면 반대방향.
                            if(dc == -1 || dr == -1) {
                                dr *= -1;
                                dc *= -1;
                            }
                            // 방향이 오른쪽이면 위쪽으로 변경.
                            else if(dc == 1) {
                                dr = -1;
                                dc = 0;
                            }
                            // 방향이 아래쪽이면 왼쪽으로.
                            else {
                                dr = 0;
                                dc = -1;
                            }
                            break;
                    }
                }
                // 블랙홀이 있으면... 게임 종료.
                else if(map[moveR][moveC] == -1) {
                    score = Math.max(score, tmpScore);
                    return;
                }
            }
            else {  // 맵에 부딫친거라 반대 방향으로 이동.
                tmpScore++;
                dr *= -1;
                dc *= -1;
            }
            nowR = moveR;
            nowC = moveC;
            moveR = nowR + dr;
            moveC = nowC + dc;
        }
    }
}
