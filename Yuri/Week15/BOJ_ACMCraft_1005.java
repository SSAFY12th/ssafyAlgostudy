import java.io.*;
import java.util.*;

public class Main {

    static int N, K, W;
    static int[] time, count, dp;
    static List<List<Integer>> edges = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            time = new int[N+1];
            count = new int[N+1];
            dp = new int[N+1];
            edges.clear();
            edges.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
                edges.add(new ArrayList<>());
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                edges.get(from).add(to);
                count[to]++;
            }

            W = Integer.parseInt(br.readLine());

            build();

            System.out.println(dp[W]);

        }
    }

    // 위상정렬
    public static void build() {
        Queue<Integer> q = new LinkedList<>();

        // 처음에 in이 없는 노드만 큐에 추가
        for (int i = 1; i <= N; i++) {
            if(count[i] == 0) {
                q.add(i);
                dp[i] = time[i];
            }
        }

        while(!q.isEmpty()) {
            int b = q.poll();

            if(b == W) return;  // 목표 건물을 지었다면 더 진행할 필요 없으므로 return

            for (Integer next : edges.get(b)) {
                dp[next] = Math.max(dp[next], dp[b]);   // next번 건물을 짓는데 필요한 시간 중 최대로 갱신.
                if(--count[next] == 0) {    // 더 이상 먼저 지을 건물이 없다면
                    q.add(next);            // 큐에 추가
                    dp[next] += time[next]; // 자기 자신을 짓는 시간 추가
                }
            }
        }
    }
}
