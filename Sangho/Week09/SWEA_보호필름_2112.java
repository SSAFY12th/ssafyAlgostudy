import java.util.*;

public class Solution {
    static int D, W, K, minDrugs;
    static int[][] film, originalFilm;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            D = sc.nextInt();
            W = sc.nextInt();
            K = sc.nextInt();
            film = new int[D][W];
            originalFilm = new int[D][W];

            for (int i = 0; i < D; i++) {
                for (int j = 0; j < W; j++) {
                    film[i][j] = sc.nextInt();
                    originalFilm[i][j] = film[i][j];
                }
            }

            minDrugs = Integer.MAX_VALUE;

            if (check()) {
                minDrugs = 0;
            } else {
                dfs(0, 0);
            }

            System.out.println("#" + t + " " + minDrugs);
        }
    }

    static void dfs(int depth, int count) {
        if (count >= minDrugs) return;

        if (depth == D) {
            if (check()) {
                minDrugs = Math.min(minDrugs, count);
            }
            return;
        }

        // 현재 막을 변경하지 않고 진행
        dfs(depth + 1, count);

        // 현재 막을 A로 변경
        modifyLayer(depth, 0);
        dfs(depth + 1, count + 1);
        restoreLayer(depth);

        // 현재 막을 B로 변경
        modifyLayer(depth, 1);
        dfs(depth + 1, count + 1);
        restoreLayer(depth);
    }

    static void modifyLayer(int layer, int value) {
        for (int i = 0; i < W; i++) {
            film[layer][i] = value;
        }
    }

    static void restoreLayer(int layer) {
        for (int i = 0; i < W; i++) {
            film[layer][i] = originalFilm[layer][i];
        }
    }

    static boolean check() {
        for (int col = 0; col < W; col++) {
            int maxStreak = 1;
            int streak = 1;
            for (int row = 1; row < D; row++) {
                if (film[row][col] == film[row - 1][col]) {
                    streak++;
                    maxStreak = Math.max(maxStreak, streak);
                } else {
                    streak = 1;
                }
            }
            if (maxStreak < K) return false;
        }
        return true;
    }
}
