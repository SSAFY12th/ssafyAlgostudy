import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int n, m;
    static int[][] city;
    static List<int[]> houses = new ArrayList<>();
    static List<int[]> chickenStores = new ArrayList<>();
    static int minChickenDistance = Integer.MAX_VALUE;
    static boolean[] selected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        city = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                city[i][j] = sc.nextInt();
                if (city[i][j] == 1) {
                    houses.add(new int[]{i, j});
                } else if (city[i][j] == 2) {
                    chickenStores.add(new int[]{i, j});
                }
            }
        }

        selected = new boolean[chickenStores.size()];
        selectChickenStores(0, 0);

        System.out.println(minChickenDistance);
    }

    public static void selectChickenStores(int start, int count) {
        if (count == m) {
            calculateChickenDistance();
            return;
        }

        for (int i = start; i < chickenStores.size(); i++) {
            selected[i] = true;
            selectChickenStores(i + 1, count + 1);
            selected[i] = false;
        }
    }

    public static void calculateChickenDistance() {
        int totalDistance = 0;

        for (int[] house : houses) {
            int houseX = house[0];
            int houseY = house[1];
            int minDistance = Integer.MAX_VALUE;

            for (int i = 0; i < chickenStores.size(); i++) {
                if (selected[i]) {
                    int chickenX = chickenStores.get(i)[0];
                    int chickenY = chickenStores.get(i)[1];
                    int distance = Math.abs(houseX - chickenX) + Math.abs(houseY - chickenY);
                    minDistance = Math.min(minDistance, distance);  // 여기서 최대 대신 최소 거리를 선택
                }
            }

            totalDistance += minDistance;
        }

        minChickenDistance = Math.min(minChickenDistance, totalDistance);
    }
}
