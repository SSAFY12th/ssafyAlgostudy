import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {

        int start;
        int cost;

        public Node(int start, int cost) {
            this.start = start;
            this.cost = cost;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<Node>[] adjList = new ArrayList[N + 1];

        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList[u].add(new Node(v, w));
        }

        //X에서 갈 수 있는 모든 정점의 최소거리
        int[] startX = dijkstra(X, N, adjList);

        //모든 정점에서 X로 갈 때, 최소거리
        int[] endX = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (i != X) {
                int[] tmpArr = dijkstra(i, N, adjList);
                endX[i] = tmpArr[X];
            }
        }

        //최댓값 구하기
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, startX[i] + endX[i]);
        }

        System.out.println(ans);

    }

    private static int[] dijkstra(int start, int N, List<Node>[] adjList) {

        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int v = node.start;

            if (visited[v]) continue;

            visited[v] = true;
            for (Node next : adjList[v]) {
                if (dist[next.start] > dist[v] + next.cost) {
                    dist[next.start] = dist[v] + next.cost;
                    pq.offer(new Node(next.start, dist[next.start]));
                }
            }
        }

        return dist;

    }
}
