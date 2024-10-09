import java.util.*;
import java.io.*;

public class 토네이도 {
	static int N, sum;
	static int[][] map;
	static int[] dr = { 0, 1, 0, -1 }; // 좌 하 우 상 으로 이동
	static int[] dc = { -1, 0, 1, 0 };
	static int[] sandRate = { 1, 1, 2, 2, 7, 7, 10, 10, 5, 0 }; 
	// 0,1번은 1(index : 0,1)/ 2,3번은 2(index : 2,3)/ 4,5번은 7(index : 4,5)/ 6,7번은 10(index : 6,7)/ 8번은 5(index : 8)/ 9번은 알파의 자리  
	static int[][] rangeDr = { 
			{ -1, 1, -2, 2, -1, 1, -1, 1, 0, 0 }, // 왼쪽 방향
			{ -1, -1, 0, 0, 0, 0, 1, 1, 2, 1 }, // 아래 방향
			{ -1, 1, -2, 2, -1, 1, -1, 1, 0, 0 }, // 오른쪽 방향
			{ 1, 1, 0, 0, 0, 0, -1, -1, -2, -1 } // 위 방향
	};

	static int[][] rangeDc = { 
			{ 1, 1, 0, 0, 0, 0, -1, -1, -2, -1 }, // 왼쪽 방향
			{ -1, 1, -2, 2, -1, 1, -1, 1, 0, 0 }, // 아래 방향
			{ -1, -1, 0, 0, 0, 0, 1, 1, 2, 1 }, // 오른쪽 방향
			{ -1, 1, -2, 2, -1, 1, -1, 1, 0, 0 } // 위 방향
	};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		sum = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0)
					sum += map[i][j];
			}
		}

		moveTornado();
		int count = sum - endSum();
		System.out.println(count);
	}

	static void moveTornado() { // 달팽이 이동 로직 구현
		int row = N / 2;
		int col = N / 2;
		int cnt = 0; // 횟수
		int maxCnt = 1; // 방향 전환용 
		int flag = 0; // maxCnt 업데이트용
		int dir = 0; // 방향

		while (true) {
			row += dr[dir];
			col += dc[dir];
			cnt++;
			if(!check(row,col)) return;
			spreadSand(row,col,dir);
			// 달팽이 이동 방식 : 왼 x 1 -> 아래 x 1 -> 오른 x 2 -> 위 x 2 -> 왼 x 3 -> 아래 x 3 -> ... 
			if (cnt == maxCnt) { // 반복횟수가 maxCnt와 같아지면 방향 전환
				cnt = 0;
				dir = (dir + 1) % 4;
      
				if (flag == 0) {
					flag = 1;
				}
        // 달팽이 이동 방식 : 왼 x 1 -> 아래 x 1 -> 오른 x 2 -> 위 x 2 -> 왼 x 3 -> 아래 x 3 -> ... 
				else if (flag == 1) { // 방향이 2번 바뀌면 최대반복횟수 1회 증가
					flag = 0;
					maxCnt++;
				}
			}
		}
	}

	static void spreadSand(int r, int c, int d) { // 퍼지는 모래 구현
		int sand = map[r][c]; // 기존 좌표의 모래 량 
		int sum = 0; // 퍼트린 모래의 총량
		if(sand > 0) {
			map[r][c] = 0;
			for (int i = 0; i < 10; i++) {
				int nr = r + rangeDr[d][i];
				int nc = c + rangeDc[d][i];
				int divSand = (sand * sandRate[i])/100;
				if(i == 9) { // 마지막 좌표는 알파의 자리
					divSand = sand - sum; // 알파 자리에 퍼트리고 남은 모래를 옮김
				}
				
				if(check(nr,nc)) {
					map[nr][nc] += divSand;
				}
				sum += divSand;
			}
		}
	}

	static int endSum() { // 최종 모래 양
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0)
					result += map[i][j];
			}
		}
		return result;
	}

	static boolean check(int r, int c) { // 격자 범위 안에 있는지 체크
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	static void printMap(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
