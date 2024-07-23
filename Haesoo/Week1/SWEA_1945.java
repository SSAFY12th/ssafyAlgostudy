import java.util.Scanner;
import java.io.FileInputStream;

class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++) {
      StringBuilder sb = new StringBuilder(); 
			int a = 0, b = 0, c = 0, d = 0, e = 0;
      int N = sc.nextInt();
      while (N != 1) {
        if (N % 2 == 0) {
				N /= 2;
				a++;
			  }
			  else if (N % 3 == 0) {
				N /= 3;
				b++;
			  }
			  else if (N % 5 == 0) {
				N /= 5;
				c++;
			  }
			  else if (N % 7 == 0) {
				N /= 7;
				d++;
			  }
			  else if (N % 11 == 0) {
				N /= 11;
				e++;
			  }
      }
			sb.append("#");
      sb.append(test_case);
      sb.append(" " +  a + " " + b + " " + c + " " + d + " " + e);
      System.out.println(sb);
		}
	}
}
