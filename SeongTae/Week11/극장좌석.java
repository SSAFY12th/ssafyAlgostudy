import java.util.*;
import java.io.*;

public class 극장좌석 {
	static int N, M;
	static int[] fixedSeat;
	static int[] fibo;
	static int[] eCnt = new int[41];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		fibo = new int[N + 1]; // 총 가능한 좌석 배치 가짓수는 피보나치 수열임

		fibo[0] = 1;
		fibo[1] = 1;

		if (N > 1) { // N이 2이상일 때만 피보나치 수열을 구성
			for (int i = 2; i <= N; i++) {
				fibo[i] = fibo[i - 1] + fibo[i - 2];
			}
		}

		int mul = 1;
		if (M != 0) { // 고정석의 개수가 0개일 경우 fixedSeat배열을 만들 필요 없음
			fixedSeat = new int[M];
			for (int i = 0; i < M; i++) {
				fixedSeat[i] = Integer.parseInt(br.readLine());
			}
			int k = 0;
			int i = 0;
			int count = 0;
			for (int j = 1; j <= N; j++) { // 좌석 1번부터 N번까지 중 고정석이 아닌 연속된 좌석의 수를 eCnt배열에 저장
				if (i < M) { // i가 고정석 개수보다 작고, 좌석번호 j가 고정석 번호보다 작으면 count를 1씩 더함
					if (j < fixedSeat[i]) {
						count++;
					} else if (j == fixedSeat[i]) { j가 고정석 번호와 일치하면 이전까지의 좌석 개수를 eCnt에 저장
						eCnt[k] = count;
						count = 0; // count는 0으로 초기화
						k++; //eCnt의 길이도 1 증가
						i++; // 다음 고정석으로 이동
					}
				} else if (j <= N) { // 만약 모든 고정석을 지나서 왔다면 N번 좌석으로 이동하는 동안 count++;
					count++;
					if (j == N) {
						eCnt[k] = count; // 마지막 구간의 좌석 개수를 eCnt에 저장
					}
				}
			}
			for (int j = 0; j <= k; j++) {
				mul *= fibo[eCnt[j]]; // mul에 eCnt값을 하나씩 빼서 피보나치 수열을 적용하여 곱함
			}
			System.out.println(mul); // 총 가짓수 출력
		} else {
			System.out.println(fibo[N]); // 만약 M이 0이라면 위의 과정 필요없이 fibo[N]을 출력
		}
	}
}
