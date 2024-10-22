import java.util.*;
import java.io.*;

public class Main {

    static int N, Q;
    static Menu[] menus;

    static class Menu implements Comparable<Menu> {
        int a, b;
        public Menu(int a, int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public int compareTo(Menu o) {
            return this.a - o.a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        menus = new Menu[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            menus[i] = new Menu(a, b);
        }

        Arrays.sort(menus); // 매운맛을 기준으로 정렬된다.

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int minSpicy = Integer.parseInt(st.nextToken());
            int maxSpicy = Integer.parseInt(st.nextToken());
            int minSweet = Integer.parseInt(st.nextToken());
            int maxSweet = Integer.parseInt(st.nextToken());

            // 승관이가 먹는 메뉴의 범위를 찾는다.
            int[] spicyResult = binarySearch(minSpicy, maxSpicy);

            // 승관이가 먹는 메뉴 범위 내에서 영우가 먹는 메뉴를 찾아 카운트한다.
            int cnt = 0;
            for (int j = spicyResult[0]; j <= spicyResult[1]; j++) {
                if(menus[j].b >= minSweet && menus[j].b <= maxSweet)
                    cnt++;
            }

            sb.append(cnt).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int[] binarySearch(int min, int max) {
        int[] result = new int[2];
        int left, right;

        // lower bound
        left = 0; right = N-1;

        while(left <= right) {
            int mid = (left+right)/2;
            if(menus[mid].a >= min)
                right = mid-1;
            else
                left = mid+1;
        }
        result[0] = left;

        // upper bound
        left = 0; right = N-1;

        while(left <= right) {
            int mid = (left+right)/2;
            if(menus[mid].a > max)
                right = mid-1;
            else
                left = mid+1;
        }
        result[1] = right;

        return result;
    }
}
