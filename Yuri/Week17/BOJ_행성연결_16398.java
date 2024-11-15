import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static long result;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

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
        result = 0;
        make();

        for (int i = 0; i <n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                if(i == j) 
                    break;
                int weight = Integer.parseInt(st.nextToken());
                pq.add(new Edge(i, j, weight));
            }
        }

        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            if(union(e.from, e.to))
                result += e.weight;
        }

        System.out.println(result);

    }

    public static void make() {
        parent = new int[n+1];
        for (int i = 1; i <= n; i++)
            parent[i] = i;
    }

    public static int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parent[bRoot] = aRoot;
        return true;
    }
}
