import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution
{
    static int n;
    static int first, second, third;
    static int[] arr = new int[1000];
    static int result;

    public static void main(String args[]) throws Exception
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int max = 0;

        for(int test_case = 1; test_case <= 10; test_case++)
        {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            result = 0;

            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 2; i < n-2; i++) {
                if(arr[i] > arr[i-1] && arr[i] > arr[i-2] && arr[i] > arr[i+1] && arr[i] > arr[i+2]) {
                    max = Math.max(arr[i-1], arr[i-2]);
                    max = Math.max(max, arr[i+1]);
                    max = Math.max(max, arr[i+2]);

                    result += arr[i]-max;
                }
            } 

            System.out.println("#"+test_case+" "+result);

        }
    }
}
