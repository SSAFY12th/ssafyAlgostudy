package swea;

import java.io.*;
import java.util.*;

public class Num1952 {

    static int t, cash[], plan[], min[], result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= t; test_case++) {

            cash = new int[4];
            plan = new int[12];
            min = new int[20];
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < 4; i++)
                cash[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
                if(plan[i]*cash[0] > cash[1]) { // 일 요금보다 월 요금이 쌀 때
                    min[i] = cash[1];
                }
                else
                    min[i] = plan[i] * cash[0];
            }

            result = cash[3];
            dfs(0, 0);

            System.out.println("#"+test_case+" "+ result);

        }

    }

    public static void dfs(int cnt, int sum) {
        if(cnt >= 12) {
            // 최소값 계산 후 return
            result = Math.min(result, sum);
            return;
        }

        // 현재 위치에서 3개월 결제 해보기.
        dfs(cnt+3, sum+cash[2]);
        // 현재 위치에서는 1달 최소 요금 결제하고 다음 달에서 3개월 결제해보기
        dfs(cnt+1, sum+min[cnt]);

    }
}
