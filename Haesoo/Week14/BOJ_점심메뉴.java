import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        flavor[] menu = new flavor[N];
        limit[] date = new limit[Q];
        for (int i = 0; i < N; i++) {// 메뉴 별 매운맛 단맛 입력
            st = new StringTokenizer(br.readLine());
            menu[i] = new flavor(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        }
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            date[i] = new limit(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        }
        Arrays.sort(menu);
        for (int i = 0; i < Q; i++) {
            int cnt = 0;
            int lo = 0, hi = N - 1;
            while (lo <= hi) {
                int midIdx = (lo + hi) / 2;
                if (menu[midIdx].curSpicy < date[i].minSpicy) lo = midIdx + 1;
                else hi = midIdx - 1;
            }
            int lowbo = lo;
            hi = N - 1;
            while (lo <= hi) {
                int midIdx = (lo + hi) / 2;
                if (menu[midIdx].curSpicy <= date[i].maxSpicy) lo = midIdx + 1;
                else hi = midIdx - 1; // lo = midIdx + 1;
            }
            int highbo = hi;

            for (int j = lowbo; j <= highbo; j++) {
                if (menu[j].curSweet >= date[i].minSweet && menu[j].curSweet <= date[i].maxSweet) cnt++;
            }
            System.out.println(cnt);
        }
    }
    static class limit {
        long minSpicy;
        long maxSpicy;
        long minSweet;
        long maxSweet;
        limit (long a, long b, long c, long d) {
            this.minSpicy = a;
            this.maxSpicy = b;
            this.minSweet = c;
            this.maxSweet = d;
        }
    }
    static class flavor implements Comparable<flavor> {
        long curSpicy;
        long curSweet;
        flavor (long a, long b) {
            this.curSpicy = a;
            this.curSweet = b;
        }

        @Override
        public int compareTo(flavor o) {
            return (int) (this.curSpicy - o.curSpicy);
        }
    }
}
