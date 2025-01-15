import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for (int i = 0; i <= n; i++)
            edges.add(new ArrayList<>());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                if(i == j) break;
                int bool = Integer.parseInt(st.nextToken());
                if(bool == 1) {
                    edges.get(i).add(j);
                    edges.get(j).add(i);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = 0;

        for (int i = 2; i <= m; i++) {
            end = Integer.parseInt(st.nextToken());
            if(!bfs(start, end)) {
                System.out.println("NO");
                return;
            }
            start = end;
        }

        System.out.println("YES");

    }

    public static boolean bfs(int start, int end) {

        if(start == end) return true;

        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();

        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int n = q.poll();

            for (Integer next : edges.get(n)) {
                if(next == end)
                    return true;
                else if(!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        return false;
    }
}
