import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] day = new int[N];
		int[] pay = new int[N];
		int[] dp = new int[N + 1];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			day[i] = Integer.parseInt(st.nextToken());
			pay[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			dp[i + 1] = Math.max(dp[i], dp[i + 1]);

			if (i + day[i] <= N) {
				dp[i + day[i]] = Math.max(dp[i + day[i]], dp[i] + pay[i]);
			}
		}
		System.out.println(dp[N]);
	}
}
