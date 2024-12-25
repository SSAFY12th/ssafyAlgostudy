import java.util.*;
import java.io.*;

public class Main {
	static class Point {
		int l;
		int r;
		int c;

		public Point(int l, int r, int c) {
			this.l = l;
			this.r = r;
			this.c = c;
		}
	}

	static int L, R, C; // 층 수, 행, 열
	static Point start, end;
	static StringBuilder sb = new StringBuilder();
	static boolean[][][] visited;
	static char[][][] map;
	static int[] dl = { 0, 0, 0, 0, -1, 1 };
	static int[] dr = { -1, 1, 0, 0, 0, 0 };
	static int[] dc = { 0, 0, -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if (L == R && R == C && C == 0)
				break;

			map = new char[30][30][30];
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					String s = br.readLine();
					for (int k = 0; k < C; k++) {
						map[i][j][k] = s.charAt(k);
						if (map[i][j][k] == 'S')
							start = new Point(i, j, k);
						else if (map[i][j][k] == 'E')
							end = new Point(i, j, k);
					}
				}
				String temp = br.readLine(); // 공백 문자열
			}
			BFS();
		}

		System.out.println(sb);
	}

	public static void BFS() {
		ArrayDeque<Point> q = new ArrayDeque<>();
		visited = new boolean[30][30][30];
		int cnt = 0;
		q.offer(new Point(start.l, start.r, start.c));
		visited[start.l][start.r][start.c] = true;
		
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				int l = p.l;
				int r = p.r;
				int c = p.c;
				if(end.l == l && end.r == r && end.c == c) {
					sb.append("Escaped in ").append(cnt).append(" minute(s).").append("\n");
					return;
				}
				for (int d = 0; d < 6; d++) {
					int nl = l + dl[d];
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(check(nl,nr,nc) && !visited[nl][nr][nc] && map[nl][nr][nc] != '#') {
						q.offer(new Point(nl,nr,nc));
						visited[nl][nr][nc] = true;
					}
				}
			}
			if(q.size() != 0) cnt++;
		}
		sb.append("Trapped!").append("\n");
	}

	public static boolean check(int l, int r, int c) {
		return l >= 0 && l < L && r >= 0 && r < R && c >= 0 && c < C;
	}
	
	public static void printMap() {
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					System.out.print(map[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}
