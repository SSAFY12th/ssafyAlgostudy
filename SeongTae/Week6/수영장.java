import java.util.*;
import java.io.*;
 
public class Solution {
    static int T,min;
    static int[] months;
    static int one_day, one_month, three_month, one_year;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T=Integer.parseInt(br.readLine());
        StringTokenizer st;
         
        for(int test_case = 1; test_case <= T; test_case++)
        {   
            months = new int[12];
            min = Integer.MAX_VALUE;
             
            st = new StringTokenizer(br.readLine());
            one_day = Integer.parseInt(st.nextToken());
            one_month = Integer.parseInt(st.nextToken());
            three_month = Integer.parseInt(st.nextToken());
            one_year = Integer.parseInt(st.nextToken());
             
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<12;i++) {
                months[i] = Integer.parseInt(st.nextToken());
            }
             
            dfs(0,0);
            System.out.println("#"+test_case+" "+min);
        }
    }
     
    public static void dfs(int cnt, int sum) {
        if(sum>=min) {
            return;
        }
        if(cnt == 12) {
            if(sum>one_year) {
                sum = one_year;
            }
            min = Math.min(min, sum);
            return;
        }
         
        for(int i=cnt;i<12;i++) {
            if(months[i] !=0){
                if((i+2 < 12) && three_month <= one_day*(months[i]+months[i+1]+months[i+2])) {
                    dfs(cnt+3,sum+three_month);
                }
                if(one_day*months[i]>one_month){
                    sum+=one_month;
                }else {
                    sum += months[i] * one_day;     
                }
            }
            dfs(cnt+1,sum);
        }
    }   
}
