import java.util.Scanner;

public class Main {
    static int N;
    static int[] time;
    static int[] pay;
    static int ans = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        time = new int[N + 1];
        pay = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            time[i] = sc.nextInt();
            pay[i] = sc.nextInt();
        }

        dfs(1, 0);
        System.out.println(ans);

    }
    public static void dfs(int day, int sum) {

        if (day == N + 1) {
            ans = Math.max(ans , sum);
            return;
        }
        if (day > N) {
            return;
        }

        System.out.println("날짜 : " + day +  ", 합계 : " + sum);
        dfs(day + time[day], sum + pay[day]); // 상담 하는 경우
        dfs(day + 1, sum); // 상담 건너뛰는 경우

    }
}