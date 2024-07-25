import java.util.*;

public class Solution {

    static int n;
    static int[] arr = {2, 3, 5, 7, 11};
    static int[] answer;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            answer = new int[5];
            n = sc.nextInt();

            for(int i = 0; i < arr.length; i++) {
                while(n % arr[i] == 0) {
                    answer[i]++;
                    n /= arr[i];
                }
            }

            System.out.print("#"+test_case+" ");
            for(int n : answer) {
                System.out.print(n+" ");
            }
            System.out.println();
        }
    }
}
