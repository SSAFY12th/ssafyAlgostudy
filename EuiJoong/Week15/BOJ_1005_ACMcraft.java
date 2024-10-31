import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T, N, K, W, ans;
    static int[] buildTime;
    static int[] dp;
    static List<Integer>[] adjList;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            buildTime = new int[N + 1];
            dp = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                buildTime[i] = Integer.parseInt(st.nextToken());
                dp[i] = buildTime[i];
            }

            adjList = new ArrayList[N + 1];
            for (int i = 0; i < N + 1; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                adjList[X].add(Y);
            }

            W = Integer.parseInt(br.readLine());

            ans = topologicalSort();
            System.out.println(ans);

        }
    }

    private static int topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        //진입차수 테이블 구성
        int[] enterDegree = new int[N + 1];

        for (int i = 0; i < adjList.length; i++) {
            for (int j = 0; j < adjList[i].size(); j++) {
                enterDegree[adjList[i].get(j)]++;
            }
        }

        //진입차수가 0인 노드들 q에 넣어주기
        for (int i = 1; i < enterDegree.length; i++) {
            if (enterDegree[i] == 0) {
                q.offer(i);
            }
        }

        //위상정렬 시작!
        while (!q.isEmpty()) {
            int node = q.poll();

            for (int connectedNode : adjList[node]) {
                //진입차수 감소
                enterDegree[connectedNode]--;
                //진입 차수가 0이라면 q에 넣기
                if (enterDegree[connectedNode] == 0) {
                    q.offer(connectedNode);
                }
                //dp테이블 갱신
                dp[connectedNode] = Math.max(dp[connectedNode], dp[node] + buildTime[connectedNode]);
            }
        }

        return dp[W];
    }
}
