import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int N, M;

    static int[] parent;

    public static class Edge implements Comparable<Edge> {
        int start, destination, weight;

        Edge(int start, int destination, int weight) {
            this.start = start;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(start, destination, weight);
        }

        int answer = kruskal(N, edges);

        System.out.println(answer);
    }

    public static int kruskal(int n, Edge[] edges) {
        // 비용 오름차순 정렬
        Arrays.sort(edges);

        // 최상위 정점 저장
        parent = new int[n + 1];

        // 자기 자신으로 초기화
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        // 전체 비용 저장 변수
        int fatigue = 0;

        // 간선 중 최대 비용 저장
        int bigcost = 0;

        // 몇개의 정점을 탐색했는지 세는 변수
        int count = 0;

        for (Edge edge : edges) {
            if (find(edge.start) != find(edge.destination)) {

                fatigue += edge.weight;
                union(edge.start, edge.destination);

                // 간선의 비용순으로 정렬하므로, 마지막 간선의 비용이 전체 비용중 가장 비쌈
                // 가장 비싼 간선 제거하면 두개의 마을을 분리하면서 최소 비용임
                bigcost = edge.weight;
                count++;

            }

            // n - 1 개의 정점을 탐색 했으면 다 돈거임
            if (count == n - 1) {
                break;
            }
        }

        // 전체 비용에서 최고 비용 간선 제거한게 답
        return fatigue - bigcost;
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[x] = y;
        }
    }
}
