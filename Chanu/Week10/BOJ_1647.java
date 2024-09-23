
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n+1];
        graph = new ArrayList[n+1];

        for (int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b,w));
            graph[b].add(new Edge(a,w));

        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1,0));
        long total = 0;

        while (!pq.isEmpty()) {

            Edge node = pq.poll();

            int to = node.to;
            int cost = node.weight;

            if (visited[to]) {
                continue;
            }

            max = Math.max(max,cost);
            visited[to] = true;
            total += cost;

            for (Edge edge : graph[to]) {

                if (!visited[edge.to]) {
                    pq.add(edge);
                }
            }
        }

        System.out.println(total-max);
    }

    static class Edge implements Comparable<Edge>{

        public int to;
        public int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
