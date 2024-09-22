import java.util.*;
import java.io.*;

public class 도시분할계획 {
	static int N, M;
	static List<List<Edge>> graph;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static Queue<Integer> q = new ArrayDeque<>();
	static boolean[] visited;

	static class Edge implements Comparable<Edge> {
		public Edge(int next, int cost) {
			super();
			this.next = next;
			this.cost = cost;
		}

		int next;
		int cost;

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			// 무향 그래프의 경우 두 정점에 대해 모두 간선을 추가해줘야 함 
			graph.get(from).add(new Edge(to, cost));
			graph.get(to).add(new Edge(from, cost));
		}

		int result = prim(1);

		System.out.println(result);
	}

	private static int prim(int start) {
		int min = 0;
		// 가장 비용이 큰 길 하나만 제거하면 유지비 합의 최솟값을 구할 수 있음
		int max_cost = Integer.MIN_VALUE; 
		visited = new boolean[N + 1];
		q.offer(start);

		while (!q.isEmpty()) {
			int cur = q.poll();
			visited[cur] = true;

			for (Edge edge : graph.get(cur)) {
				if (!visited[edge.next]) {
					pq.offer(edge);
				}
			}

			while (!pq.isEmpty()) {
				Edge e = pq.poll();
				int next = e.next;
				int cost = e.cost;
				if (!visited[next]) {
					visited[next] = true;
					q.offer(next);
					min += cost;
					max_cost = Math.max(max_cost, cost);
					break;
				}
			}
		}
		return min - max_cost; // 모든 길의 최소 유지비 - 그 중 가장 큰 유지비
	}
}
