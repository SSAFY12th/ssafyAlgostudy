import java.util.*;
import java.io.*;

public class Solution {
	static class Position implements Comparable<Position> {
		public Position(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}

		int r;
		int c;
		int time;

		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.time, o.time);
		}
	}

	static int T, N;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}
			int result = getMinDistance(0, 0, N - 1, N - 1);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static int getMinDistance(int sr, int sc, int er, int ec) {
		final int INF = Integer.MAX_VALUE;
		boolean[][] visited = new boolean[N][N];
		int[][] minTime = new int[N][N];
		PriorityQueue<Position> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minTime[i][j] = INF;
			}
		}

		minTime[sr][sc] = 0;
		pq.offer(new Position(sr,sc,minTime[sr][sc]));
		
		while(!pq.isEmpty()) {
			Position p = pq.poll();
			int r = p.r;
			int c = p.c;
			int time = p.time;
			if(!visited[r][c]) {
				visited[r][c] = true;
				
				if(r == er && c == ec) return time;
				
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(check(nr,nc) && !visited[nr][nc] && minTime[nr][nc] > time + map[nr][nc]) {
						minTime[nr][nc] = time + map[nr][nc];
						pq.offer(new Position(nr,nc,minTime[nr][nc]));
					}
				}
			}
		}

		return -1;
	}

	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
