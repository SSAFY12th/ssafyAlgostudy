import java.util.*;
import java.io.*;

public class Main {
	static int N, M, K, T;
	static int[] homes;
	static int[] S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			if(N != M) {
				homes = new int[N + M - 1];
				S = new int[N + M];
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < N; i++) {
					homes[i] = Integer.parseInt(st.nextToken());
				}

				for (int i = N; i < homes.length; i++) {
					homes[i] = homes[i - N];
				}				
			}
			else {
				homes = new int[N];
				S = new int[N + 1];
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < N; i++) {
					homes[i] = Integer.parseInt(st.nextToken());
				}				
			}

			for (int i = 1; i < S.length; i++) {
				S[i] = S[i - 1] + homes[i - 1];
			}
			sb.append(cal()).append("\n");
		}
		System.out.println(sb);
	}

	static int cal() {
		int cnt = 0;
		for (int i = M; i < S.length; i++) {
			if (S[i] - S[i - M] < K) {
				cnt++;
			}
		}
		return cnt;
	}
}
