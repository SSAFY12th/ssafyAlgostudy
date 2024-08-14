package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea1244 {
	private static int T;
	private static StringTokenizer st;
	private static int[] cards;
	private static int swapCnt;
	private static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());//testcase의 수
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			String card = st.nextToken();
			swapCnt = Integer.parseInt(st.nextToken());
			cards = new int[card.length()];
			for (int i = 0; i < card.length(); i++) {
				cards[i] = card.charAt(i) - '0';
			}
			
			
			// 최대 교환 횟수는 카드의 개수를 넘어가지 않게
			swapCnt = Math.min(swapCnt, cards.length);
			ans = 0;
			exchange(0, 0);
			System.out.println("#" + tc + " " + ans);
			
		}
		
	}
	private static void exchange(int idx, int cnt) {
		if (cnt == swapCnt) {
			String str = "";
			for (int i = 0; i < cards.length; i++) {
				str += cards[i];
			}
			
			ans = Math.max(ans, Integer.parseInt(str));
			return;
		}
		
		// 조합 뽑기
		for (int i = idx; i < cards.length; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				swap(i, j);
				exchange(i, cnt + 1);
				swap(i, j);
			}
		}
	}
	private static void swap(int i, int j) {
		int temp = cards[i];
		cards[i] = cards[j];
		cards[j] = temp;
	}
}
