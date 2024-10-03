import java.util.*;
import java.io.*;

public class 다리만들기2 {
	static int N, M;
	static int[][] map;
	static int[] parents;
	static int landCnt;
	static int[][] Matrix;
	static List<List<Point>> landList;
	static List<Edge> EdgeList;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static class Point {
		public Point(int r, int c, int landNum) {
			super();
			this.r = r;
			this.c = c;
			this.landNum = landNum;
		}

		int r;
		int c;
		int landNum;
	}

	static class Edge implements Comparable<Edge> {
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		int from;
		int to;
		int weight;

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		landCnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		landList = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			landList.add(new ArrayList<>());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					landCnt++;
					BFS(i, j);
				}
			}
		}

		Matrix = new int[landCnt + 1][landCnt + 1];
		for (int i = 0; i < landCnt + 1; i++) {
			for (int j = 0; j < landCnt + 1; j++) {
				Matrix[i][j] = Integer.MAX_VALUE;
			}
		}

		 makeEdgeList();

		 System.out.println(kruskal());
	}

	static void BFS(int sr, int sc) {

		ArrayDeque<Point> q = new ArrayDeque<>();
		q.offer(new Point(sr, sc, landCnt));
		map[sr][sc] = landCnt;
		visited[sr][sc] = true;
		landList.get(landCnt).add(new Point(sr, sc, landCnt));

		while (!q.isEmpty()) {
			Point p = q.poll();
			int r = p.r;
			int c = p.c;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					q.offer(new Point(nr, nc, landCnt));
					map[nr][nc] = landCnt;
					visited[nr][nc] = true;
					landList.get(landCnt).add(new Point(nr, nc, landCnt));
				}
			}
		}
	}
	
	private static int kruskal() {
		make();
		int cost = 0;
		int cnt = 0;
		Collections.sort(EdgeList);
		for (Edge edge : EdgeList) {
			if (union(edge.from, edge.to)) {
				cost += edge.weight;
				if (++cnt == landCnt - 1)
					break;
			}
		}
		if (cnt != landCnt - 1)
			return -1;

		return cost;
	}

	private static void makeEdgeList() {
		EdgeList = new ArrayList<>();
		for (int i = 1; i <= landCnt; i++) {
			calMinDistance(i);
		}
		for (int i = 1; i < Matrix.length; i++) {
			for (int j = 1; j < Matrix.length; j++) {
				if (i != j && Matrix[i][j] != Integer.MAX_VALUE) {
					Edge edge = new Edge(i, j, Matrix[i][j]);
					EdgeList.add(edge);
				}
			}
		}
	}

	private static void calMinDistance(int Lnum) {
		for (Point p : landList.get(Lnum)) {
			int r = p.r;
			int c = p.c;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (check(nr, nc) && map[nr][nc] == 0) {
					int[] cur = calDistance(d, r, c);
					int distance = cur[0];
					int endLnum = cur[1];
					if (Lnum != endLnum && distance > 1 && Matrix[Lnum][endLnum] > distance) {
						Matrix[Lnum][endLnum] = distance;
					}
				}
			}
    }
	}

	private static int[] calDistance(int d, int r, int c) {
		int[] dist = new int[2];
		int count = 0;
		int endLnum = 0;
		if (d == 1) {
			for (int i = 1; r + i < N; i++) {
				if (map[r + i][c] != 0) {
					endLnum = map[r + i][c];
					break;
				}
				count++;
			}
		} else if (d == 3) {
			for (int i = 1; c + i < M; i++) {
				if (map[r][c + i] != 0) {
					endLnum = map[r][c + i];
					break;
				}
				count++;
			}
		}

		if (endLnum != 0) {
			dist[0] = count;
			dist[1] = endLnum;
		}
		return dist;
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	static void make() {
		parents = new int[landCnt + 1];
		Arrays.fill(parents, -1);
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;

		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}

	static int findSet(int a) {
		if (parents[a] < 0)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
