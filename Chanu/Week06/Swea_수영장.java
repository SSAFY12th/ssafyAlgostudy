	
import java.io.*;
import java.util.*;
 
public class Solution {
    static int[] month = new int[13];
    static int oneDay;
    static int oneMonth;
    static int threeMonth;
    static int oneYear;
    static int min;
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
 
        for (int test_case = 1; test_case <=T; test_case++) {
             
            min = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
 
            oneDay = Integer.parseInt(st.nextToken());
            oneMonth = Integer.parseInt(st.nextToken());
            threeMonth = Integer.parseInt(st.nextToken());
            oneYear = Integer.parseInt(st.nextToken());
             
            st = new StringTokenizer(br.readLine());
            for (int i=1; i<=12; i++) {
                month[i] = Integer.parseInt(st.nextToken());
            }
             
            backTrack(1,0);
            System.out.println("#" + test_case +" " +min);
           
        }
    }
 
    static void backTrack(int depth, int sum) {
 
        if (depth >= 13) {
            if (sum > oneYear) {
                sum = oneYear;
            }
            min = Math.min(sum,min);
            return;
        }
        if (month[depth] != 0) {
            backTrack(depth + 1, sum + month[depth] * oneDay);
            backTrack(depth + 1,sum + oneMonth);
            backTrack(depth + 3, sum + threeMonth);
        }else {
            backTrack(depth + 1, sum);
        }
    }
}
