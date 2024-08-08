import java.util.*;

public class Main {
    static List<Pair<Integer, Integer>> home = new ArrayList<>();
    static List<Pair<Integer, Integer>> combi = new ArrayList<>();
    static boolean[] visit;
    static int minCal = 100000;
    static void dfs(int start, int m) {
        if (m == 0) {
            List<Pair<Integer, Integer>> chicken = new ArrayList<>();
            int ans = 0;
            for (int i = 0; i < visit.length; i++) {
                if (visit[i]) {
                    chicken.add(combi.get(i));
                }
            }
            for (Pair<Integer, Integer> h : home) {
                int tempSum = 100000;
                for (Pair<Integer, Integer> c : chicken) {
                    tempSum = Math.min(tempSum, Math.abs(h.first - c.first) + Math.abs(h.second - c.second));
                }
                ans += tempSum;
            }
            minCal = Math.min(ans, minCal);
        }
        for (int i = start; i < combi.size(); i++) {
            visit[i] = true;
            dfs(i + 1, m - 1);
            visit[i] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] cities = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cities[i][j] = sc.nextInt();
                if (cities[i][j] == 2) {
                    combi.add(new Pair<>(i, j));
                } else if (cities[i][j] == 1) {
                    home.add(new Pair<>(i, j));
                }
            }
        }
        visit = new boolean[combi.size()];
        Arrays.fill(visit, 0, M, true);
        dfs(0, M);
        System.out.println(minCal);
    }

    static class Pair<y, x> {
        y first;
        x second;

        Pair(y first, x second) {
            this.first = first;
            this.second = second;
        }
    }

}
