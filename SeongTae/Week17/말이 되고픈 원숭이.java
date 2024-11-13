import java.util.*;
import java.io.*;

public class Main {
	static int K, W, H, min;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };
	static int[] hdr = { -1, -1, -2, -2, 1, 1, 2, 2 }; // 10시, 2시, 11시, 1시, 8시, 4시, 7시, 5시
	static int[] hdc = { -2, 2, -1, 1, -2, 2, -1, 1 };

	static class Point {
		public Point(int r, int c, int k) {
			super();
			this.r = r;
			this.c = c;
			this.k = k;
		}

		int r;
		int c;
		int k;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		min = Integer.MAX_VALUE;

		BFS();
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	static void BFS() {
		int cnt = 0;
		boolean[][][] visited = new boolean[H][W][K + 1];
		ArrayDeque<Point> q = new ArrayDeque<>();
		q.offer(new Point(0, 0, K));
		visited[0][0][K] = true;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				int r = p.r;
				int c = p.c;
				int k = p.k;
				if (r == H - 1 && c == W - 1) {
					min = Math.min(min, cnt);
					return;
				}
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (check(nr, nc) && !visited[nr][nc][k] && map[nr][nc] != 1) {
						visited[nr][nc][k] = true;
						q.offer(new Point(nr, nc, k));
					}
				}
				if (k > 0) {
					for (int d = 0; d < 8; d++) {
						int nr = r + hdr[d];
						int nc = c + hdc[d];
						if (check(nr, nc) && !visited[nr][nc][k - 1] && map[nr][nc] != 1) {
							visited[nr][nc][k - 1] = true;
							q.offer(new Point(nr, nc, k - 1));
						}
					}
				}
			}
			cnt++;
		}
	}

	static boolean check(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
}
