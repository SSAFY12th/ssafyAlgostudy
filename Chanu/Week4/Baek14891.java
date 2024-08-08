
import java.io.*;
import java.util.*;

public class Baek14891 {

    static int[][] arr = new int[4][2];
    static int[][] wheel = new int[4][8];


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i=0; i<4; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j=0; j<8; j++) {
                wheel[i][j] = str.charAt(j)-'0';
            }
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        init();

        for (int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int wheelNum = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            if (direction == 1) {
                clock(wheelNum-1);
            }else {
                disClock(wheelNum-1);
            }

            int left = direction;
            int right = direction;


            for (int j = wheelNum-1; j < 4; j++) {

                if (j == wheelNum-1) {
                    continue;
                }

                if (arr[j][0] == arr[j-1][1]) {
                    break;
                }else {
                    if (left == 1) {
                        disClock(j);
                        left = -1;
                    }else {
                        clock(j);
                        left = 1;
                    }
                }

            }


            for (int j = wheelNum-1; j>=0; j--) {

                if (j == wheelNum-1) {
                    continue;
                }

                if (arr[j][1] == arr[j+1][0]) {
                    break;
                }else {
                    if (right == 1) {
                        disClock(j);
                        right = -1;
                    }else {
                        clock(j);
                        right = 1;
                    }
                }

            }

            init();

        }

        int sum = 0;


        if (wheel[0][0] == 1) {
            sum+=1;
        }
        if (wheel[1][0] == 1) {
            sum+=2;
        }
        if (wheel[2][0] == 1) {
            sum+=4;
        }
        if (wheel[3][0] == 1) {
            sum+=8;
        }
        System.out.println(sum);



    }

    static void init () {

        for (int i=0; i<4; i++) {
            arr[i][0] = wheel[i][6];
            arr[i][1] = wheel[i][2];
        }
    }

    static void clock (int num) {

        int temp = wheel[num][7];

        for (int i=7; i>0; i--) {
            wheel[num][i] = wheel[num][i-1];
        }

        wheel[num][0] = temp;


    }

    static void disClock (int num) {

        int temp = wheel[num][0];

        for (int i=1; i<8; i++) {
            wheel[num][i-1] = wheel[num][i];
        }

        wheel[num][7] = temp;


    }

}
