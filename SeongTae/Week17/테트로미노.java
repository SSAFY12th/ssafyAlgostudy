import java.util.*;
import java.io.*;

public class Main {
	static int N, M, max;
	static int[][] map;
	static int[][] dr = { { 0, 0, 0, 0 }, // l 모양
			{ 0, 1, 2, 3 },

			{ 0, 0, 1, 1 }, // ㅁ 모양

			{ 0, 1, 1, 2 }, // ㄹ 모양
			{ 1, 2, 0, 1 }, { 0, 0, 1, 1 }, { 0, 0, 1, 1 },

			{ 0, 1, 1, 1 }, // ㅗ 모양
			{ 0, 0, 0, 1 }, { 1, 0, 1, 2 }, { 0, 1, 2, 1 },

			{ 0, 1, 2, 2 }, // ㄴ 모양
			{ 2, 0, 1, 2 }, { 0, 1, 2, 0 }, { 0, 0, 1, 2 }, { 0, 1, 1, 1 }, { 1, 1, 1, 0 }, { 0, 0, 0, 1 },
			{ 0, 0, 0, 1 }, };
	static int[][] dc = { { 0, 1, 2, 3 }, // l 모양
			{ 0, 0, 0, 0 },

			{ 0, 1, 0, 1 }, // ㅁ 모양

			{ 0, 0, 1, 1 }, // ㄹ 모양
			{ 0, 0, 1, 1 }, { 0, 1, 1, 2 }, { 1, 2, 0, 1 },

			{ 1, 0, 1, 2 }, // ㅗ 모양
			{ 0, 1, 2, 1 }, { 0, 1, 1, 1 }, { 0, 0, 0, 1 },

			{ 0, 0, 0, 1 }, // ㄴ 모양
			{ 0, 1, 1, 1 }, { 0, 0, 0, 1 }, { 0, 1, 1, 1 }, { 0, 0, 1, 2 }, { 0, 1, 2, 2 }, { 0, 1, 2, 0 },
			{ 0, 1, 2, 2 },

	};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		max = Integer.MIN_VALUE;

		for (int k = 0; k < 19; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					simulation(i, j, k);
				}
			}
		}
		System.out.println(max);
	}

	static void simulation(int r, int c, int k) {
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[k][d];
			int nc = c + dc[k][d];
			if(check(nr,nc)) {
				cnt += map[nr][nc];
			}
		}
		max = Math.max(max, cnt);
	}

	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
