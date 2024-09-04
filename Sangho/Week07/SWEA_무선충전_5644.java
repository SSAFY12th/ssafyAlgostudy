import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

class BC {
    int x, y, coverage, power;

    public BC(int x, int y, int coverage, int power) {
        this.x = x;
        this.y = y;
        this.coverage = coverage;
        this.power = power;
    }

    // 범위안에 사용자가 있는지 체크하는 메서드
    public boolean isInRange(int userX, int userY) {
        return Math.abs(this.x - userX) + Math.abs(this.y - userY) <= this.coverage;
    }
}

public class Solution {
    static int[] dx = {0, -1, 0, 1, 0}; // 이동 방향 x (0: 정지, 1: 상, 2: 우, 3: 하, 4: 좌)
    static int[] dy = {0, 0, 1, 0, -1}; // 이동 방향 y

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());

            // 사용자의 이동 정보
            int[] moveA = new int[M + 1];
            int[] moveB = new int[M + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                moveA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                moveB[i] = Integer.parseInt(st.nextToken());
            }

            // BC 정보가 들어있는 리스트
            List<BC> aps = new ArrayList<>();
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int coverage = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());
                aps.add(new BC(x, y, coverage, power));
            }

            // 사용자 초기 좌표
            int ax = 1, ay = 1, bx = 10, by = 10;

            // 총 충전량
            int totalCharge = 0;

            // 사용자의 이동 전략을 따라감
            for (int i = 0; i <= M; i++) {
                ax += dx[moveA[i]];
                ay += dy[moveA[i]];
                bx += dx[moveB[i]];
                by += dy[moveB[i]];

                int maxCharge = 0;
                // 무선 충전기를 돌아봄
                for (int j = 0; j < A; j++) {
                    for (int k = 0; k < A; k++) {
                        int chargeA = 0, chargeB = 0;
                        // 배터리 범위안에 사용자가 들어오면 파워계산
                        if (aps.get(j).isInRange(ax, ay)) chargeA = aps.get(j).power;
                        if (aps.get(k).isInRange(bx, by)) chargeB = aps.get(k).power;

                        // 같은 배터리일 경우 둘 중에 하나만
                        if (j == k) {
                            maxCharge = Math.max(maxCharge, chargeB);

                        // 아닌경우 둘 충전량 다 더해줌
                        } else {
                            maxCharge = Math.max(maxCharge, chargeA + chargeB);
                        }
                    }
                }

                totalCharge += maxCharge;
            }

            System.out.println("#" + t + " " + totalCharge);
        }
    }
}
