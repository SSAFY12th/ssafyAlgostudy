import java.util.Scanner;
import java.io.FileInputStream;

class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = 10;
		for(int test_case = 1; test_case <= T; test_case++) {	
            int sol = 0;
            int N = sc.nextInt();
			      int[] maxHeight = new int[N];
            for (int i = 0; i < N; i++) maxHeight[i] = sc.nextInt();
            for (int i = 2; i < N - 2; i++) {
            	int left = Math.max(maxHeight[i - 1], maxHeight[i - 2]);
                int right = Math.max(maxHeight[i + 1], maxHeight[i + 2]);
                if (maxHeight[i] > left && maxHeight[i] > right) {
				            int temp = Math.max(left, right);
				            sol += maxHeight[i] - temp;
			        }
            }
			System.out.println("#" + test_case + " " + sol);
		}
	}
}
