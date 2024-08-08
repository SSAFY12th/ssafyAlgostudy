import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static int answer = 0;
    static int[][] wheel = new int[4][8];

    // 바퀴를 돌리는 함수
    public static void turnWheel(int[] wheel, int direction) {

        // 시계 방향 회전
        if (direction == 1) {
            int temp = wheel[7];
            for (int i = 7; i > 0; i--) {
                wheel[i] = wheel[i - 1];
            }
            wheel[0] = temp;
            // 반시계 회전
        } else {
            int temp = wheel[0];
            for (int i = 0; i < 7; i++) {
                wheel[i] = wheel[i + 1];
            }
            wheel[7] = temp;
        }
    }

    public static void updateState(int wheelNum, int direction) {
        int[] directions = new int[4];

        // 바퀴가 돌아가기로 결정 되면 0이 아닌 값을 갖게 됨
        directions[wheelNum] = direction;

        // 왼쪽 바퀴들에 대한 회전 방향 결정
        for (int i = wheelNum; i > 0; i--) {
            if (wheel[i][6] != wheel[i - 1][2]) {
                directions[i - 1] = -directions[i];
            } else {
                break;
            }
        }

        // 오른쪽 바퀴들에 대한 회전 방향 결정
        for (int i = wheelNum; i < 3; i++) {
            if (wheel[i][2] != wheel[i + 1][6]) {
                directions[i + 1] = -directions[i];
            } else {
                break;
            }
        }

        // 모든 바퀴 회전 적용
        for (int i = 0; i < 4; i++) {
            if (directions[i] != 0) {
                turnWheel(wheel[i], directions[i]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheel[i][j] = str.charAt(j) - '0';
            }
        }

        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int wheelNum = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            updateState(wheelNum, direction);
        }

        if (wheel[0][0] == 1) {
            answer += 1;
        }
        if (wheel[1][0] == 1) {
            answer += 2;
        }
        if (wheel[2][0] == 1) {
            answer += 4;
        }
        if (wheel[3][0] == 1) {
            answer += 8;
        }

        System.out.println(answer);
    }
}
