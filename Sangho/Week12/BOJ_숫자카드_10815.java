import java.util.*;
import java.io.*;

public class Main {
	
	static StringTokenizer st;
	
	static int N,M;
	
	static HashSet<Integer> numSet;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		numSet = new HashSet<>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			numSet.add(num);
		}
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(numSet.contains(num)) {
				sb.append("1 ");
			} else {
				sb.append("0 ");
			}
		}
		
		System.out.println(sb);
		
		
	}

}

// 해시셋에 담아둠
// 있는지 검증, 답 출력하기
