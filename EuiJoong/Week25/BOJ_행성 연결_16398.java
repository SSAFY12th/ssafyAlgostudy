import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int startNode, endNode, cost;

        public Edge(int startNode, int endNode, int cost) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "startNode=" + startNode +
                    ", endNode=" + endNode +
                    ", cost=" + cost +
                    '}';
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int N;
    static List<Edge> graph;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (cost != 0) graph.add(new Edge(i, j, cost));
            }
        }

        Collections.sort(graph);
        makeSet();
        kruskal();
    }
    private static void makeSet() {
        p = new int[N];
        for (int i = 0; i < N; i++) {
            p[i] = i;
        }
    }

    private static int find(int x) {
        if (x == p[x]) return x;
        return p[x] = find(p[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y) p[y] = x;
        else p[x] = y;
    }

    private static void kruskal() {
        long cost = 0;
        for (Edge edge : graph) {
            if (find(edge.startNode) != find(edge.endNode)) {
                cost += edge.cost;
                union(edge.startNode, edge.endNode);
            }
        }
        System.out.println(cost);
    }
}
