import java.util.Scanner;
import java.io.FileInputStream;
 
class Solution {
	
    static int N;
    static int[] arr;
 
    public static void main(String[] args) throws Exception {
    	
        Scanner sc = new Scanner(System.in);
 
        int T = 10;
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }
 
            int cnt = 0;
            for (int i = 2; i < N - 2; i++) {
                int max = Math.max(arr[i - 2], Math.max(arr[i - 1], Math.max(arr[i + 1], arr[i + 2])));
                if (arr[i] - max > 0) 
                    cnt += arr[i] - max; 
            }
 
            System.out.println("#" + tc + " " + cnt);
        }
    }
}
