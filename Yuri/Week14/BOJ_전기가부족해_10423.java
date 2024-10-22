import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;
    static int[] powerStation;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static class Edge implements Comparable<Edge> {
        int from, to, weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        powerStation = new int[K];
        parent = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++)
            powerStation[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.add(new Edge(from, to, weight));
        }

        make();

        int cost = 0;
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            if(union(edge.from, edge.to)) {
                cost += edge.weight;
            }
        }

        System.out.println(cost);

    }

    public static void make() {
        for (int i = 1; i <= N; i++)
            parent[i] = i;

        for (int n : powerStation)
            parent[n] = 0;
    }

    public static int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parent[bRoot] = aRoot;
        return true;
    }

}
