import java.util.*;
import java.io.*;

public class Main {
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int count;

	static class Puyo {
		public Puyo(int r, int c, char color) {
			super();
			this.r = r;
			this.c = c;
			this.color = color;
		}

		int r;
		int c;
		char color;
	}

	public static void main(String[] args) throws Exception {
		map = new char[12][6];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		count = 0;
		for (int i = 0; i < 12; i++) {
			s = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		boom();

		System.out.println(count);
	}

	static void boom() {
		while (true) {
			boolean isPoped = false;
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.') {
						isPoped |= bfs(i, j);
					}
				}
			}
			if (isPoped) {
				down();
				count++;
			} else {
				break;
			}
		}
	}

	private static boolean bfs(int r, int c) {
		boolean[][] visited = new boolean[12][6];
		
		List<Puyo> list = new ArrayList<>();
		
		Queue<Puyo> q = new ArrayDeque<>();
		
		char color = map[r][c];
		
		q.offer(new Puyo(r, c, color));
		list.add(new Puyo(r, c, color));
		visited[r][c] = true;
		
		while (!q.isEmpty()) {
			Puyo p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == p.color) {
					visited[nr][nc] = true;
					q.offer(new Puyo(nr, nc, p.color));
					list.add(new Puyo(nr, nc, p.color));
				}
			}
		}
		if (list.size() >= 4) {
			for(Puyo p : list) {
				map[p.r][p.c] = '.';
			}
			return true;	
		}
		return false;
	}

	static void down() {
		for (int i = 0; i < 6; i++) {
			Stack<Character> stack = new Stack<>();
			for (int j = 0; j < 12; j++) {
				if (map[j][i] != '.') {
					stack.push(map[j][i]);
					map[j][i] = '.';
				}
			}
			int size = stack.size();
			for (int k = 0; k < size; k++) {
				map[11 - k][i] = stack.pop();
			}
		}
	}

	static boolean check(int r, int c) {
		return r >= 0 && r < 12 && c >= 0 && c < 6;
	}
}
