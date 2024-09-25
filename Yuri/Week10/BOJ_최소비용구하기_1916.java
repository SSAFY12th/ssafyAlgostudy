import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] dist;
    static List<List<Edge>> edges = new ArrayList<>();
    static int start, end;

    static class Edge implements Comparable<Edge>{
        int node, weight;
        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < N+1; i++)
            edges.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.get(from).add(new Edge(to, weight));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra(start);
        System.out.println(dist[end]);

    }

    public static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Edge current = pq.poll();

            if(dist[current.node] < current.weight) continue;

            for (Edge edge : edges.get(current.node)) {
                if(dist[edge.node] > dist[current.node] + edge.weight) {
                    dist[edge.node] = dist[current.node] + edge.weight;
                    pq.add(new Edge(edge.node, dist[edge.node]));
                }
            }
        }
    }

}
