import java.util.*;
import java.io.*;

public class BOJ1647 {
    static int N, M;
    static List<Edge>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[first - 1].add(new Edge(second - 1, weight));
            list[second - 1].add(new Edge(first - 1, weight));
        }
        prim(1);
    }
    static void prim(int start) {
        boolean[] visited = new boolean[N];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        int totalCost = 0;
        int lastCost = 0;
        while(!pq.isEmpty()) {
            Edge curE = pq.poll();
            int v = curE.v;
            int curCost = curE.weight;

            if (visited[v]) continue;
            visited[v] = true;
            totalCost += curCost;
            lastCost = Math.max(curCost, lastCost);
            for (Edge e : list[v]) { // v라는 노드와 연결된 간선 수만큼
                if (!visited[e.v]) pq.add(e); // 방문하지 않은 노드라면 그 노드 PQ에 넣기
            }
        }
        System.out.println(totalCost - lastCost);
    }

    static class Edge implements Comparable <Edge> {
        int v, weight;
        Edge (int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
