import java.io.*;
import java.util.*;

public class Main {
    public static void turn(int[] wheel, int isCw) {
        if (isCw == 1) {
            int last = wheel[7];
            for(int i = 6; i >= 0; i--) wheel[i + 1] = wheel[i];
            wheel[0] = last;
        }
        else {
            int last = wheel[0];
            for (int j = 0; j <= 7; j++) wheel[j] = j < 7 ? wheel[j + 1] : last;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int [][] wheels = new int[4][8];
        //Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            //String next = sc.next();
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j < 8; j++) {
                //wheels[i][j] = next.charAt(j) - '0';
                wheels[i][j] = str.charAt(j)-'0';
            }
        }
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int curWheel = Integer.parseInt(st.nextToken());
            int isCw = Integer.parseInt(st.nextToken());

            boolean[] flags = new boolean[3];
            if (wheels[0][2] != wheels[1][6]) flags[0] = true; // 다르면 true (영향 O)
            if (wheels[1][2] != wheels[2][6]) flags[1] = true;
            if (wheels[2][2] != wheels[3][6]) flags[2] = true;

            if (curWheel == 1) { // 1번째 톱니 선택
                turn(wheels[0], isCw);
                if (flags[0]) turn(wheels[1], -isCw);
                if (flags[0] && flags[1]) turn(wheels[2], isCw);
                if (flags[0] && flags[1] && flags[2]) turn(wheels[3], -isCw);
            }

            else if (curWheel == 2) {
                turn(wheels[1], isCw);
                if (flags[0]) turn(wheels[0], -isCw);
                if (flags[1]) turn(wheels[2], -isCw);
                if (flags[1] && flags[2]) turn(wheels[3], isCw);
            }
            else if (curWheel == 3) {
                turn(wheels[2], isCw);
                if (flags[2]) turn(wheels[3], -isCw);
                if (flags[1]) turn(wheels[1], -isCw);
                if (flags[1] && flags[0]) turn(wheels[0], isCw);
            }
            else {
                turn(wheels[3], isCw);
                if (flags[2]) turn(wheels[2], -isCw);
                if (flags[2] && flags[1]) turn(wheels[1], isCw);
                if (flags[2] && flags[1] && flags[0]) turn(wheels[0], -isCw);
            }
        }
        int ans = 0;
        if (wheels[0][0] == 1) ans += 1;
        if (wheels[1][0] == 1) ans += 2;
        if (wheels[2][0] == 1) ans += 4;
        if (wheels[3][0] == 1) ans += 8;
        System.out.println(ans);
    }
}
