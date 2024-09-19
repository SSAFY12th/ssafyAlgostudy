import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int T, D, W, K, ans;
	static int[][] map;
	static int[][] copyMap;
	static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			copyMap = new int[D][W];
			isSelected = new boolean[D];
			ans = Integer.MAX_VALUE;

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = copyMap[i][j] = Integer.parseInt(st.nextToken());
                }
            }

			sb.append("#").append(tc).append(" ");
			if (check()) {
				sb.append(0).append("\n");
			}else {
                powerSet(0);
				sb.append(ans).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void powerSet(int depth) {
		if (depth == D) {
			dfs(0, 0);
			copy();
			return;
		}

		isSelected[depth] = false;
		powerSet(depth + 1);
		isSelected[depth] = true;
		powerSet(depth + 1);

	}

	private static void dfs(int depth, int idx) {
		if (depth >= ans) {
			return;
		}
		if (idx == D) {
			if (check()) {
				ans = Math.min(ans,  depth);
			}
            return;
		}

		if (isSelected[idx]) {
			Arrays.fill(map[idx], 0);
			dfs(depth + 1, idx + 1);

			Arrays.fill(map[idx], 1);
			dfs(depth + 1, idx + 1);
		}else {
			dfs(depth, idx + 1);
		}
	}

	private static boolean check() {

		for (int c = 0; c < W; c++) {
			int cnt = 1;
			int start = map[0][c];
			boolean pass = false;
			for (int r = 1; r < D; r++) {
				if (start == map[r][c]) {
					cnt++;
				}else {
					start = map[r][c];
					cnt = 1;
				}

				if (cnt == K) {
					pass = true;
					break;
				}
			}
			if (!pass) {
				return false;
			}
		}
		return true;
	}

	private static void copy() {
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = copyMap[i][j];
			}
		}
	}
}
