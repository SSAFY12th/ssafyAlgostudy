import java.io.*;
import java.util.*;
public class Main {
	static int N, min, small, big;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		min = Integer.MAX_VALUE;
		int left = 0;
		int right = N - 1;
		while(left < right) {
			int sum = arr[left] + arr[right];
			int absolute = Math.abs(sum);
			if(min > absolute) {
				min = absolute;
				small = arr[left];
				big = arr[right];
			} else if (sum < 0){
				left++;
			} else if(sum > 0) {
				right--;
			} else break;
		}
		System.out.println(small + " " + big);
	}
}
