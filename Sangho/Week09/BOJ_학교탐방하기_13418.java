import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] parent;

    public static class Edge {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 건물의 수 (정점)
        M = Integer.parseInt(st.nextToken());  // 도로의 수 (간선)

        Edge[] edges = new Edge[M + 1];  // 간선 배열, M개의 간선 + 학교에서 시작하는 도로 1개

        // 입력 받기
        for (int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());  // 시작 정점
            int v = Integer.parseInt(st.nextToken());  // 끝 정점
            int weight = Integer.parseInt(st.nextToken()) == 0 ? 1 : 0;  // 오르막길(1), 내리막길(0)로 수정

            edges[i] = new Edge(u, v, weight);
        }

        // 최소 피로도 계산 (오름차순)
        int minFatigue = kruskal(N, edges, true);
        // 최대 피로도 계산 (내림차순)
        int maxFatigue = kruskal(N, edges, false);

        // 최대 피로도와 최소 피로도의 차이 제곱 출력
        System.out.println(maxFatigue * maxFatigue - minFatigue * minFatigue);
    }

    static int kruskal(int n, Edge[] edges, boolean ascending) {
        // true면 오름차순 (최소 피로도), false면 내림차순 (최대 피로도)
        Arrays.sort(edges, (a, b) -> ascending ? a.weight - b.weight : b.weight - a.weight);

        // 부모 배열 초기화
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        // 가중치 계산용 변수 (피로도)
        int fatigue = 0;

        // 연결된 엣지 수
        int edgesUsed = 0;

        // 간선들을 순차적으로 확인
        for (Edge edge : edges) {
            // 두 정점이 같은 집합에 속해있지 않다면, 즉 사이클이 생기지 않는다면
            if (find(edge.u) != find(edge.v)) {
                // 두 정점을 연결 (union)
                union(edge.u, edge.v);
                // 피로도는 오르막길(1)일 경우 피로도 증가
                fatigue += edge.weight;
                // 사용된 엣지 수 증가
                edgesUsed++;
            }
            // N개의 노드를 연결하는 N-1개의 간선을 사용하면 종료
            if (edgesUsed == n) {
                break;
            }
        }

        return fatigue;
    }

    // 파인드
    static int find(int x) {
        // 자기 자신이 부모 노드이면 그대로 반환
        if (parent[x] == x) return x;
        // 자기 자신이 부모가 아닌 경우 부모를 따라 올라가서 부모 노드를 찾는다
        // 경로 압축, 부모값 갱신
        return parent[x] = find(parent[x]);
    }

    // 병합
    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        // 두 정점이 다른 집합에 속해 있다면
        if (x != y) {
            // x의 루트 노드를 y의 루트 노드로 연결
            parent[x] = y;
        }
    }
}
