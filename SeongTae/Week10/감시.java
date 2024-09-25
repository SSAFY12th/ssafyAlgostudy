import java.util.*;
import java.io.*;

public class 감시 {
	static int N, M, min;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dc = { 0, 0, -1, 1 };
	static List<Point> list = new ArrayList<>();

	static class Point {
		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", value=" + value + "]";
		}

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
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 5) {
					list.add(new Point(i, j, map[i][j]));
				}
			}
		}
		watching(0);
		System.out.println(min == Integer.MAX_VALUE ? 0 : min);
	}

	private static void watching(int cnt) {
		if (cnt == list.size()) {
			int area = count();
			if (min > area) {
				min = area;
			}
			return;
		}

		int r = list.get(cnt).r;
		int c = list.get(cnt).c;
		int value = list.get(cnt).value;

		for (int d = 0; d < 4; d++) {
			ArrayList<Point> road = new ArrayList<>();
			spread(r, c, d, value, road);
			watching(cnt + 1);
			remove(road);
		}
	}

	private static void remove(ArrayList<Point> road) {
		for (int i = 0; i < road.size(); i++) {
			Point p = road.get(i);
			int r = p.r;
			int c = p.c;
			map[r][c] = 0;
		}
		road.clear();
	}

	private static void spread(int r, int c, int d, int value, ArrayList<Point> road) {
		if (value == 1) {
			if (d == 0) {
				goUp(r, c, road);
			} else if (d == 1) {
				goDown(r, c, road);
			} else if (d == 2) {
				goLeft(r, c, road);
			} else if (d == 3) {
				goRight(r, c, road);
			}

		} else if (value == 2) {
			if (d == 0 || d == 1) {
				goUp(r, c, road);
				goDown(r, c, road);
			} else if (d == 2 || d == 3) {
				goLeft(r, c, road);
				goRight(r, c, road);
			}

		} else if (value == 3) {
			if (d == 0) {
				goUp(r, c, road);
				goRight(r, c, road);
			} else if (d == 1) {
				goDown(r, c, road);
				goRight(r, c, road);
			} else if (d == 2) {
				goDown(r, c, road);
				goLeft(r, c, road);
			} else if (d == 3) {
				goUp(r, c, road);
				goLeft(r, c, road);
			}

		} else if (value == 4) {
			if (d == 0) {
				goUp(r, c, road);
				goLeft(r, c, road);
				goRight(r, c, road);
			} else if (d == 1) {
				goUp(r, c, road);
				goRight(r, c, road);
				goDown(r, c, road);
			} else if (d == 2) {
				goLeft(r, c, road);
				goDown(r, c, road);
				goRight(r, c, road);
			} else if (d == 3) {
				goUp(r, c, road);
				goLeft(r, c, road);
				goDown(r, c, road);
			}
		} else if (value == 5) {
			goUp(r, c, road);
			goDown(r, c, road);
			goLeft(r, c, road);
			goRight(r, c, road);
		}
	}

	private static int count() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}

	private static void goUp(int r, int c, ArrayList<Point> road) {
		int i = 1;
		while (true) {
			if (r - i < 0 || map[r - i][c] == 6)
				break;
			if (map[r - i][c] == 0) {
				map[r - i][c] = -1;
				road.add(new Point(r - i, c, map[r - i][c]));
			}
			i++;
		}
	}

	private static void goDown(int r, int c, ArrayList<Point> road) {
		int i = 1;
		while (true) {
			if (r + i >= N || map[r + i][c] == 6)
				break;
			if (map[r + i][c] == 0) {
				map[r + i][c] = -1;
				road.add(new Point(r + i, c, map[r + i][c]));
			}
			i++;
		}
	}

	private static void goLeft(int r, int c, ArrayList<Point> road) {
		int i = 1;
		while (true) {
			if (c - i < 0 || map[r][c - i] == 6)
				break;
			if (map[r][c - i] == 0) {
				map[r][c - i] = -1;
				road.add(new Point(r, c - i, map[r][c - i]));
			}
			i++;
		}
	}

	private static void goRight(int r, int c, ArrayList<Point> road) {
		int i = 1;
		while (true) {
			if (c + i >= M || map[r][c + i] == 6)
				break;
			if (map[r][c + i] == 0) {
				map[r][c + i] = -1;
				road.add(new Point(r, c + i, map[r][c + i]));
			}
			i++;
		}
	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
