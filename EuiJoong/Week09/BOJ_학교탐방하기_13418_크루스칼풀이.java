import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] graph;
    static int[] p;
    static int N;
    static int M;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[M + 1][3];
        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //최소 구하기. 최소를 구하려면 내리막(1)이 우선적으로 와야함
        Arrays.sort(graph, (o1, o2) -> o2[2] - o1[2]);
        makeSet();
        int min = kruskal();

        //최대 구하기. 최대를 구하려면 오르막(0)이 우선적으로 와야함
        Arrays.sort(graph, ((o1, o2) -> o1[2] - o2[2]));
        makeSet();
        int max = kruskal();

        ans = max - min;
        System.out.println(ans);

    }

    private static void makeSet() {
        p = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            p[i] = i;
        }
    }

    private static int find(int x) {
        if (p[x] == x) {
            return x;
        }
        return p[x] = find(p[x]);
    }

    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }

        if (rootX < rootY) {
            p[rootY] = rootX;
        }else {
            p[rootX] = rootY;
        }
    }

    private static int kruskal() {
        int res = 0;

        for (int i = 0; i < graph.length; i++) {
            if (find(graph[i][0]) != find(graph[i][1])) {
                if (graph[i][2] == 0) {
                    res++;
                }
                union(find(graph[i][0]), find(graph[i][1]));
            }
        }

        return res * res;
    }
}