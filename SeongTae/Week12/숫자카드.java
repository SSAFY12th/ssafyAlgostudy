import java.util.*;
import java.io.*;
public class Main {
	static int N,M;
	static int[] nums;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		nums = new int[N];
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken()) ;
		}
		
		Arrays.sort(nums); // 이분탐색을 위한 정렬
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int search = Integer.parseInt(st.nextToken());
			if(binarySearch(nums, search)) {
				sb.append("1 ");
			}
			else {
				sb.append("0 ");
			}
		}
		System.out.println(sb);
	}
	private static boolean binarySearch(int[] nums, int search) {
		int right = N-1;
		int left = 0;
		int mid = (left + right)/2;
		int target = search;
		while(right > left) {
			if(nums[mid] >= target) { // mid값이 target과 같거나 크면 right를 mid로 옮김
				right = mid; // right는 항상 target범위 안에 존재
			} else if(nums[mid] < target) { //target보다 mid값이 작으면 left를 mid+1로 옮김
				left = mid + 1;
			}
			mid = (left + right)/2; // mid index 갱신
		}
		if(target == nums[left]) return true; // right와 left가 겹치는 순간 target이 지정됨
		
		return false; // 만약 left와 right가 겹쳤는데도 없으면 flase리턴
	}
}
