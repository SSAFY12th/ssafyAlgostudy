import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] wheels = new int[4][8];
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheels[i][j] = str.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            chkLeftWheel(num - 1, -direction); // 왼쪽으로 가면서 검사 시작
            chkRightWheel(num + 1, -direction); // 오른쪽으로 가면서 검사 시작
            rotate(num, direction);
        }

        for (int i = 0; i < 4; i++) {
            if (wheels[i][0] == 1) {
                ans += Math.pow(2, i);
            }
        }

        System.out.println(ans);
    }

    //처음 회전한 톱니바퀴의 왼쪽에 있는 톱니바퀴들 검사
    public static void chkLeftWheel(int num, int direction) {
        if (num < 0 || wheels[num][2] == wheels[num + 1][6]) {  // 톱니바퀴 개수의 범위 밖이거나 맞닿아있는 극이 같다면 리턴
            return;
        }
        chkLeftWheel(num - 1, -direction); // 재귀로 방향만 바꿔서 검사!
        rotate(num, direction); // 톱니바퀴 개수 범위 안임과 동시에 맞닿아 있는 극이 다르다면 회전!
    }

    //처음 회전한 톱니바퀴의 오른쪽에 있는 톱니바퀴들 검사
    public static void chkRightWheel(int num, int direction) {
        if (num > 3 || wheels[num][6] == wheels[num - 1][2]) { // 톱니바쿠 개수의 범위 밖이거나 맞닿아있는 극이 같다면 리턴
            return;
        }
        chkRightWheel(num + 1, -direction); // 재귀로 방향만 바꿔서 검사!
        rotate(num, direction); //톱니바퀴 개수 범위 안임과 동시에 맞닿아 있는 극이 다르다면 회전!
    }

    //배열 회전하는 메서드
    public static void rotate(int num, int direction) {
        if (direction == 1) { //시계방향
            int temp = wheels[num][7];
            for (int i = 7; i > 0; i--) {
                wheels[num][i] = wheels[num][i - 1];
            }
            wheels[num][0] = temp;
        }else {  //반시계 방향
            int temp = wheels[num][0];
            for (int i = 0; i < 7; i++) {
                wheels[num][i] = wheels[num][i + 1];
            }
            wheels[num][7] = temp;
        }
    }
}