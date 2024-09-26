import java.util.*;
import java.io.*;

public class Main {

  // 크루스칼 알고리즘

    static int N, M;
    static int[] parents;
    static List<Edge> edges = new ArrayList<>();

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

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, weight));
        }

        make();
        Collections.sort(edges);

        int cost = 0;
        List<Edge> connEdge = new ArrayList<>();    // 선택한 간선을 따로 저장
        for (Edge edge : edges) {
            if(union(edge.from, edge.to)) {
                cost += edge.weight;
                connEdge.add(edge);
            }
        }

        // 이미 정렬된 리스트에서 차례대로 뽑아 추가한거기때문에, connEdge는 정렬된 상태이다.
        Edge last = connEdge.remove(connEdge.size()-1);
        cost -= last.weight;

        System.out.println(cost);
    }

    public static void make() {
        parents = new int[N+1];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    public static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

}
