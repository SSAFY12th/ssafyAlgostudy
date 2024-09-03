import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 이중 for문으로 2차원 배열에서 m개만큼 뽑는다.
2. m개 중 최댓값을 뽑아줌. dfs로
   dfs
     IF SUM > C return
     IF CNT == M return;
     다음 열을 더해주는 경우
     더해주지 않고 하나만 뽑는 경우
     둘다 cnt + 1
3. 일꾼 1의 최댓값을 뽑았으면 일꾼 2의 최댓값을 뽑아야 함.
    같은 열이라면 ! 자기 자신이 포함되지 않도록 하기위해 j열을 +M 만큼 미루고 시작
    다른 열이라면 0 부터 시작
    일꾼 2의 최댓값을 뽑아줌.
4. 둘이 더해서 최댓값 갱신.
 */
public class Main {

    private static int T, N, M, C;
    private static int[][] map;
    private static int man1, man2, max, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            //입력받기
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("#" + tc + " " + solution());

        }
    }
    private static int solution() {

        man1 = 0;
        man2 = 0;
        ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - M + 1; j++) {
                //일꾼1 벌통 최댓값 뽑기
                max = 0;
                maxHoney(i, j, 0, 0, 0);
                man1 = max;

                //일꾼2
                for (int r = i; r < N; r++) {
                    int start_c = i == r ? j + M : 0;
                    for (int c = start_c; c < N - M + 1; c++) {
                        max = 0;
                        maxHoney(r, c, 0, 0, 0);
                        man2 = max;
                        ans = Math.max(ans, man1 + man2);
                    }
                }


            }
        }
        return ans;
    }

    private static void maxHoney(int i, int j, int depth, int sum, int value) {

        if (sum > C) return;
        if (depth == M) {
            max = Math.max(max, value);
            return;
        }
        //하나를 뽑는 경우 부터 M개를 뽑는 경우까지 고려해보고 최댓값 뽑기
        maxHoney(i, j + 1, depth + 1, sum + map[i][j], value + (int) Math.pow(map[i][j], 2));
        maxHoney(i, j + 1, depth + 1, sum, value);
    }
}