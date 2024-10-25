import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int N,M,X;

    static List<Node>[] graph;

    public static class Node implements Comparable<Node> {
        int vertex;
        int cost;

        Node (int v, int c) {
            vertex = v;
            cost = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }

    }

    static PriorityQueue<Node> pq;

    // 시작 지점에서 목표 지점까지 거리 저장
    static int[] distance;

    static int INF = Integer.MAX_VALUE;

    // 가장 오래 걸리는 학생의 소요시간 출력
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];

        distance = new int[N + 1];

        for(int i = 0; i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end,cost));
        }

        for(int i = 1; i <= N; i++) {
            int dis = 0;

            dijkstra(i);
            dis += distance[X];
            dijkstra(X);
            dis += distance[i];

            answer = Math.max(answer,dis);
        }

        System.out.println(answer);

    }

    public static void dijkstra(int start) {
        Arrays.fill(distance,INF);

        pq = new PriorityQueue<>();

        distance[start] = 0;

        pq.add(new Node(start,0));

        while (!pq.isEmpty()){
            Node currentNode = pq.poll();
            int currentVertex = currentNode.vertex;
            int currentCost = currentNode.cost;

            if(distance[currentVertex] < currentCost) continue;

            for(Node node : graph[currentVertex]) {
                int nextVertex = node.vertex;
                int nextCost = currentCost + node.cost;

                if(distance[nextVertex] > nextCost) {
                    distance[nextVertex] = nextCost;
                    pq.add(new Node(nextVertex,nextCost));
                }

            }
        }
    }
}
