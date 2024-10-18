import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int N,M,K;

    static int[] parent;

    static List<Integer> powerPlants;

    static List<Edge> edges;

    public static class Edge{
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        powerPlants = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            powerPlants.add(Integer.parseInt(st.nextToken()));
        }

        edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, w));
        }

        edges.sort(Comparator.comparingInt(edge -> edge.weight));

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 발전소는 하나의 집합으로 합치기
        for (int i = 0; i < powerPlants.size() - 1; i++) {
            union(powerPlants.get(i), powerPlants.get(i + 1));
        }

        int result = 0;
        for (Edge edge : edges) {
            int u = edge.u;
            int v = edge.v;
            int w = edge.weight;

            if (find(u) != find(v)) {
                union(u, v);
                result += w;
            }
        }

        System.out.println(result);
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}
