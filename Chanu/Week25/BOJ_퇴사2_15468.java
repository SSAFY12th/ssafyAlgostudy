
import java.io.*;
import java.util.*;

public class Main{

    static int n;
    static int max;
    static int [][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int [n+1][2];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0]=Integer.parseInt(st.nextToken());
            arr[i][1]=Integer.parseInt(st.nextToken());
        }
        dp();
        System.out.println(max);
    }

    static void dp() {
        int [] dp = new int[n+2];
        for(int i=1; i<=n; i++) {
            int next = i + arr[i][0];
            if(next <= n+1) {
                dp[next] = Math.max(dp[i] + arr[i][1], dp[next]);
            }
            dp[i+1]= Math.max(dp[i],  dp[i+1]);
        }
        max = dp[n+1];
    }

}
