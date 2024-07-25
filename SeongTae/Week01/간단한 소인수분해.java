import java.util.Scanner;

class Solution
{

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int a=0, b=0, c=0, d=0, e=0;
            while(N!=1){
            	if(N%2==0){
                	a++;
                	N = N/2;
            	}
            	if(N%3==0){
                	b++;
                	N = N/3;
            	}
            	if(N%5==0){
                	c++;
                	N = N/5;
            	}
            	if(N%7==0){
                	d++;
                	N = N/7;
           		}
            	if(N%11==0){
                	e++;
                	N = N/11;
            	}
            }
            System.out.println("#"+test_case+" "+a+" "+b+" "+c+" "+d+" "+e);

        }
	}
}
