import java.util.*;
import java.io.*;

public class 최소비용 {
	static int N, M, start, end;
	static int[] d;
	static int[][] matrix;
	static final int INF = Integer.MAX_VALUE;
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	static class Node implements Comparable<Node> {
		public Node(int next, int dist) {
			super();
			this.next = next;
			this.dist = dist;
		}

		int next;
		int dist;

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		matrix = new int[N + 1][N + 1];
		d = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(matrix[i], INF); // 인접행렬  초기화
			matrix[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			if (dist < matrix[from][to]) // 중복된 간선이 있는 경우 거리가 짧은 간선을 선택
				matrix[from][to] = dist;
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		if (start == end) { // 시작노드와 종점 노드가 같으면 0으로 리턴
			System.out.println(0);
			return;
		}

		int result = dijkstra(start, end);
		System.out.println(result);
	}

	private static int dijkstra(int start, int end) {
		boolean[] visited = new boolean[N + 1];
		Arrays.fill(d, INF); // 거리배열 초기화
		d[start] = 0;

		pq.clear();
		pq.offer(new Node(start, 0)); // 시작 노드는 거리 0 으로 시작
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			int cur = n.next;
			int dist = n.dist;

			if (!visited[cur]) {
				visited[cur] = true;

				if (cur == end) // 현재 노드가 종점 노드면 현재 거리를 리턴
					return dist;

				for (int next = 1; next <= N; next++) {
					// 다음 노드가 미방문상태 && 현재 노드와 다음 노드사이에 간선이 존재 && 현재노드까지 총 거리 + 다음 노드와의 거리 < 다음 노드의 거리
					if (!visited[next] && matrix[cur][next] != INF && d[next] > d[cur] + matrix[cur][next]) {
						d[next] = d[cur] + matrix[cur][next]; // 다음 노드의 최소 거리 업데이트
						pq.offer(new Node(next, d[next]));
					}
				}
			}
		}
		return d[end]; 
	}
}
