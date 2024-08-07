import java.util.*;
import java.io.*;

public class Main {
	static class Point {
		int x;
		int y;

		public Point(int r, int c) {
			this.x = r;
			this.y = c;
		}
	}

	public static int N;
	public static int result;
	public static int max = Integer.MIN_VALUE;
	public static int[][] arr;
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static Queue<Point> queue = new LinkedList<>();
	public static boolean[][] visited;

	public static void BFS(int r, int c, boolean[][] visited) {
		result++;
		queue.offer(new Point(r, c));
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			int x = p.x;
			int y = p.y;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}

		return;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		int max_num = 1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max_num = Math.max(max_num, arr[i][j]);
			}
		}

		for (int rain = 0; rain <= max_num; rain++) {
			result = 0;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] <= rain) {
						visited[i][j] = true;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] > rain && !visited[i][j]) {
						BFS(i, j, visited);
					}
				}
				
			}
			
			max = Math.max(result, max);
			
		}

		System.out.println(max);
	}

}
