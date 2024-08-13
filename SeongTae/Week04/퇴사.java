import java.util.*;
import java.io.*;

public class Main{
    static int N;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        for(int i=0;i<N;i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	arr[i][0] = Integer.parseInt(st.nextToken());
        	arr[i][1] = Integer.parseInt(st.nextToken());		
        }

        int[] dp = new int[N+1];
        for(int i=0;i<N;i++) {
        	dp[i+1] = Math.max(dp[i], dp[i+1]);
        	if(i+arr[i][0]<=N) {
        		dp[i+arr[i][0]] = Math.max(dp[i+arr[i][0]], dp[i] + arr[i][1]);
        	}
        }
       System.out.println(dp[N]);
    }       
}
