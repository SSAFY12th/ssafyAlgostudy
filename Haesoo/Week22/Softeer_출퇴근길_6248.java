import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, S, T;
    public static List <Integer>[] relation;
    public static List <Integer>[] reverse;
    public static boolean[] visited1 = new boolean[100000];
    public static boolean[] visited2 = new boolean[100000];
    public static boolean[] visited3 = new boolean[100000];
    public static boolean[] visited4 = new boolean[100000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        relation = new ArrayList[N];
        reverse = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            relation[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            relation[start - 1].add(end - 1);
            reverse[end - 1].add(start - 1);
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()) - 1;
        T = Integer.parseInt(st.nextToken()) - 1;
        visited1[T] = true;
        visited2[S] = true;

        dfs(visited1, S, relation);
        dfs(visited2, T, relation);
        dfs(visited3, S, reverse);
        dfs(visited4, T, reverse);
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (visited1[i] && visited2[i] && visited3[i] && visited4[i]) cnt++;
        }
        System.out.println(cnt - 2);
    }
    public static void dfs (boolean[] visited, int idx, List <Integer> [] v) {
        if (visited[idx]) return;
        visited[idx] = true;
        for (int i = 0; i < v[idx].size(); i++) {
            dfs(visited, v[idx].get(i), v);
        }
    }
}
