import java.util.*;
import java.io.*;

public class BOJ1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] depth = new int[1000];
            List<Integer>[] relations = new List[N];
            int[] cost = new int[1000];
            int[] result = new int[1000];
            Queue<Integer> q = new ArrayDeque<>();
            for (int j = 0; j < N; j++) {
                relations[j] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[j] = Integer.parseInt(st.nextToken());
                result[j] = cost[j];
            }
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int prev = Integer.parseInt(st.nextToken()) - 1;
                int next = Integer.parseInt(st.nextToken()) - 1;
                depth[next]++;
                relations[prev].add(next);
            }
            int W = Integer.parseInt(br.readLine()) - 1;
            for (int j = 0; j < N; j++) {
                if (j == W) continue;
                if (depth[j] == 0) q.add(j);
            }
            while (!q.isEmpty()) {
                int curNode = q.peek();
                q.poll();
                for (int j = 0; j < relations[curNode].size(); j++) {
                    int next = relations[curNode].get(j);
                    result[next] = Math.max(result[next], result[curNode] + cost[next]);
                    if (--depth[next] == 0) q.add(next);
                }
            }
            System.out.println(result[W]);
        }
    }
}
