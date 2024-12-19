import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, S, T;
    static List<List<Integer>> forwardGraph, reverseGraph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        forwardGraph = new ArrayList<>();
        reverseGraph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            forwardGraph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            forwardGraph.get(u).add(v);
            reverseGraph.get(v).add(u);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        Set<Integer> s3 = new HashSet<>();
        Set<Integer> s4 = new HashSet<>();

        dfs(S, T, forwardGraph, s1, new boolean[N + 1]);
        dfs(T, -1, reverseGraph, s2, new boolean[N + 1]);
        dfs(T, S, forwardGraph, s3, new boolean[N + 1]);
        dfs(S, -1, reverseGraph, s4, new boolean[N + 1]);

//        System.out.println("s1 = " + s1);
//        System.out.println("s2 = " + s2);
//        System.out.println("s3 = " + s3);
//        System.out.println("s4 = " + s4);
        s1.retainAll(s2);
        s3.retainAll(s4);
        s1.retainAll(s3);
//        System.out.println(s1);
        int ans = s1.size();
        if (s1.contains(S)) ans--;
        if (s1.contains(T)) ans--;
        System.out.println(ans);



    }

    public static void dfs(int v, int stop, List<List<Integer>> graph, Set<Integer> set, boolean[] visited) {
        if (stop != -1 && v == stop) {
            return;
        }

        for (int i = 0; i < graph.get(v).size(); i++) {
            int nextNode = graph.get(v).get(i);

            if (visited[nextNode]) continue;

            visited[nextNode] = true;
            set.add(nextNode);
            dfs(nextNode, stop, graph, set, visited);
        }

    }
}
