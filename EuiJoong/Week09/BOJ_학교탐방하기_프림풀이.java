import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

    static int N;
    static int M;
    static List<Edge>[] graph;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[v].add(new Edge(w, cost));
            graph[w].add(new Edge(v, cost));
        }


        int min = minPrim(0);
        //System.out.println(min);
        //System.out.println(Arrays.deepToString(graph));

        int max = maxPrim(0);
        //System.out.println(max);

        ans = max - min;
        System.out.println(ans);
    }

    private static int minPrim(int start) {

        //최소를 구하려면 1이 먼저 와야함, 1부터 우선순위를 갖는 큐 만들기
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o2.cost - o1.cost);
        boolean[] visit = new boolean[N + 1];
        pq.offer(new Edge(start, 1));

        int res = 0;
        while (!pq.isEmpty()) {
            //System.out.println(pq);
            Edge edge = pq.poll();
            int vertex = edge.w;
            int cost = edge.cost;

            if (visit[vertex]) continue;

            visit[vertex] = true;
            if (cost == 0) {
                res++;
            }

            for (Edge e : graph[vertex]) {
                if (!visit[e.w]) {
                    pq.offer(e);
                }
            }
        }

        return res * res;
    }

    private static int maxPrim(int start) {

        //최소를 구하려면 1이 먼저 와야함, 1부터 우선순위를 갖는 큐 만들기
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        boolean[] visit = new boolean[N + 1];
        pq.offer(new Edge(start, 1));

        int res = 0;
        while (!pq.isEmpty()) {
            //System.out.println(pq);
            Edge edge = pq.poll();
            int vertex = edge.w;
            int cost = edge.cost;

            if (visit[vertex]) continue;

            visit[vertex] = true;
            if (cost == 0) {
                res++;
            }

            for (Edge e : graph[vertex]) {
                if (!visit[e.w]) {
                    pq.offer(e);
                }
            }
        }

        return res * res;
    }
}