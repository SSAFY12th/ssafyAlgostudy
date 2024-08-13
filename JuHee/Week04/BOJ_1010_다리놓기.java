import java.util.*;

public class Main {
	static int [][] arr = new int[30][30];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		
		for(int i=0; i<num; i++) {
			int b = sc.nextInt();
			int a = sc.nextInt();
			System.out.println(combination(a,b));
		}
	}
	
	static int combination(int a, int b) {
		if(arr[a][b]>0) {
			return arr[a][b];
		}
		if(a==b||b==0) {
			return arr[a][b]=1;
		}
		return arr[a][b]=combination(a-1,b-1)+combination(a-1,b);
	}
}
