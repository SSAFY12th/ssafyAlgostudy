import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int N,M;

    static int[] time;

    static int[] parent;

    static List<Edge> graph;

    public static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int cost;

        public Edge(int s, int e, int c) {
            start = s;
            end = e;
            cost = c;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        // 각 방의 몇
        time = new int[N];
        parent = new int[N + 1];

        for(int i = 0; i <= N; i++){
            parent[i] = i;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.add(new Edge(start,end,cost));
            graph.add(new Edge(end,start,cost));
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < time.length; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        // 모든 정점 탐색
        for(int i = 1; i <= N; i++) {
            graph.add(new Edge(0,i,time[i-1]));
        }


        Collections.sort(graph);

        kruskal();

        System.out.println(answer);

    }

    public static void kruskal() {

        int totalCost = 0;

        for(Edge edge : graph) {
            int start = edge.start;
            int end = edge.end;
            int cost = edge.cost;

            if(find(start) != find(end)) {
                union(start,end);
                totalCost += cost;
            }
        }


        answer = Math.min(answer,totalCost);
    }

    public static int find (int x) {
        if(parent[x] == x) return parent[x];
        return parent[x] = find(parent[x]);
    }

    public static void union (int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX > rootY) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
        }
    }


}
