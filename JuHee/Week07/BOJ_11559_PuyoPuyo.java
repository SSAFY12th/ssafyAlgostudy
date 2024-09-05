import java.io.*;
import java.util.Arrays;
                  
public class Main {

	static int R = 12, C = 6, result = 0;
	static char[][] map = new char[R + 1][C];
	static boolean[][] visit = new boolean[R + 1][C];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i <= R; i++) {
			if (i == 0) {
				Arrays.fill(map[i], '.');
			} else {
				map[i] = br.readLine().toCharArray();
			}
		}

		while (simulation()) {
			result++;
			for (int i = 1; i <= R; i++) {
				for (int j = 0; j < C; j++) {
					visit[i][j] = false;
				}
			}
		}
		System.out.println(result);
	}

	private static boolean simulation() {
		boolean flag = false;
		int count;
		for (int i = 1; i <= R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visit[i][j] && map[i][j] != '.' && (count = dfs(i, j, map[i][j])) >= 4) {
					remove(i, j, map[i][j]);
					flag = true;
				}
			}
		}
		if (flag) {
			downFall();
		}
		return flag;
	}

	private static void downFall() {
		for (int j = 0; j < C; j++) {
			for (int i = R; i >= 1; i--) {
				boolean flag = false;
				while (map[i][j] == '.') {
					for (int idx = i; idx >= 1; idx--) {
						map[idx][j] = map[idx - 1][j];
						if (map[idx][j] != '.') {
							flag = true;
						}
					}
					if (!flag) {
						break;
					}
				}
			}
		}
	}

	private static int dfs(int x, int y, char target) {
		visit[x][y] = true;
		int count = 1;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 1 && ny >= 0 && nx <= R && ny < C && !visit[nx][ny] && map[nx][ny] == target) {
				count += dfs(nx, ny, target);
			}
		}
		return count;
	}

	private static void remove(int x, int y, char target) {
		map[x][y] = '.';
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 1 && ny >= 0 && nx <= R && ny < C && map[nx][ny] == target) {
				remove(nx, ny, target);
			}
		}
	}
}
