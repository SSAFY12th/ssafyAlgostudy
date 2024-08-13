import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
    static int N;
    static int[] T;
    static int[] P;
    static int max_money = Integer.MIN_VALUE;

    static void answer(int day, int money) {
        if (day > N) return;
        if (day == N) {
            max_money = Math.max(max_money, money);
            return;
        }

        answer(day + T[day], money + P[day]); // 현재 작업을 선택하고 다음 작업을 선택하는 경우
        answer(day + 1, money);				  // 현재 작업을 선택하지 않고 다음 날짜로 이동하는 경우
        
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];

        for (int i = 0; i < N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        answer(0, 0);

        System.out.println(max_money);
    }
}
