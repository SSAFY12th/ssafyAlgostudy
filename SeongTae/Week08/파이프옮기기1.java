package test;

import java.io.*;
import java.util.*;

public class test {
	static int N, count;
	static int[][] map;
	static int[] dr = { 0, 1, 1 };
	static int[] dc = { 1, 1, 0 };
	static int direction = 0; // 0 : 가로 , 1 : 대각선 , 2 : 세로

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		count = 0;
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		 simulation(0, 1, 0);

		System.out.println(count);
	}

	private static void simulation(int r, int c, int d) {
		if (r == N - 1 && c == N - 1) {
			count++;
			return;
		}

		if (d == 0) {
			for (int i = 0; i < 2; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (i == 1) {
					if(checkDiagonal(r, c)) {
						simulation(nr,nc,i);
					}
				} else {
					if (check(nr, nc) && map[nr][nc] != 1) {
						simulation(nr, nc, i);
					}	
				}
			}
		}
		
		if (d == 1) {
			for (int i = 0; i < 3; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (i == 1) {
					if(checkDiagonal(r, c)) {
						simulation(nr,nc,i);
					}
				} else {
					if (check(nr, nc) && map[nr][nc] != 1) {
						simulation(nr, nc, i);
					}	
				}
			}
		}
		if (d == 2) {
			for (int i = 1; i < 3; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (i == 1) {
					if(checkDiagonal(r, c)) {
						simulation(nr,nc,i);
					}
				} else {
					if (check(nr, nc) && map[nr][nc] != 1) {
						simulation(nr, nc, i);
					}	
				}
			}
		}
	}

	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	static boolean checkDiagonal(int r, int c) {
		for (int i = 0; i < 3; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(!check(nr,nc) || map[nr][nc] == 1) return false;
		}
		return true;
	}
}
