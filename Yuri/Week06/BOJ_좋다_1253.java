import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static long num[];
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        num = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++)
            num[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(num);

        // 음수도 입력될 수 있음.
        int s, e;
        long sum;
        for (int i = 0; i < n; i++) {
            s = 0;
            e = n-1;

            if(s == i) s++;
            if(e == i) e--;

            while(s < e) {
                sum = num[s]+num[e];
                if(sum == num[i]) {
                    if(s != i && e != i) {    // 현재값을 더한게 아니라면 
                        result++;             // 좋다!
                        break;
                    }
                    else if(s == i) s++;
                    else e--;
                }
                else if(sum > num[i]) {
                    e--;
                }
                else {
                    s++;
                }
            }
        }

        System.out.println(result);
    }
}
