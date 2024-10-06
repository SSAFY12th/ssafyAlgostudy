import java.util.*;
import java.io.*;

public class 젤다 {
	static int N;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static boolean[][] visited;

	static class Node implements Comparable<Node> {
		public Node(int r, int c, int cost) {
			super();
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		int r;
		int c;
		int cost;

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int number = 0;
		while (true) {
			number++;
			N = Integer.parseInt(br.readLine());
			if (N == 0) break; // N이 0이면 멈춤
			else { // 아니면 map만들고 다익스트라 수행
				map = new int[N][N];
				for (int i = 0; i < N; i++) {
					st = new StringTokenizer(br.readLine());
					for (int j = 0; j < N; j++) {
						map[i][j] = Integer.parseInt(st.nextToken());
					}
				}
				int result = dijkstra(map); // 다익스트라 결과 담음
				sb.append("Problem ").append(number).append(": ").append(result).append("\n"); // StringBuilder에 미리 저장
			}
		}
		System.out.println(sb); // 마지막에 출력
	}

	private static int dijkstra(int[][] map) {
		PriorityQueue<Node> pq = new PriorityQueue<>(); // PQ를 써서 최소 비용 순으로 다음 Node 정렬&저장
		visited = new boolean[N][N]; 

		int[][] minMatrix = new int[N][N]; // 최소비용 업데이트를 위한 2차원 배열 생성

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minMatrix[i][j] = Integer.MAX_VALUE; // 모든 원소를 INF값으로 초기화(값 비교를 위해)
			}
		}

		pq.offer(new Node(0, 0, map[0][0])); // (0,0)번 좌표부터 시작
		visited[0][0] = true;
		minMatrix[0][0] = map[0][0];

		while (!pq.isEmpty()) {
			Node n = pq.poll();
			int r = n.r;
			int c = n.c;
			int nCost = n.cost;

			if (r == N - 1 && c == N - 1) // 만약 현재 노드가 배열의 끝이라면 바로 Cost리턴
				return nCost;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && minMatrix[nr][nc] > nCost + map[nr][nc]) {
          // 최소비용배열의 값보다 (현재까지의 최소비용 + 다음 좌표의 비용)이 더 작으면 업데이트해줌
					minMatrix[nr][nc] = nCost + map[nr][nc];
					visited[nr][nc] = true;
					pq.offer(new Node(nr, nc, minMatrix[nr][nc]));
				}
			}
		}

		return minMatrix[N - 1][N - 1]; // 마지막으로 끝 좌표의 비용 리턴
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
