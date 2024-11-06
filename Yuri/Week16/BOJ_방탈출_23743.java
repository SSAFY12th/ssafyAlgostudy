import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
    static int[] parent;
    static int[] escape;
    static int result = 0;

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
        parent = new int[N+1];
        escape = new int[N+1];
        escape[0] = Integer.MAX_VALUE;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, weight));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            escape[i] = Integer.parseInt(st.nextToken());
            edges.add(new Edge(0, i, escape[i]));
        }

        make();
        kruskal();

        System.out.println(result);

    }

    public static void kruskal() {
        while(!edges.isEmpty()) {
            Edge e = edges.poll();

            if(union(e.from, e.to))
                result += e.weight;
        }
    }

    public static void make() {
        for (int i = 1; i <= N; i++)
            parent[i] = i;
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
