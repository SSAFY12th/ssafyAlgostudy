import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1253 {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	
		int count = 0;
		Arrays.sort(arr); // 이분탐색을 위한 정렬
		for(int i=0;i<N;i++) {
			int target = arr[i]; // 타겟 값 설정
			int left = 0; // 가장 왼쪽
			int right = arr.length-1; // 가장 오른쪽
			while(left<right) { // 왼쪽 포인터와 오른쪽 포인터가 겹치지 않는 동안 반복
				if(i == left) { // 자기 자신을 제외한 나머지 원소를 사용
					left++;  // 왼쪽 포인터를 한칸 오른쪽으로 옮겨줌
					continue;
					}
				if(i == right) { // 자기 자신을 제외한 나머지 원소를 사용
					right--; // 오른쪽 포인터를 한칸 왼쪽으로 옮겨줌
					continue;
				}
				int sum = arr[left] + arr[right]; // 두 원소의 합		
				if(sum == target) { // 만약 합이 타겟과 같으면 count+1하고 break
					count++;
					break;
				}
				else if(sum < target) { // 합이 타겟보다 작으면 왼쪽 포인터 한칸 오른쪽으로 이동
					left++;
				}
				else { // 합이 타겟보다 크면 오른쪽 포인터 한칸 왼쪽으로 이동
					right--;
				}
			}
		}
		System.out.println(count);
	}
}
