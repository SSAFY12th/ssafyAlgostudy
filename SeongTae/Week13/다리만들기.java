import java.util.*;
import java.io.*;

public class 다리만들기 {
	static int N, landCnt, min;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static List<List<Point>> list;
	static boolean[][] visited = new boolean[100][100];

	static class Point {
		public Point(int r, int c, int landNum) { // 섬 번호 붙이기용
			this.r = r;
			this.c = c;
			this.landNum = landNum;
		}

		public Point(int r, int c, int dist, int landNum) { // 최단거리 찾기용
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.landNum = landNum;
		}

		int r;
		int c;
		int dist;
		int landNum;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		min = Integer.MAX_VALUE;
		N = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Numbering(); // 섬 번호 붙이기
		calMinDistance(); // 최단거리 구하기
		System.out.println(min);
	}

	private static void calMinDistance() {
		for (List<Point> land : list) {
			for (Point p : land) {
				calDistance(p);
			}
		}
	}

	private static void calDistance(Point p) {
		ArrayDeque<Point> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		q.offer(new Point(p.r, p.c, 0, p.landNum));
		visited[p.r][p.c] = true;
		while (!q.isEmpty()) {
			Point area = q.poll();
			int r = area.r;
			int c = area.c;
			int dist = area.dist;
			int landNum = area.landNum;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
					q.offer(new Point(nr, nc, dist + 1, p.landNum)); // 바다 위를 이동하는 경우 <이전 거리 + 1> 해서 다음 위치로 이동
					visited[nr][nc] = true;
				}
				 // 바다를 건너다가 육지(현재 섬의 번호와 다른)가 발견되면 최단거리 업데이트 
				else if(check(nr, nc) && map[nr][nc] != 0 && map[nr][nc] != landNum) {
					min = Math.min(min, dist);
					break; // 최단거리 업데이트되면 멈춤
				}
			}
		}
	}

	private static void Numbering() {
		landCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					landCnt++;
					BFS(i, j);
				}
			}
		}
	}

	private static void BFS(int sr, int sc) {
		ArrayDeque<Point> q = new ArrayDeque<>();
		q.offer(new Point(sr, sc, landCnt));

		List<Point> land = new ArrayList<>();
		land.add(new Point(sr, sc, landCnt));

		map[sr][sc] = landCnt;
		visited[sr][sc] = true;

		while (!q.isEmpty()) {
			Point p = q.poll();
			int r = p.r;
			int c = p.c;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					q.offer(new Point(nr, nc, landCnt));
					visited[nr][nc] = true;
					map[nr][nc] = landCnt;
					land.add(new Point(nr, nc, landCnt));
				}
			}
		}
		list.add(land);
	}

	static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
