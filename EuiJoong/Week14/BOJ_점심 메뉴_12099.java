import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
맛을 매운맛을 기준으로 정렬
u, v 구간안에 있는 매운맛들을 탐색. 그 것 중 x, y 구간안에 있는 단맛들을 탐색
매운맛의 상한, 하한을 구한 뒤 범위 안의 단맛이 범위 내라면 count++
 */
public class Main {

    static class Taste {
        int hot;
        int sweet;

        public Taste(int hot, int sweet) {
            this.hot = hot;
            this.sweet = sweet;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        List<Taste> list = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Taste(a, b));
        }

        Collections.sort(list, (o1, o2) -> o1.hot - o2.hot);

        for (int i = 0; i < Q; i++) {
            int cnt = 0;
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            //매운맛이 포함되는 하한과 상한 찾기
            int left = lowerBound(list, u);
            int right = upperBound(list, v);
            //매운맛 포함하는 범위 안에서 단맛이 범위 안에 포함된다면 둘 다 좋아하는 메뉴의 수임
            for (int j = left; j < right; j++) {
                if (list.get(j).sweet >= x && list.get(j).sweet <= y) {
                    cnt++;
                }
            }
            System.out.println(cnt);


        }
    }

    private static int lowerBound(List<Taste> list, int target) {

        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid).hot >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static int upperBound(List<Taste> list, int target) {

        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid).hot > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


}
