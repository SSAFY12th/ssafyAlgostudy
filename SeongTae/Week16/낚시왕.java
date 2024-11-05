import java.util.*;
import java.io.*;

public class 낚시왕 {
	static int R, C, M;
	static int sum;
	static int[] dr = { 0, -1, 1, 0, 0 }; // 상 하 우 좌
	static int[] dc = { 0, 0, 0, 1, -1 }; // 사람은 계속 오른쪽으로 이동
	static List<Point> list = new ArrayList<>();
	static int[][] map;
	static Point person;

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static Shark[] sharkList;

	static class Shark {
		public Shark(int r, int c, int s, int d, int z, int index) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			this.index = index;
		}

		int r;
		int c;
		int s;
		int d;
		int z;
		int index;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[R + 1][C + 1];

		sharkList = new Shark[M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int direct = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			map[row][col] = 1; // 상어가 있는 곳은 1로 표시(한 마리 있다는 뜻)
			sharkList[i] = new Shark(row, col, speed, direct, size, i);
		}

		person = new Point(0, 0);
		sum = 0;

		for (int i = 0; i < C; i++) {
			// 사람 이동 & 상어 있으면 잡음
			movePerson();

			// 상어 이동
			moveShark();

			// 상어가 2마리이상 있는 곳 찾음
			findShark();

			// 상어가 겹친 위치에서 크기가 작은 상어 죽임
			killShark();
		}

		System.out.println(sum);
	}

	public static void movePerson() {
		person.c++;
		int col = person.c;
		for (int i = 1; i <= R; i++) {
			for (int j = 0; j < M; j++) {
				if (sharkList[j].r == i && sharkList[j].c == col) {
					sum += sharkList[j].z;
					sharkList[j] = new Shark(0, 0, 0, 0, 0, j);
					map[i][col] = 0;
					return;
				}
			}
		}
	}

	private static void moveShark() {
		for (int i = 0; i < M; i++) {
			Shark shark = sharkList[i];
			if (shark.z != 0 && shark.s != 0) {
				map[shark.r][shark.c] -= 1;
				int diff = 0;
				if (shark.d == 1 || shark.d == 2) {
					diff = (R - 1) * 2;
				} else {
					diff = (C - 1) * 2;
				}

				int move = shark.s % diff;

				for (int j = 0; j < move; j++) {
					int nr = shark.r + dr[shark.d];
					int nc = shark.c + dc[shark.d];
					if (!check(nr, nc)) {
						if (shark.d == 1)
							shark.d = 2;
						else if (shark.d == 2)
							shark.d = 1;
						else if (shark.d == 3)
							shark.d = 4;
						else if (shark.d == 4)
							shark.d = 3;
						nr = shark.r + dr[shark.d];
						nc = shark.c + dc[shark.d];
					}
					shark.r = nr;
					shark.c = nc;
				}
				map[shark.r][shark.c] += 1;
			}
		}
	}

	private static void findShark() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (map[i][j] > 1) {
					list.add(new Point(i, j));
				}
			}
		}
	}

	private static void killShark() {
		for (Point p : list) {
			Shark before = null;
			for (int j = 0; j < M; j++) {
				Shark shark = sharkList[j];
				if (shark.r == p.r && shark.c == p.c) {
					if (before == null || before.z < shark.z) {
						if (before != null) {
							sharkList[before.index] = new Shark(0, 0, 0, 0, 0, before.index);
						}
						before = shark;
					} else {
						sharkList[shark.index] = new Shark(0, 0, 0, 0, 0, shark.index);
					}
				}
			}
			map[p.r][p.c] = 1;
		}
		list.clear();
	}

	public static boolean check(int r, int c) {
		return r >= 1 && r <= R && c >= 1 && c <= C;
	}
}
