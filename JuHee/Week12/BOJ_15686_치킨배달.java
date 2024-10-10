package algo;

import java.io.*;
import java.util.*;

class Main {

	static int N, M, result = Integer.MAX_VALUE;
	static int[][] map;
	static int[] temp, temp2;
	static List<int[]> house = new ArrayList<>();
	static List<int[]> chicken = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) { // 집이라면
					house.add(new int[] { i, j });
				} else if (map[i][j] == 2) { // 치킨집이라면
					chicken.add(new int[] { i, j });
				}
			}
		}

		// M개와 같지 않다면 M개 선택하기(조합)
		int[] combSet = new int[M];

		Comb(0, 0, combSet);

//		for (int i = 0; i < chicken.size(); i++) {
//			temp = chicken.get(i);
//			int chickenX = temp[0] + 1;
//			int chickenY = temp[1] + 1;
//			result = Calulate(chickenX, chickenY);
//		}
		System.out.println(result);
	}

	public static void Comb(int start, int index, int[] combSet) {
		if (index == M) {

			result = Math.min(Calulate(combSet), result);
			return;
		}
		for (int i = start; i < chicken.size(); i++) {
			combSet[index] = i;
			Comb(i + 1, index + 1, combSet);
		}
	}

	public static int Calulate(int[] combSet) {
		int distance = 0;

		for (int i = 0; i < house.size(); i++) {
			int minDistance = Integer.MAX_VALUE;
			int[] houseXY = house.get(i);
			for (int j = 0; j < M; j++) {
				int[] chickenXY = chicken.get(combSet[j]);
				int d = Math.abs(houseXY[0] - chickenXY[0]) + Math.abs(houseXY[1] - chickenXY[1]);

				if (minDistance > d) {
					minDistance = d;
				}

//				minDistance = Math.min(Math.abs(houseXY[0] - chickenXY[0]) + Math.abs(houseXY[1] - chickenXY[1]), minDistance);
			}

			distance += minDistance;
		}

		return distance;
	}
}
