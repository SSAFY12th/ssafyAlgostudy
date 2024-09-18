import java.util.*;
import java.io.*;

public class 학교탐방 {
	static int N, M;
	static int[] parents;
	static Edge[] matrix;

	static class Edge implements Comparable<Edge> {
		@Override
		public String toString() {
			return "from=" + from + ", to=" + to + ", weight=" + weight;
		}

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
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 건물의 개수
		M = Integer.parseInt(st.nextToken()); // 도로의 개수

		matrix = new Edge[M + 1];

		for (int i = 0; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			// 가중치 0 : 오르막길 , 가중치 1 : 내리막길 -> 가중치를 변환해줘야함
			int weight = Integer.parseInt(st.nextToken()) == 0 ? 1 : 0;
			matrix[i] = new Edge(from, to, weight);
		}

		make();
		Arrays.sort(matrix); // 오름차순 정렬
		// 오르막길 K번 오르면 피로도는 K^2


		int min_cost = kruskal();
		min_cost = min_cost * min_cost;

		make();
		Arrays.sort(matrix, Collections.reverseOrder()); // 내림차순 정렬

		int max_cost = kruskal();
		max_cost = max_cost * max_cost;
		System.out.println(max_cost - min_cost);
	}

	static void make() {
		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = -1;
		}
	}

	static int findSet(int a) {
		if (parents[a] < 0)
			return a;
		return parents[a] = findSet(parents[a]);
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

	static int kruskal() {
		int cost = 0;
		int cnt = 0;
		for (Edge e : matrix) {
			if (union(e.from, e.to)) {
				cost += e.weight;
				if (++cnt == N)
					break;
			}
		}
		return cost;
	}
}
