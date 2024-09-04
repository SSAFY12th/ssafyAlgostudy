import java.util.*;
import java.io.*;

public class Solution {
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int T, N, M, R, C, L;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken()); // 맨홀 R좌표
			C = Integer.parseInt(st.nextToken()); // 맨홀 C좌표
			L = Integer.parseInt(st.nextToken()); // 소요시간

			map = new int[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int result = bfs();

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static int bfs() {
		boolean[][] visited = new boolean[N][M];
		Queue<Point> q = new ArrayDeque<>();
		int result = 1;
		q.offer(new Point(R, C));
		visited[R][C] = true;

		while (L-- > 1) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				int r = p.r;
				int c = p.c;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (check(nr, nc) && map[nr][nc] != 0 && !visited[nr][nc]) {
						if (canMove(map[r][c], map[nr][nc], d)) {
							q.offer(new Point(nr, nc));
							visited[nr][nc] = true;
							result++;
						}
					}
				}
			}
		}
		return result;
	}

	private static boolean canMove(int before_pipe, int cur_pipe, int d) {
		if (before_pipe == 1) {
			if (d == 0) {
				if(cur_pipe == 3 || cur_pipe == 4 ||cur_pipe == 7) return false;
			}
			if (d == 1) {
				if(cur_pipe == 3 || cur_pipe == 5 ||cur_pipe == 6) return false;
			}
			if (d == 2) {
				if(cur_pipe == 2 || cur_pipe == 6 ||cur_pipe == 7) return false;
			}
			if (d == 3) {
				if(cur_pipe == 2 || cur_pipe == 4 ||cur_pipe == 5) return false;
			}
		}
		if (before_pipe == 2) {
			if (d == 0) {
				if(cur_pipe == 3 || cur_pipe == 4 ||cur_pipe == 7) return false;
			}
			if (d == 1) {
				if(cur_pipe == 3 || cur_pipe == 5 ||cur_pipe == 6) return false;
			}
			if (d == 2) {
				return false;
			}
			if (d == 3) {
				return false;
			}
		}
		if (before_pipe == 3) {
			if (d == 0) {
				return false;
			}
			if (d == 1) {
				return false;
			}
			if (d == 2) {
				if(cur_pipe == 2 || cur_pipe == 6 ||cur_pipe == 7) return false;
			}
			if (d == 3) {
				if(cur_pipe == 2 || cur_pipe == 4 ||cur_pipe == 5) return false;
			}
		}
		if (before_pipe == 4) {
			if (d == 0) {
				if(cur_pipe == 3 || cur_pipe == 4 || cur_pipe == 7) return false;
			}
			if (d == 1) {
				return false;
			}
			if (d == 2) {
				return false;
			}
			if (d == 3) {
				if(cur_pipe == 2 || cur_pipe == 4 || cur_pipe == 5) return false;
			}
		}
		if (before_pipe == 5) {
			if (d == 0) {
				return false;
			}
			if (d == 1) {
				if(cur_pipe == 3 || cur_pipe == 5 || cur_pipe == 6) return false;
			}
			if (d == 2) {
				return false;
			}
			if (d == 3) {
				if(cur_pipe == 2 || cur_pipe == 4 || cur_pipe == 5) return false;
			}
		}
		if (before_pipe == 6) {
			if (d == 0) {
				return false;
			}
			if (d == 1) {
				if(cur_pipe == 3 || cur_pipe == 5 || cur_pipe == 6) return false;
			}
			if (d == 2) {
				if(cur_pipe == 2 || cur_pipe == 6 || cur_pipe == 7) return false;
			}
			if (d == 3) {
				return false;
			}
		}
		if (before_pipe == 7) {
			if (d == 0) {
				if(cur_pipe == 3 || cur_pipe == 4 || cur_pipe == 7) return false;
			}
			if (d == 1) {
				return false;
			}
			if (d == 2) {
				if(cur_pipe == 2 || cur_pipe == 6 || cur_pipe == 7) return false;
			}
			if (d == 3) {
				return false;
			}
		}
		return true;
	}

	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
