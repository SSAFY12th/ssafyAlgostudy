import java.util.*;
import java.io.*;

public class 보물상자비밀번호 {
	static int T, N, K, ans;
	static StringBuilder line;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ans = 0;
			line = new StringBuilder(br.readLine());
			addLine();

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void addLine() {
		line.append(line.substring(0, N / 4));
		List<Integer> pass = new ArrayList<>();
		for (int j = 0; j < N; j++) {
			String s = line.substring(j, j + N / 4);
			int value = Integer.parseInt(s, 16);
			if (!pass.contains(value)) {
				pass.add(value);
			}
		}
		Collections.sort(pass, Collections.reverseOrder());
		ans = pass.get(K - 1);
	}
}
