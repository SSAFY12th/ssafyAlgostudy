import java.io.*;
import java.util.*;

public class Main {

    static StringTokenizer st;

    static int n, m; // n: 정점의 개수, m: 간선의 개수
    static int startNode, endNode; // 출발점(s)과 도착점(t)

    static List<Edge> edges; // 모든 간선 정보를 저장하는 리스트

    static List<List<Integer>> graph; // 정방향 그래프
    static List<List<Integer>> reverseGraph; // 역방향 그래프

    // 간선을 표현하는 클래스
    static class Edge {
        int start;
        int end;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 정점과 간선의 개수를 입력받음
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();

        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            edges.add(new Edge(start, end));
            graph.get(start).add(end);
            reverseGraph.get(end).add(start);
        }

        // 출발점(s)과 도착점(t) 입력
        st = new StringTokenizer(br.readLine());
        startNode = Integer.parseInt(st.nextToken());
        endNode = Integer.parseInt(st.nextToken());

        // 출발점에서 도착점으로 도달 가능한 정점 집합
        Set<Integer> reachableFromStart = bfs(startNode, endNode, graph);
        // 도착점에서 역방향으로 도달 가능한 정점 집합
        Set<Integer> reachableToEnd = bfs(endNode, -1, reverseGraph);

        // 두 집합의 교집합 (출발점에서 도착점까지 도달 가능하고, 도착점에서 출발점으로도 도달 가능한 정점)
        reachableFromStart.retainAll(reachableToEnd);

        // 도착점에서 출발점으로 도달 가능한 정점 집합
        Set<Integer> reachableFromEnd = bfs(endNode, startNode, graph);
        // 출발점에서 역방향으로 도달 가능한 정점 집합
        Set<Integer> reachableToStart = bfs(startNode, -1, reverseGraph);

        // 두 집합의 교집합
        reachableFromEnd.retainAll(reachableToStart);

        // 최종적으로 모든 조건을 만족하는 정점 집합
        reachableFromStart.retainAll(reachableFromEnd);

        // 결과 계산
        int answer = reachableFromStart.size();

        // 출발점과 도착점 제외
        if (reachableFromStart.contains(startNode)) answer--;
        if (reachableFromStart.contains(endNode)) answer--;

        // 결과 출력
        System.out.println(answer);
    }

    public static Set<Integer> bfs(int start, int stop, List<List<Integer>> graph) {
        Set<Integer> reachable = new HashSet<>(); // 도달 가능한 정점들을 저장
        boolean[] visited = new boolean[n + 1]; // 방문 여부 체크 배열
        Queue<Integer> queue = new LinkedList<>(); // BFS를 위한 큐

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            // 특정 정점 도달하면 탐색 중단
            if (stop != -1 && node == stop) {
                continue;
            }

            reachable.add(node);

            // 인접 정점 탐색
            for (int next : graph.get(node)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        return reachable;
    }
}
