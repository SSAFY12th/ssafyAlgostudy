import java.util.Scanner;
 
public class Solution {
 
    static int[] costs;
    static int[] plan;
    static int minCost;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int T = sc.nextInt();
 
        for (int tc = 1; tc < T + 1; tc++) {
            costs = new int[4];
            for (int i = 0; i < 4; i++) {
                costs[i] = sc.nextInt();
            }
 
            plan = new int[13];
            for (int i = 1; i <= 12; i++) {
                plan[i] = sc.nextInt();
            }
 
            minCost = costs[3];
 
            dfs(1, 0);
 
            System.out.println("#" + tc + " " + minCost);
        }
 
        sc.close();
    }
 
    public static void dfs(int month, int currentCost) {
        // 기저 조건: 12월을 넘어가면 최소 비용 갱신
        if (month > 12) {
            minCost = Math.min(minCost, currentCost);
            return;
        }
 
        // 일일
        dfs(month + 1, currentCost + costs[0] * plan[month]);
 
        // 월간
        dfs(month + 1, currentCost + costs[1]);
 
        // 3개월
        dfs(month + 3, currentCost + costs[2]);
    }
}
