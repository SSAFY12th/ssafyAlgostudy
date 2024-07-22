import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int min = 0;
			int sum = 0;
			int[] arr = new int[N];
			for(int i=0;i<N;i++){
				arr[i] = sc.nextInt();
			}

			for(int i=2;i<N-2;i++){
				if(i==2){
					min = Math.min(arr[i] - arr[i+1], arr[i] - arr[i+2]);
					if(min >0)
						sum+=min;
				}
				else if(i==N-3){
					min = Math.min(arr[i] - arr[i-1], arr[i] - arr[i-2]);
					if(min >0)
						sum+=min;
				}
				else{
					int num1 = Math.min(arr[i] - arr[i+1], arr[i] - arr[i+2]);
					int num2 = Math.min(arr[i] - arr[i-1], arr[i] - arr[i-2]);
					min = Math.min(num1,num2);
					if(min >0)
						sum+=min;
				}
			}
			System.out.println("#"+test_case+" "+sum);
    }
	}
}
