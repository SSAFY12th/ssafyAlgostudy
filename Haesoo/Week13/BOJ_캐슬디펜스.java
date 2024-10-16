import java.util.*;
import java.io.*;
public class Main {
    static int N, M, D;
    static int total = 0, ans = 0;
    static int[][] enemies = new int [15][15];
    static int[] archers = new int[3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                enemies[i][j] = Integer.parseInt(st.nextToken());
                if (enemies[i][j] == 1) total++;
            }
        }
        combi(0, 0);
        System.out.println(ans);
    }
    static void combi(int depth, int start) {
        if (depth == 3) {
            simul(total);
            return;
        }
        for (int i = start; i < M; i++) {
            archers[depth] = i;
            combi(depth + 1, i + 1);
        }
    }
    static void simul(int temptotal) {
        int[][] temp = new int[15][15];
        int curAns = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = enemies[i][j];
            }
        } // 현재 뽑은 조합 case에서 시뮬레이션 하기 위해 복사

        while (temptotal > 0) {
            if (curAns == total) {
                ans = curAns;
                return;
            }
            int[] maxRows = new int[M];
            int[][] targets = new int[3][2];
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (temp[j][i] != 0) maxRows[i] = Math.max(maxRows[i], j);
                }
            } // 각 열마다 가장 밑에 있는 적의 행 값 저장
            for (int i = 0; i < 3; i++) { // 각 궁수마다 열 별로 탐색
                int mindis = 1000;
                //for (int j = -1; j <= 1; j++) { // 궁수 기준 좌상, 상, 우상만 보려고 했는데 안되더라 ..
                for (int j = 0; j < M; j++) {
                    //if (archers[i].second + j < 0 || archers[i].second + j >= M) continue;
                    int curx = j;
                    int cury = maxRows[curx]; // 해당 열에서 가장 행이 큰 놈
                    int curdis = Math.abs(N - cury) + Math.abs(archers[i] - curx);

                    if (curdis < mindis) {
                        mindis = curdis;
                        targets[i][0] = cury;
                        targets[i][1] = curx;
                    } // 현재 거리가 더 작다면 궁수가 공격할 좌표 변경
                    if (curdis == mindis) {
                        if (targets[i][1] > curx) {
                            targets[i][0] = cury;
                            targets[i][1] = curx;
                        }
                    } // 현재 거리가 최솟값과 같다면 더 왼쪽에 있는지 비교 갱신
                }
                if (mindis > D) {
                    targets[i][0] = -1;
                    targets[i][1] = -1; // 공격 범위 벗어나면 체크하기
                }
            }
            for (int i = 0; i < 3; i++) {
                if (targets[i][0] != -1) { // 공격 범위 벗어나지 않았다면
                    if (temp[targets[i][0]][targets[i][1]] == 1) { // 공격 당하지 않은 적이라면
                        temp[targets[i][0]][targets[i][1]] = 0;
                        curAns++;
                        temptotal--;
                        maxRows[targets[i][1]] = -1;
                    }
                }
            }
            for (int i = N; i >= 1; i--) {
                for (int j = 0; j < M; j++) {
                    if (temp[i - 1][j] == 1) {
                        if (i <= N - 1) temp[i][j] = temp[i - 1][j];
                        else temptotal--;
                        temp[i - 1][j] = 0;
                    }
                }
            } // 적군 모두 이동
        }
        ans = Math.max(ans, curAns); // 공격으로 제거한 최대 적 수 갱신
    }
}
