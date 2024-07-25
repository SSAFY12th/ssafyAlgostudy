
import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
	
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int n = Integer.parseInt(br.readLine());
            int a =0;
            int b =0;
            int c =0;
            int d =0;
            int e =0;
            
            while (n > 1) {
                
                if (n % 2 == 0) {
                    n /= 2;
                    a++;
                }else if (n % 3 == 0) {
                    b++;
                    n /= 3;
                }else if (n % 5 == 0) {
                    c++;
                    n /= 5;
                }else if (n % 7 == 0) {
                    d++;
                    n /= 7;
                }else if (n % 11 == 0) {
                    e++;
                    n /= 11;
                }
                
            }
            
            System.out.println("#"+test_case+" "+a+" "+b+" "+c+" "+d+" "+e);
            

		}
	}
}
