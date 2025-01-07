import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[] parents;

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
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        make();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                if(i == j) break;
                pq.add(new Edge(i, j, Integer.parseInt(st.nextToken())));
            }
        }

        long value = 0;
        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            if(union(e.from, e.to))
                value += e.weight;
        }

        System.out.println(value);

    }

    public static void make() {
        parents = new int[n+1];
        for (int i = 1; i <= n; i++)
            parents[i] = i;
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
