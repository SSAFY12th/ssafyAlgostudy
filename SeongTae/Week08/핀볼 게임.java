package test;

import java.util.*;
import java.io.*;

public class Solution {
	static int N, T, max;
	static int[][] map;
	static List<Point> canGo;
	static List<WarmHole> hole;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static class WarmHole {
		public WarmHole(int r, int c, int number) {
			super();
			this.r = r;
			this.c = c;
			this.number = number;
		}
		int r;
		int c;
		int number;
	}

	static class Point {
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		int r;
		int c;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			max = 0;
			map = new int[N][N];
			canGo = new ArrayList<>();
			hole = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 0)
						canGo.add(new Point(i, j));
					else if(map[i][j]>= 5 && map[i][j] <= 10) 
						hole.add(new WarmHole(i,j,map[i][j])); 
				}
			}

			for (int i = 0; i < canGo.size(); i++) {
				int r = canGo.get(i).r;
				int c = canGo.get(i).c;
				for (int d = 0; d < 4; d++) { // 상 하 좌 우
					Game(r, c, d);
				}
			}
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

	static void Game(int r, int c, int d) {
		int moveR = r + dr[d];
		int moveC = c + dc[d];
		int count = 0;
		while(true) {
			if(moveR < 0 || moveR >=N || moveC < 0 || moveC >= N) {
				if(d == 0) d = 1;
				else if(d == 1) d = 0;
				else if(d == 2) d = 3;
				else if(d == 3) d = 2;
				count++;
				
				moveR += dr[d];
				moveC += dc[d];
			} 

			if((r == moveR && c == moveC) || map[moveR][moveC] == -1) break;
			
			if(map[moveR][moveC] >= 1 && map[moveR][moveC] <= 5) {
				d = next_d(moveR,moveC,d);
				count++;	
			}
			
			if(map[moveR][moveC] >= 6 && map[moveR][moveC] <= 10) {
				for(WarmHole h : hole) {
					if(h.number == map[moveR][moveC] && (h.r != moveR || h.c != moveC)) {
						moveR = h.r;
						moveC = h.c;
						break;
					}
				}
			}			
			moveR += dr[d];
			moveC += dc[d];
		}
		max = Math.max(max, count);
	}

	static int next_d(int r, int c, int d) { // 상: 0 /하: 1 /좌: 2 /우: 3/
		if (map[r][c] == 1) {
			if (d == 0) return 1;
			else if (d == 1) return 3;
			else if (d == 2) return 0;
			else if (d == 3) return 2;
		}
		if (map[r][c] == 2) {
			if (d == 0) return 3;
			else if (d == 1) return 0;
			else if (d == 2) return 1;
			else if (d == 3) return 2;
		}
		if (map[r][c] == 3) {
			if (d == 0) return 2;
			else if (d == 1) return 0;
			else if (d == 2) return 3;
			else if (d == 3) return 1;
		}
		if (map[r][c] == 4) {
			if (d == 0) return 1;
			else if (d == 1) return 2;
			else if (d == 2) return 3;
			else if (d == 3) return 0;
		}
		if (map[r][c] == 5) {
			if (d == 0) return 1;
			else if (d == 1) return 0;
			else if (d == 2) return 3;
			else if (d == 3) return 2;
		}
		return -1;
	}
}
