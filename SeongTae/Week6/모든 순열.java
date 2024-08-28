import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int[] nums;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 N = Integer.parseInt(br.readLine());
		 nums = new int[N];
		 visited = new boolean[N];
		 Perm(0);
		 

//		 for(int i=0;i<N;i++) {
//			 nums[i] = i+1;
//		 }
//		 do {
//			 for(int i=0;i<N;i++) {
//				 System.out.print(nums[i]+" ");
//			 }
//			 System.out.println();
//		 }while(NPerm(nums));
		 
	}
	static void Perm(int cnt) {
		if(cnt == N) {
			for(int i=0;i<N;i++) {
				System.out.print(nums[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(visited[i]) continue;
			nums[cnt] = i+1;
			visited[i] = true;
			Perm(cnt+1);
			visited[i] = false;
		}
	}
	
	static boolean NPerm(int[] p) {
		int len = p.length;
		int i = len-1;
		int j = len-1;
		int k = len-1;
		
		while(i>0 && p[i-1]>=p[i]) i--;
		if(i==0) return false;
		
		while(p[i-1]>=p[j]) j--;
		
		swap(p,i-1,j);
		
		while(i<k) {
			swap(p,i++,k--);
		}
		return true;	
	}
	static void swap(int[] p, int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}
}
