import java.util.*;
import java.io.*;
public class Main {
	static class Edge {
		int from;
		int to;
		int connect;
		public Edge(int from, int to, int connect) {
			this.from = from;
			this.to = to;
			this.connect = connect;
		}
	}
	
	static int N, M;
	static int[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		M  = Integer.parseInt(st.nextToken());
		int[] search = new int[M];
		
		List<Edge> edgeList = new  ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int conn = Integer.parseInt(st.nextToken());
				if(i < j) edgeList.add(new Edge(i + 1, j + 1, conn));
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			search[i] = Integer.parseInt(st.nextToken());
		}
		
		make();
		for(Edge e : edgeList) {
			if(e.connect == 1) {
				union(e.from, e.to);	
			}
		}
		
		int Root = find(search[0]);
		boolean canGo = true;
        for (int i = 1; i < search.length; i++) {
            if(find(search[i]) != Root) {
                canGo = false;
                break;
            }
        }
		System.out.println(canGo == true? "YES" : "NO");
	}
	
	static void make() {
		parent = new int[N + 1];
		Arrays.fill(parent, -1);
	}
	
	static int find(int a) {
		if(parent[a] < 0) return a;
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		
		parent[bRoot] = aRoot;
		return true;
	}
}
