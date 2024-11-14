import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int N,range,E;

    static int[] items;

    static int a,b,weight;

    // 다익스트라 관련
    static int INF = Integer.MAX_VALUE;

    static int[] distance;

    static List<Edge>[] graph;

    public static class Edge implements Comparable<Edge>{
        int vertex;
        int weight;

        public Edge(int v, int w){
            vertex = v;
            weight = w;
        }

        @Override
        public int compareTo(Edge o){
            return weight - o.weight;
        }
    }

    static PriorityQueue<Edge> pq;

    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        range = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        items = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[N + 1];

        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        distance = new int[N + 1];

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b,weight));
            graph[b].add(new Edge(a,weight));
        }

        // 각 정점 별로 다른 모든 정점을 향해

        for(int i = 1; i <= N; i++) {
            dijkstra(i);
            // 비용 갱신 메서드
            simulate();
        }

        System.out.println(answer);

    }

    public static void simulate() {
        // 각 distance가 거리보다 작으면 총량에 추가 후 갱신
        int value = 0;

        for(int i = 1; i <= N; i++) {
            if(distance[i] <= range) {
                value += items[i];
            }
        }

        answer = Math.max(answer,value);
    }

    public static void dijkstra(int start) {
        pq = new PriorityQueue<>();

        pq.add(new Edge(start,0));

        for(int i = 1; i <= N; i++) {
            distance[i] = INF;
        }

        distance[start] = 0;

        while(!pq.isEmpty()){
            Edge currentEdge = pq.poll();
            int currentVertex = currentEdge.vertex;
            int currentWeight = currentEdge.weight;

            if(distance[currentVertex] < currentWeight) continue;

            for(Edge edge : graph[currentVertex]){
                int nextVertex = edge.vertex;
                int nextWeight = edge.weight + currentWeight;

                if(distance[nextVertex] > nextWeight) {
                    distance[nextVertex] = nextWeight;
                    pq.add(new Edge(nextVertex,nextWeight));
                }
            }
        }
    }
}

// int answer = Integer.MIN_VALUE;
// 정점 -> 모든 정점 비용 계산
// 수색범위보다 비용이 저렴하다면 해당 정점의 아이템 획득
// answer 갱신
