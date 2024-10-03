import java.util.*;
import java.io.*;

public class 미세먼지안녕 {
	static int R, C, T;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static List<Point> list;
	static List<Point> cleaner;

	static class Point {
		public Point(int r, int c, int value) {
			super();
			this.r = r;
			this.c = c;
			this.value = value;
		}

		int r;
		int c;
		int value;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		list = new ArrayList<>();
		cleaner = new ArrayList<>();

		int cleanerNum = 1;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					cleaner.add(new Point(i, j, cleanerNum));
					map[i][j] = 0;
					cleanerNum++;
				}
			}
		}
		for (int t = 0; t < T; t++) {
			checkMeongi(); // 1. 미세먼지 좌표 저장
			spread(); // 2. 미세먼지 확산
			cycle(); // 3. 미세먼지 순환
		}
		System.out.println(count());
	}

	private static int count() {
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				result += map[i][j];
			}
		}
		return result;
	}

	private static void checkMeongi() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] >= 5) {
					list.add(new Point(i, j, map[i][j]));
				}
			}
		}
	}

	static void spread() {
		for (Point p : list) {
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (check(nr, nc) && !isClenaerPoint(nr, nc)) {
					int tmp = p.value / 5; // 5로 나눈 값을 tmp에 저장
					map[nr][nc] += tmp;
					map[p.r][p.c] -= tmp;
				}
			}
		}
		list.clear();
	}

	private static boolean isClenaerPoint(int nr, int nc) {
		for (Point clean : cleaner) {
			if (nr == clean.r && nc == clean.c)
				return true;
		}
		return false;
	}

	static void cycle() {
		windDown(cleaner.get(0));
		windLeft(cleaner.get(0));
		windUp(cleaner.get(0));
		windRight(cleaner.get(0));

		windUp(cleaner.get(1));
		windLeft(cleaner.get(1));
		windDown(cleaner.get(1));
		windRight(cleaner.get(1));

	}

	static void windRight(Point cleaner) {
		int r = cleaner.r;
		int c = cleaner.c;
		for (int i = C - 1; i > c; i--) {
			map[r][i] = map[r][i - 1];
		}
	}

	static void windLeft(Point cleaner) {
		int r = cleaner.r;
		int c = cleaner.c;
		if (cleaner.value == 1) {
			for (int i = 0; i < C - 1; i++) {
				map[0][i] = map[0][i + 1];
			}
		} else {
			for (int i = 0; i < C - 1; i++) {
				map[R - 1][i] = map[R - 1][i + 1];
			}
		}
	}

	static void windUp(Point cleaner) {
		int r = cleaner.r;
		int c = cleaner.c;
		if (cleaner.value == 1) {
			for (int i = 0; i < r; i++) {
				map[i][C - 1] = map[i + 1][C - 1];
			}
		} else {
			for (int i = r + 1; i < R - 1; i++) {
				map[i][0] = map[i + 1][0];
			}
		}
	}

	static void windDown(Point cleaner) {
		int r = cleaner.r;
		int c = cleaner.c;
		if (cleaner.value == 1) {
			for (int i = r - 1; i > 0; i--) {
				map[i][0] = map[i - 1][0];
			}
		} else {
			for (int i = R - 1; i > r; i--) {
				map[i][C - 1] = map[i - 1][C - 1];
			}
		}
	}

	static boolean check(int r, int c) {
		return r < R && r >= 0 && c < C && c >= 0;
	}

	static void printMap() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
