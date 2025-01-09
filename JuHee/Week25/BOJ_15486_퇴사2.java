import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] T; // 상담 소요 일자
    static int[] P; // 상담 후 받을 수 있는 금액
    static int max_money=Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];

        for(int day = 0; day < N; day++){
            st = new StringTokenizer(br.readLine());
            T[day] = Integer.parseInt(st.nextToken());
            P[day] = Integer.parseInt(st.nextToken());
        }

        answer(0,0);

        System.out.println(max_money);
    }

    public static void answer(int day, int money) {
        if(day > N){
            return;
        }
        if(day == N){
            max_money = Math.max(max_money, money);
            return;
        }

        answer(day+T[day], money+P[day]);
        // 현재 작업을 선택하고 다음 작업을 선택하는 경우
        answer(day+1, money);
        // 현재 작업을 선택하지 않고 다음 날짜로 이동하는 경우
    }
}
