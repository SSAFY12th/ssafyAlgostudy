import java.util.*;
import java.io.*;

public class Solution {
	static int D, W, K, T, min;
	static int[][] map =new int[13][20];
	static int[][] temp =new int[13][20];
	static int[] A = new int[20];
	static int[] B = new int[20];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); // row
			W = Integer.parseInt(st.nextToken()); // col
			K = Integer.parseInt(st.nextToken()); // 연속된 값의 개수
			
			min = K;
			
			Arrays.fill(A, 0);
			Arrays.fill(B, 1);

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
				temp[i] = map[i]; // 맵 복사
			}

			if (K <= 1 || check()) {
				min = 0;
			} else {
				solve(0, 0);
			}
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}

	private static void solve(int idx, int cnt) {
		if (cnt >= min) { // 최소값보다 cnt가 크면 가지치기
			return;
		}

		if (check()) {
			min = Math.min(min, cnt); // 최소값 갱신
			return;
		}

		for (int i = idx; i < D; i++) {
			temp[i] = A; // 약품 A를 선택한 경우
			solve(i+1, cnt+1);
			
			temp[i] = B; // 약품 B를 선택한 경우
			solve(i+1, cnt+1);
			
			temp[i] = map[i]; // 현상태로 복원
		}
	}

	private static boolean check() {
		for (int i = 0; i < W; i++) {
			int cnt = 1;
			int cur = -1;
			for (int j = 0; j < D; j++) {
				if (temp[j][i] != cur) { // 현재 원소와 이전 원소 비교 -> 다르면 현재 원소를 이전 원소로 대입
					cur = temp[j][i];
					cnt = 1;
				} else { // 현재 원소와 이전 원소가 같으면 cnt++
					cnt++; 
					if (cnt >= K) // K와 같아지면 더 셀 필요 X
						break;
				}
			}
			if (cnt < K) // 하나의 열이라도 cnt가 K보다 작으면 false
				return false;
		}
		return true;
	}
}
