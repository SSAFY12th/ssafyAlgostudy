import java.util.*;
import java.io.*;

public class Main {

    static int N, M, X;
    static int[] dist;
    static int[] studentDist;
    static List<List<Node>> edgs = new ArrayList<>();

    static class Node implements Comparable<Node> {
        int node, weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        studentDist = new int[N+1];
        for (int i = 0; i < M+1; i++)
            edgs.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgs.get(from).add(new Node(to, weight));
        }

        for (int i = 1; i <= N; i++) {
            dijkstra(i);
            studentDist[i] = dist[X];
        }

        dijkstra(X);

        for (int i = 1; i <= N; i++)
            studentDist[i] += dist[i];

        Arrays.sort(studentDist);

        System.out.println(studentDist[N]);

    }

    public static void dijkstra(int start) {
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            if(dist[curr.node] < curr.weight) continue;

            for (Node next : edgs.get(curr.node)) {
                if(dist[next.node] > dist[curr.node] + next.weight) {
                    dist[next.node] = dist[curr.node] + next.weight;
                    pq.add(new Node(next.node, dist[next.node]));
                }
            }
        }
    }
}
