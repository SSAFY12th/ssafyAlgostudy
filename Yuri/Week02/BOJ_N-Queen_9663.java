import java.util.Scanner;

public class Main {

    static int n;
    static int[] arr;
    static int result = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];   // index값을 열로, value를 행으로 나타내는 배열

        dfs(0);

        System.out.println(result);

    }

    public static void dfs(int cnt) {
        if(cnt == n) {
            result++;
            return;
        }

        for(int i = 0; i < n; i++) {
            arr[cnt] = i;   // cnt열에 i행
            if(isOK(cnt)) {
                dfs(cnt+1);
            }
        }
    }

    public static boolean isOK(int c) {
        for(int i = 0; i < c; i++) {
            // 새로 놓은 퀸이 이전 퀸과 같은 행에 위치하는 경우.
            if(arr[c] == arr[i])
                return false;

            // 새로 놓은 퀸이 이전 퀸과 대각선 상에 위치하는 경우
            if(c - i == Math.abs(arr[c] - arr[i]))
                return false;

        }
        return true;
    }
}
