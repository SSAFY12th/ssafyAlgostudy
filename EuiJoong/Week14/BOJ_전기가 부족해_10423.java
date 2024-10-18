package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10423 {

    static int N, M, K;

    static int[] p;
    static int[][] graph;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        makeSet();

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < K; i++) {
            int power = Integer.parseInt(st.nextToken());
            p[power] = -1;
        }

        graph = new int[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.sort(graph, (o1, o2) -> o1[2] - o2[2]);


        kruskal();
    }

    private static void makeSet() {
        p = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            p[i] = i;
        }
    }

    private static int find(int x) {
        if (p[x] == x) return x;
        if (p[x] == -1) return -1;
        return p[x] = find(p[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == -1 && y == -1) return; //둘다 발전소면 리턴
        //부모가 다르다, 이어지지 않았다면?
        if (x != y) {
            // 만약 x가 발전소라면
            if (x == -1) {
                // y는 발전소에 연결됨
                p[y] = x;
            } else if (y == -1) { // 만약 y가 발전소라면
                p[x] = y; // x는 발전소에 연결됨
            } else { //만약 둘다 발전소가 아니라면?

                p[y] = x; // 그냥 연결!
            }
        }
    }

    private static void kruskal() {
        int cost = 0;
        for (int i = 0; i < graph.length; i++) {
            if (find(graph[i][0]) != find(graph[i][1])) {
                cost += graph[i][2];
                union(graph[i][0], graph[i][1]);

                if (checked()) break; // 모두 발전소에 연결되어 있다면 끝내기.
            }
        }
        System.out.println(cost);
    }

    private static boolean checked() {

        for (int i = 0; i < p.length; i++) {
            if (p[i] != -1) {
                return false;
            }
        }

        return true;
    }
}
