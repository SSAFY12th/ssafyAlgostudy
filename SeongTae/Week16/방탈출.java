import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int sum;
	static int[] escapeTime, parents;
	static Edge[] edgeList;

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sum = 0;

		edgeList = new Edge[M + N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, cost);
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int from = i;
			int to = 0;
			int cost = Integer.parseInt(st.nextToken());
			edgeList[M + i - 1] = new Edge(from, to, cost);
		}
		
		Arrays.sort(edgeList);
		make();
		int len = 0;
		for (Edge e : edgeList) {
			if(union(e.from,e.to)) {
				sum +=e.cost;
				if(++len == N) break;
			}
		}
		
		System.out.println(sum);
	}

	public static void make() {
		parents = new int[N + 1];
		Arrays.fill(parents, -1);
	}

	public static int findSet(int a) {
		if (parents[a] < 0)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;

		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}
}
