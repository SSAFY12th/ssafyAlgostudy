package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int w;
        int cost;

        public Edge(int w, int cost) {
            this.w = w;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int N, M, ans;
    static List<Edge>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

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
            adjList[v].add(new Edge(u, cost));
        }

        ans = prim(1);
        System.out.println(ans);
    }

    private static int prim(int start) {
        boolean[] visit = new boolean[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        int max = 0;
        int total = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int cur = edge.w;
            int cost = edge.cost;

            if (visit[cur]) continue;

            visit[cur] = true;
            total += cost;
            if (cost > max) {
                max = cost;
            }

            for (Edge e : adjList[cur]) {
                if (!visit[e.w]) {
                    pq.offer(e);
                }
            }
        }

        return total - max;
    }
}