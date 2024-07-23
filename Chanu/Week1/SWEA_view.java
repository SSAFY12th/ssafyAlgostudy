import java.util.*;
import java.io.*;
import java.lang.Math;


class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
            
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            
            int n = Integer.parseInt(st.nextToken());
            
            int[] arr = new int[n];
            int count = 0;
            
            int max = 0;
            int middle =Integer.MIN_VALUE;
            
            st = new StringTokenizer(br.readLine()," ");
            for (int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
               
     
            for (int i=2; i < n-2; i++) {
  
                max = arr[i];
                middle = Integer.MIN_VALUE;
                
                for (int j = i - 2; j <=i+2; j++) {
          
                    if (j == i) {
                        continue;
                    }
                    
                    middle = Math.max(arr[j], middle);
                   
                 
                }
                
                if (max > middle) {
                    count += (max - middle);
                }
            }
            
            System.out.println("#"+test_case+" "+count);
        

		}
	}
}
