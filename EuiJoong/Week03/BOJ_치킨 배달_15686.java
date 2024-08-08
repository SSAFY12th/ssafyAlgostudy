import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] graph;
    static StringTokenizer st;
    static boolean[] isOpen;
    static ArrayList<int[]> chicken;
    static ArrayList<int[]> home;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        chicken = new ArrayList<>();
        home = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) {
                    home.add(new int[] {i, j});
                }else if (graph[i][j] == 2) {
                    chicken.add(new int[] {i, j});
                }
            }
        }

        isOpen = new boolean[chicken.size()];
        dfs(0,0);
        System.out.println(ans);
    }
    public static void dfs(int start, int depth) {
        if (depth == M) {
            int cityChickenDistance = 0;
            for (int i = 0; i < home.size(); i++) {
                int chickenDistance = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (isOpen[j]) {
                        int distance = Math.abs(home.get(i)[0] - chicken.get(j)[0]) + Math.abs(home.get(i)[1] - chicken.get(j)[1]);
                        chickenDistance = Math.min(chickenDistance, distance);
                    }
                }
                cityChickenDistance += chickenDistance;
            }
            ans = Math.min(ans, cityChickenDistance);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            isOpen[i] = true;
            dfs(i + 1, depth + 1);
            isOpen[i] = false;
        }
    }
}