import java.util.*;
import java.io.*;

public class Main {
	static int N, M, X; // N 학생 수& 마을 번호 / M : 유향 간선 수 / X : 파티할 마을 번호
	static List<List<Edge>> graph = new ArrayList<>();

	static class Edge implements Comparable<Edge> {
		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		int to;
		int weight;

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Edge>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Edge(to, weight));
		}

		int[] minDistances = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			int goParty = dijksta(i, X);
			int goHome = dijksta(X, i);
			minDistances[i] = goParty + goHome;
		}

		Arrays.sort(minDistances);
		System.out.println(minDistances[N]);
	}

	private static int dijksta(int start, int end) {
		if (start == end)
			return 0;
		int[] d = new int[N + 1];
		Arrays.fill(d, 100000000);
		d[start] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			int cur = e.to;
			int dist = e.weight;

			if (cur == end)
				return dist;

			for (Edge edge : graph.get(cur)) {
				int next = edge.to;
				int distance = edge.weight;
				if (d[next] > d[cur] + distance) {
					d[next] = d[cur] + distance;
					pq.offer(new Edge(next, d[next]));
				}
			}
		}
		return d[end];
	}
}
