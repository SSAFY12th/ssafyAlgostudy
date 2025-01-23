import java.io.*;
import java.util.*;

public class Main {
	static int N, L, R, cnt;
	static int[][] matrix;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Position {
		int r;
		int c;

		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken()); // L명 이상
		R = Integer.parseInt(st.nextToken()); // R명 이하

		matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cnt = 0;
		while (true) {
			boolean[][] visited = new boolean[N][N];
			boolean moved = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						if(move(i, j, visited)) moved = true;
					}
				}
			}
			if(!moved) break;
			else cnt++;
		}
		System.out.println(cnt);
	}

	public static boolean move(int sr, int sc, boolean[][] visited) {
		ArrayDeque<Position> q = new ArrayDeque<>();
		List<Position> list = new ArrayList<>();

		visited[sr][sc] = true;
		q.offer(new Position(sr, sc));
		list.add(new Position(sr, sc));

		int pCnt = 0;
		while (!q.isEmpty()) {
			Position p = q.poll();
			int r = p.r;
			int c = p.c;
			pCnt += matrix[r][c];
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (check(nr, nc) && !visited[nr][nc]) {
					int diff = Math.abs(matrix[r][c] - matrix[nr][nc]);
					if (diff >= L && diff <= R) {
						visited[nr][nc] = true;
						q.offer(new Position(nr, nc));
						list.add(new Position(nr, nc));
					}
				}
			}
		}

		if (list.size() == 1)
			return false;

		for (Position p : list) {
			matrix[p.r][p.c] = pCnt / list.size();
		}

		return true;
	}

	public static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
