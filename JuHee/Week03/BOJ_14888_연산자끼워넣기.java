import java.util.Scanner;

public class Main {
	static int n;
	static int[] arr;
	static int[] operation = new int[4];
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		
		for(int i=0; i<4; i++) {
			operation[i] = sc.nextInt();
		}
		
		op(1, arr[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void op(int dept, int arrValue) {
		if(dept == n) {
			max = Math.max(max, arrValue);
			min = Math.min(min, arrValue);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(operation[i] > 0 ) {
				operation[i]--;
				
				switch(i) {
				case 0:
					op(dept + 1, arrValue + arr[dept]);
					break;
				case 1:
					op(dept + 1, arrValue - arr[dept]);
					break;
				case 2:
					op(dept + 1, arrValue * arr[dept]);
					break;
				case 3:
					op(dept + 1, arrValue / arr[dept]);
					break;
				}
				operation[i]++;
			}
		}
	}
}
