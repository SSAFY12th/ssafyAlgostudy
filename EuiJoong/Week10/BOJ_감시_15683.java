import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class CCTV {
		int r;
		int c;
		int number;

		public CCTV(int r, int c, int number) {
			this.r = r;
			this.c = c;
			this.number = number;
		}
	}

	static int N, M;
	static int[][] map;
	static int[][] copyMap;
	static List<CCTV> cctvList;
	static int[] number;
	// 0:우 1:하 2:좌 3:상
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int ans;

	public static void main(String[] args) throws IOException {

		input();
		number = new int[cctvList.size()];
		ans = Integer.MAX_VALUE;
		permutation(0);
		System.out.println(ans);

	}

	//입력
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copyMap = new int[N][M];
		cctvList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = copyMap[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 5) cctvList.add(new CCTV(i, j, map[i][j]));
			}
		}
	}

	private static void permutation(int depth) {

		if (depth == cctvList.size()) {
			//printArr();
			//리스트에 담긴 cctv들 회전 방향을 정했다!

			//bfs 탐색할 방향을 정해주고 탐색 하면서 감시 지역 정해주기
			copy();
			for (int i = 0; i < cctvList.size(); i++) {
				setDirection(cctvList.get(i), number[i]);
			}
			ans = Math.min(ans, cntFree());
			return;
		}

		for (int i = 0; i < 4; i++) {
			number[depth] = i;
			permutation(depth + 1);
		}
	}

	private static void copy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = copyMap[i][j];
			}
		}
	}

	private static void setDirection(CCTV cctv, int dir) {
		int cctvNum = cctv.number;

		switch (cctvNum) {
			case 1:
				//감시
				monitoring(cctv, dir);
				break;
			case 2:
				//감시
				if (dir == 0 || dir == 2) {
					monitoring(cctv, 0);
					monitoring(cctv, 2);
				}else if (dir == 1 || dir == 3) {
					monitoring(cctv, 1);
					monitoring(cctv, 3);
				}
				break;
			case 3:
				//감시
				if (dir == 0) {
					monitoring(cctv, 0);
					monitoring(cctv, 3);
				}else if (dir == 1) {
					monitoring(cctv, 1);
					monitoring(cctv, 0);
				}else if (dir == 2) {
					monitoring(cctv, 2);
					monitoring(cctv, 1);
				}else if (dir == 3) {
					monitoring(cctv, 3);
					monitoring(cctv, 2);
				}
				break;
			case 4:
				//감시
				if (dir == 0) {
					monitoring(cctv, 0);
					monitoring(cctv, 2);
					monitoring(cctv, 3);
				}else if (dir == 1) {
					monitoring(cctv, 1);
					monitoring(cctv, 0);
					monitoring(cctv, 3);
				}else if (dir == 2) {
					monitoring(cctv, 2);
					monitoring(cctv, 1);
					monitoring(cctv, 0);
				}else if (dir == 3) {
					monitoring(cctv, 3);
					monitoring(cctv, 2);
					monitoring(cctv, 1);
				}
				break;
			case 5:
				//감시
				for (int i = 0; i < 4; i++) {
					monitoring(cctv, i);
				}
				break;
		}
	}

	private static void monitoring(CCTV cctv, int dir) {
		Queue<CCTV> q = new LinkedList<>();
		q.offer(cctv);

		while (!q.isEmpty()) {
			CCTV cctv1 = q.poll();
			int r = cctv1.r;
			int c = cctv1.c;

			int nr = r + dr[dir];
			int nc = c + dc[dir];
			// 범위를 벗어나거나 벽을 만나면 끝.
			if (0 > nr || nr >= N || 0 > nc || nc >= M || map[nr][nc] == 6) break;
			//빈칸이라면 감시!
			if (map[nr][nc] == 0) {
				map[nr][nc] = -1;
				q.offer(new CCTV(nr, nc, map[nr][nc]));
			}
			// 다른 cctv가 있거나 이미 감시된 칸이면 그냥 통과!
			else {
				q.offer(new CCTV(nr, nc, map[nr][nc]));
			}
		}
	}

	private static int cntFree() {
		int res = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					res++;
				}
			}
		}
		return res;
	}




	private static void printArr() {
		for (int i = 0; i < cctvList.size(); i++) System.out.print(number[i] + " ");
		System.out.println();
	}
}