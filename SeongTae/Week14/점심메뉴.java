import java.util.*;
import java.io.*;

public class Main {
	static int N, Q, K;
	static int[] a, b;
	static int v, w, x, y;
	static int count = 0;
	static Menu[] menus;

	static class Menu {
		public Menu(int spicy, int sweet) {
			super();
			this.spicy = spicy;
			this.sweet = sweet;
		}

		int spicy;
		int sweet;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		menus = new Menu[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int spicy = Integer.parseInt(st.nextToken());
			int sweet = Integer.parseInt(st.nextToken());

			menus[i] = new Menu(spicy, sweet);
		}
    // 매운 맛 기준으로 정렬
		Arrays.sort(menus, (a, b) -> a.spicy - b.spicy);

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			// 매운 맛 기준안에서 단 맛 검사
			count = countValidMenus(v, w, x, y);
			
			sb.append(count).append("\n");
			count = 0;
		}

		System.out.println(sb);
	}

	private static int countValidMenus(int minSpicy, int maxSpicy, int minSweet, int maxSweet) {
		int cnt = 0;
		int left = lowerBound(minSpicy); // 매운 맛 최솟값 검사
		int right = upperBound(maxSpicy); // 매운 맛 최댓값 검사
		
		for (int i = left; i < right; i++) {
			if(menus[i].sweet >= minSweet && menus[i].sweet <= maxSweet) {
				cnt++;
			}
		}
		return cnt;
	}

	private static int upperBound(int maxSpicy) {
		int left = 0;
		int right = N;
		
		while(left < right) {
			int mid = (left + right)/2;
			if(menus[mid].spicy > maxSpicy) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	private static int lowerBound(int minSpicy) {
		int left = 0;
		int right = N;
		
		while(left < right) {
			int mid = (left + right)/2;
			if(menus[mid].spicy >= minSpicy) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

}
