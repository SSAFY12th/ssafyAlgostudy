import java.util.*;
import java.io.*;

public class Main {
	static int[] pattern;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		pattern = new int[N + 1];
		visited = new boolean[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			pattern[i] = Integer.parseInt(st.nextToken());
		}
		
		List<Integer> nums = new ArrayList<>();
		
		visited[0] = true;
		for(int current = 1; current <= N; current++) {
			int cnt = 0;
			while(!visited[current]) {
				visited[current] = true;
				current = pattern[current];
				cnt++;
			}
			if(cnt != 0) nums.add(cnt);
		}
		
		int result = 1;
		for(int i : nums) {
			result = lcm(result, i);
		}
		
		System.out.println(result);
	}
	
	static int gcd(int a, int b) {
		while(b != 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
	
	static int lcm(int a, int b) {
		return a / gcd(a, b) * b;
	}
}
