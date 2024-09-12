import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 1, 0);
		System.out.println(ans);
	}

	private static void dfs(int r, int c, int dir) {

		if (r == N - 1 && c == N - 1) {
			ans++;
			return;
		}

		//우
		if (dir == 0) {
			if (chkRight(r, c)) dfs(r, c + 1, 0);
			if (chkRightDown(r, c)) dfs(r + 1, c + 1, 2);
		}
		//하
		if (dir == 1) {
			if (chkDown(r, c)) dfs(r + 1, c, 1);
			if (chkRightDown(r, c)) dfs(r + 1, c + 1, 2);
		}
		//우하
		if (dir == 2) {
			if (chkRight(r, c)) dfs(r, c + 1, 0);
			if (chkDown(r, c)) dfs(r + 1, c, 1);
			if (chkRightDown(r, c)) dfs(r + 1, c + 1, 2);
		}






	}
	private static boolean chkRight(int r, int c) {
		if (c + 1 < N && map[r][c + 1] != 1) return true;
		return false;
	}
	private static boolean chkDown(int r, int c) {
		if (r + 1 < N && map[r + 1][c] != 1) return true;
		return false;
	}
	private static boolean chkRightDown(int r, int c) {
		if (r + 1 < N && c + 1 < N && map[r + 1][c] != 1 && map[r][c + 1] != 1 && map[r + 1][c + 1] != 1) {
			return true;
		}
		return false;
	}
}
