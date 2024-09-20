import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Edge {
        int w;
        int cost;

        public Edge(int w, int cost) {
            this.w = w;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "w=" + w +
                    ", cost=" + cost +
                    '}';
        }
    }


    static int N; // 정점 개수
    static int M; // 간선 개수
    static ArrayList<Edge>[] adjList;
    static int[] dist;
    static boolean[] visit;
    static final int INF  = Integer.MAX_VALUE;
    static int startNode; //출발점
    static int endNode; //도착점
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[u].add(new Edge(v, cost));
        }

        st = new StringTokenizer(br.readLine());
        startNode = Integer.parseInt(st.nextToken());
        endNode = Integer.parseInt(st.nextToken());


        dist = new int[N + 1];
        Arrays.fill(dist, INF);
        ans = dijkstra(startNode, endNode);
        System.out.println(ans);
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        visit = new boolean[N + 1];
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int cur = edge.w;

            if (visit[cur]) continue;

            visit[cur] = true;
            for (Edge e : adjList[cur]) {
                if (!visit[e.w] && dist[e.w] > dist[cur] + e.cost) {
                    dist[e.w] = dist[cur] + e.cost;
                    pq.offer(new Edge(e.w, dist[e.w]));
                }
            }
        }

        return dist[end];
    }
}