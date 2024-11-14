import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringTokenizer st;

    static int V, E;
    static int a, b, cost;
    static List<Node> graph;

    public static class Node implements Comparable<Node> {
        int start;
        int end;
        int cost;

        public Node(int s, int e, int c) {
            start = s;
            end = e;
            cost = c;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    static int[] parent;
    static int allCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            st = new StringTokenizer(br.readLine());

            // "0 0"이 입력되면 종료
            if (!st.hasMoreTokens()) {
                break;
            }

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            allCost = 0;

            if (V == 0 && E == 0) {
                break;
            }

            graph = new ArrayList<>();
            parent = new int[V];

            // 초기 부모 설정
            for (int i = 0; i < V; i++) {
                parent[i] = i;
            }

            // 간선 입력 받기
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                cost = Integer.parseInt(st.nextToken());

                // 양방향 간선 추가
                graph.add(new Node(a, b, cost));
                graph.add(new Node(b, a, cost));

                allCost += cost;
            }

            // 간선 비용 오름차순 정렬
            Collections.sort(graph);

            int selectCost = 0;

            // 크루스칼 알고리즘으로 최소 스패닝 트리 만들기
            for (Node node : graph) {
                int x = node.start;
                int y = node.end;

                // 사이클이 생기지 않으면 union
                if (find(x) != find(y)) {
                    union(x, y);
                    selectCost += node.cost;
                }
            }

            // 전체 비용에서 최소 스패닝 트리의 비용을 빼서 출력
            System.out.println(allCost - selectCost);
        }
    }

    // find 연산 (경로 압축)
    public static int find(int x) {
        if (parent[x] == x) return parent[x];
        return parent[x] = find(parent[x]);
    }

    // union 연산 (Union by Rank)
    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rootX > rootY) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
            }
        }
    }
}
