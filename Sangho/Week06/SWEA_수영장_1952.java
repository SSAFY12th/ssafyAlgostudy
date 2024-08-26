import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static StringTokenizer st;

    static int T;

    static int[] prices;
    static int[] plans;
    static boolean[] selected;

    static int answer;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {

            prices = new int[4];
            plans = new int[12];
            selected = new boolean[12];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) {
                plans[i] = Integer.parseInt(st.nextToken());
            }

            // 최악의 경우 1년권을 사버리는거임
            answer = prices[3];

            DFS(0, 0);

            System.out.println("#" + tc + " " + answer);

        }
    }

    public static void DFS(int month, int cost) {
        // 종료조건, 12월이 넘어가면 == 가아닌 이유는 11월에 3개월짜리를 끊을수도 있으니까
        if (month > 11) {
            // 최솟값 갱신
            if (cost < answer) {
                answer = cost;
            }
            return;
        }

        // 해당 달에 이용 계획이 존재하는 경우
        if (plans[month] > 0) {
            // 1일권 사용
            DFS(month + 1, cost + prices[0] * plans[month]);

            // 1달권 사용
            DFS(month + 1, cost + prices[1]);

            // 3달권 사용
            DFS(month + 3, cost + prices[2]);

        } else {
            // 안사고 걍 넘어감
            DFS(month + 1, cost);
        }
    }
}
