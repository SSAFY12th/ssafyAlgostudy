import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int N,M;

    // 출발 정점, 도착 정점
    static int start, destination;

    // 그래프 정보
    static List<Node>[] graph;

    // 최단 거리 배열
    static int[] distance;

    static final int INF = Integer.MAX_VALUE;

    static class Node implements Comparable<Node> {
        int vertex;
        int cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;  // 비용 기준 오름차순 정렬
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        // 정점의 개수
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        // 간선의 개수
        M = Integer.parseInt(st.nextToken());

        // 노드 연결 정보
        graph = new ArrayList[N + 1];

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 최단거리 저장 배열 (해당 정점까지 최소 거리를 저장한다)
        distance = new int[N + 1];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 해당 정점에서 목표 정점으로 가는 비용 저장
            graph[start].add(new Node(destination,cost));
        }

        st = new StringTokenizer(br.readLine());

        // 시작할 위치
        start = Integer.parseInt(st.nextToken());
        // 목표한 위치
        destination = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(distance[destination]);
    }

    public static void dijkstra(int start){
        Arrays.fill(distance, INF);
        // start 정점까지의 거리 == 0;
        distance[start] = 0;

        // 현재 정점으로 부터 가장 가까운 정점을 처리하기 위한 우선 순위 큐
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 시작 정점으로 시작 정점 -> cost 0
        pq.add(new Node(start,0));

        while (!pq.isEmpty()){
            Node current = pq.poll();
            int vertex = current.vertex;
            int cost = current.cost;

            // 현재 코스트가 저장된 코스트보다 크면 continue
            if(distance[vertex] < cost) continue;

            // 현재 정점과 연결된 모든 정점 탐색
            for(Node neighborNode : graph[vertex]){
                // 다음 정점 탐색
                int nextVertex = neighborNode.vertex;
                // 코스트 갱신
                int nextCost = cost + neighborNode.cost;

                if(nextCost < distance[nextVertex]){
                    // 거리 정보 갱신
                    distance[nextVertex] = nextCost;
                    // 더 짧은 거리 발견했으므로 탐색
                    pq.add(new Node(nextVertex,nextCost));
                }
            }
        }
    }
}
