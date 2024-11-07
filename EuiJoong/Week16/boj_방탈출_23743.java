import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Edge {
        int node1;
        int node2;
        int cost;

        public Edge(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "node1=" + node1 +
                    ", node2=" + node2 +
                    ", cost=" + cost +
                    '}';
        }
    }

    static int N, M;
    static int[] p;
    static List<Edge> edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(node1, node2, cost));
        }

        st = new StringTokenizer(br.readLine());
        int[] exit = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            exit[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N + 1; i++) {
            int node1 = 0;
            int node2 = i;
            int cost = exit[i];
            edgeList.add(new Edge(node1, node2, cost));
        }

        Collections.sort(edgeList, (o1, o2) -> o1.cost - o2.cost);
        makeSet();
        kruskal();


    }

    private static void makeSet() {
        p = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
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

        if (x == y) return;

        if (x < y) {
            p[y] = x;
        } else {
            p[x] = y;
        }
    }

    private static void kruskal() {
        int res = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            if (find(edgeList.get(i).node1) != find(edgeList.get(i).node2)) {
                res += edgeList.get(i).cost;
                union(edgeList.get(i).node1, edgeList.get(i).node2);
            }
        }
        System.out.println(res);
    }
}
