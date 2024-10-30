import java.util.*;
import java.io.*;

public class Main {
	static int T, N, K, X, Y, W; // 테케, 정점 수, 조건 개수, 진출 정점, 진입 정점, 목표 정점
	static int[] Time, comeCnt, times;
	static List<List<Integer>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			Time = new int[N + 1]; // 각 정점 별 비용
			times = new int[N + 1]; // 각 정점 별 최대 비용 저장용 배열
			comeCnt = new int[N + 1]; // 진입차수 저장용 배열
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				Time[i] = Integer.parseInt(st.nextToken());
			}

			graph = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<Integer>());
			}

			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine());
				X = Integer.parseInt(st.nextToken());
				Y = Integer.parseInt(st.nextToken());
				graph.get(X).add(Y);
				comeCnt[Y] += 1;
			}

			W = Integer.parseInt(br.readLine());

			sb.append(TopologySort()).append("\n");
		}
		System.out.println(sb);
	}

	private static int TopologySort() {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (comeCnt[i] == 0) {
				q.offer(i);
				times[i] = Time[i];
			}
		}

		while (!q.isEmpty()) {
			int cur = q.poll();
			List<Integer> list = graph.get(cur);
			for (int next : list) {
				comeCnt[next]--;
				times[next] = Math.max(times[next], times[cur] + Time[next]);
				if (comeCnt[next] == 0) {
					q.offer(next);
				}
			}
		}

		return times[W];
	}
}
