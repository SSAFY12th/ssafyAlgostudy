import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek1005 {

    static int t;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {

            Queue<int[]> queue = new ArrayDeque<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] degree = new int[n+1];
            int[] time = new int[n+1];
            int[] result = new int[n+1];
            ArrayList<Integer>[] graph = new ArrayList[n+1];

            for (int i=0; i<=n; i++) {
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i=1; i<=n; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            for (int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
            }

            st = new StringTokenizer(br.readLine());
            int end = Integer.parseInt(st.nextToken());

            for (int i=1; i<=n; i++) {
                for (int node : graph[i]) {
                    degree[node]++;
                }
            }

            for (int i=1; i<=n; i++) {
               if (degree[i]==0) {
                   result[i] = time[i];
                   queue.offer(new int[]{i,time[i]});
               }
            }

            while (!queue.isEmpty()) {

                int[] node = queue.poll();

                for (int j=0; j < graph[node[0]].size(); j++) {

                    int nextNode = graph[node[0]].get(j);
                    degree[nextNode]--;

                    result[nextNode] = Math.max(result[nextNode],node[1] + time[nextNode]);

                    if (degree[nextNode] == 0) {
                        queue.offer(new int[]{nextNode, result[nextNode]});
                    }
                }
            }

            System.out.println(result[end]);


        }

    }
}
