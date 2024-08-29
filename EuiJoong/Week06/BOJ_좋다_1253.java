
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static int N;
    private static int[] arr;
    private static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        ans = 0;
        if (N <= 2) {
            System.out.println(0);
        } else {
            for (int i = 0; i < N; i++) {

                int start = 0;
                int end = N - 1;
                int target = arr[i];


                while (start < end) {

                    if (end == i) {
                        end -= 1;
                        continue;
                    }
                    if (start == i) {
                        start += 1;
                        continue;
                    }
                    //System.out.println(i+","+start+","+end);
                    int sum = arr[start] + arr[end];
                    if (sum > target) {
                        end--;
                    } else if (sum < target) {
                        start++;
                    } else if (sum == arr[i]) {
                        ans++;
                        //System.out.println(ans);
                        break;
                    }
                }
            }
            System.out.println(ans);
        }
    }
}