import java.util.*;
import java.io.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight; // 가중치 오름차순 정렬
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 행성의 개수
        List<Edge> edges = new ArrayList<>(); // 간선 리스트

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i; // 초기 부모는 자기 자신
        }

        // 가중치 행렬 입력 처리
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int weight = Integer.parseInt(st.nextToken());
                if (i < j) { // 중복 간선 제거 (i < j 조건)
                    edges.add(new Edge(i, j, weight));
                }
            }
        }

        // 간선 정렬 (가중치 기준 오름차순)
        Collections.sort(edges);

        // 크루스칼 알고리즘 수행
        long totalWeight = 0; // 최소 스패닝 트리 가중치 합
        for (Edge edge : edges) {
            if (find(edge.start) != find(edge.end)) { // 사이클이 생기지 않으면
                union(edge.start, edge.end);
                totalWeight += edge.weight;
            }
        }

        System.out.println(totalWeight);
    }

    // 유니온-파인드: find 연산
    public static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }

    // 유니온-파인드: union 연산
    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootX] = rootY; // 부모 갱신 
        }
    }
}
