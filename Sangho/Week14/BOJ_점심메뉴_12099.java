import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int N, Q;
    static int a, b;
    static List<int[]> menuData;
    static int u, v, x, y;
    static List<int[]> likeData;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        menuData = new ArrayList<>();
        likeData = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            menuData.add(new int[]{a, b});
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            likeData.add(new int[]{u, v, x, y});
        }

        // 매운맛 기준 정렬
        menuData.sort(Comparator.comparingInt(o -> o[0]));

        for (int[] like : likeData) {
            u = like[0];
            v = like[1];
            x = like[2];
            y = like[3];

            // 이분탐색을 활용해 범위 내의 메뉴 개수를 찾음
            int count = findDishes(u, v, x, y);
            System.out.println(count);
        }
    }

    // 범위 내의 메뉴 개수 찾는 함수 (이분 탐색 활용)
    private static int findDishes(int u, int v, int x, int y) {
        int left = lowerBound(u); // 매운맛이 u 이상인 첫 번째 인덱스 찾기
        int right = upperBound(v); // 매운맛이 v 이하인 마지막 인덱스 찾기

        int count = 0;
        // 찾은 범위 내에서 단맛 범위 검사
        for (int i = left; i < right; i++) {
            if (menuData.get(i)[1] >= x && menuData.get(i)[1] <= y) {
                count++;
            }
        }
        return count;
    }

    // 매운맛이 u 이상인 첫 번째 인덱스 찾기 (lower bound)
    private static int lowerBound(int u) {
        int left = 0, right = menuData.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (menuData.get(mid)[0] < u) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // 매운맛이 v 이하인 마지막 인덱스 찾기 (upper bound)
    private static int upperBound(int v) {
        int left = 0, right = menuData.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (menuData.get(mid)[0] <= v) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
