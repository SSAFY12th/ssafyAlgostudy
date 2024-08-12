import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;
    static int t, max;
    static String s;
    static int changeNum;
    static int[] all;

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= t; test_case++) {

            st = new StringTokenizer(br.readLine());
            s = st.nextToken();
            max = Integer.MIN_VALUE;    // 최대 상금을 저장할 변수
            all = new int[s.length()];  // 숫자 위치 변경을 용이하게 하기 위해 배열로 저장
            for(int i = 0; i < all.length; i++)
                all[i] = s.charAt(i) - '0';
            changeNum = Integer.parseInt(st.nextToken());

            dfs(0);

            System.out.println("#"+test_case+" "+max);

        }
    }

    public static void dfs(int cnt) {
        // 모든 케이스를 전부 탐색하면 시간 초과. 숫자의 길이를 초과하는 교환은 할 필요 없다.
        if(cnt == changeNum || cnt == all.length) {
            int result = 0;
            for(int n : all)
                result = result*10 + n;
            max = Math.max(max, result);
            return;
        }

        for(int i = 0; i < all.length-1; i++) {
            for(int j = i+1; j < all.length; j++) {
                int tmp = all[i];
                all[i] = all[j];
                all[j] = tmp;
                dfs(cnt+1);
                tmp = all[i];
                all[i] = all[j];
                all[j] = tmp;
            }
        }
    }
}
