import java.util.*;
import java.io.*;

public class Solution {

    static int D, W, K;
    static int[][] film;
    static int min; // 최소 약품 투입 횟수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            film = new int[D][W];
            min = K;

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if(K == 1)
                min = 0;
            else
                dfs(0, 0);

            sb.append("#").append(t).append(" ").append(min).append("\n");

        }
        System.out.println(sb.toString());
    }

    public static void dfs(int R, int cnt) {

        if(cnt >= min) {    // 이미 최소 약품투입 횟수보다 많이 변경했다면 더 진행할 필요 x
            return;
        }

        // 변경을 끝내고 마지막에 도착했을 때
        if(R == D) {
            int sameCnt = 0;
            for (int c = 0; c < W; c++) {
                int same = 1;   // 처음 1개는 무조건 포함.
                for (int r = 0; r < D-1; r++) {
                    if(film[r][c] == film[r+1][c]) {
                        if(++same == K) {
                            sameCnt++;
                            break;
                        }
                    }
                    else {
                        same = 1;
                    }
                }
            }
            if(sameCnt == W) {
                min = Math.min(min, cnt);
            }
            return;
        }

        int[] tmp = film[R].clone();    // 원본 복사

        dfs(R+1, cnt);  // 약품 투입 x
        
        Arrays.fill(film[R], 0);    // A약품 투입
        dfs(R+1, cnt+1);

        Arrays.fill(film[R], 1);    // B약품 투입
        dfs(R+1, cnt+1);

        film[R] = tmp;  // 복원

    }
}


/*
보호 필름은 얇은 투명한 막을 D장 쌓아서 제작함.
가로로 W개. -> 배열[D][W]

셀은 특성 A또는 B -> int[][] film = new int[D][W]
보호필름 성능검사 합격기준 K
충격은 세로 방향으로 가해짐. 세로 방향의 셀 특성이 중요.
***세로로 동일한 특성의 셀이 K개 이상 연속적으로 있는 경우 성능검사 통과.
성능검사 통과를 위해 약품을 사용해야함.
약품은 가로로 투입 가능. 해당 가로줄은 모두 A 또는 B하나로 변경됨.
성능검사에서 약품 최소 투입횟수는??? 없을수도 있다.
 */

/*
성능 검사 시, 한 열만 통과된다고 되는게 아니라 모든 열에 K개 이상 중복되는 셀이 있어야함.
중복이 반드시 하나로만 될 필요는 없음. A or B 중 아무거나 K개 이상 연속되면 됨.
 */
