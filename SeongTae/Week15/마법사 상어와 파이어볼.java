import java.util.*;
import java.io.*;

public class Main {
	static int N, M, K;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 0 ~ 7번 위치
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static List<FireBall> FBs;
	static int[][] map;
	static List<Point> positions = new ArrayList<>();

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class FireBall {
		public FireBall(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		int r;
		int c;
		int m;
		int s;
		int d;

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 격자 크기
		M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
		K = Integer.parseInt(st.nextToken()); // 이동 횟수
		
		map = new int[N][N];
		FBs = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			FBs.add(new FireBall(r, c, m, s, d));
		}
		for (int i = 0; i < K; i++) {
			move();
		}

		System.out.println(sum());
	}

	private static void move() {
		for(int i = 0; i < N; i++) {
	        Arrays.fill(map[i], 0);
	    }
		for (FireBall fb : FBs) {
			int s = fb.s % N;
	        fb.r = ((fb.r + dr[fb.d] * s) + N * 1001) % N;
	        fb.c = ((fb.c + dc[fb.d] * s) + N * 1001) % N;
	        map[fb.r][fb.c]++;
		}
		check();
	}

	private static void check() {
		positions.clear();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] > 1) {
					positions.add(new Point(i, j));
				}		
			}
		}
		divideFireBall();

		for (int i = FBs.size() - 1; i >= 0; i--) {
			if (FBs.get(i).m == 0)
				FBs.remove(i);
		}
	}

	private static void divideFireBall() {
		for (Point cur: positions) {
			int r = cur.r;
			int c = cur.c;
			int sameCnt = 0;
			int mSum = 0;
			int sSum = 0;
			int checkOddEven = 0;
			for (FireBall fb : FBs) {
				if (fb.r == r && fb.c == c) {
					sameCnt++;
					mSum += fb.m;
					sSum += fb.s;
					checkOddEven += fb.d % 2;
					fb.m = 0;
				}
			}

			if (mSum == 0)
				continue;
			boolean Allsame = (checkOddEven == 0 || checkOddEven == sameCnt);
			for (int i = 0; i < 4; i++) {
				int nd = Allsame ? 2 * i : 2 * i + 1;
				int nr = r;
				int nc = c;
				int ns = sSum / sameCnt;
				int nm = mSum / 5;
				FBs.add(new FireBall(nr, nc, nm, ns, nd));
			}
		}
	}

	private static int sum() {
		if (FBs.isEmpty())
			return 0;
		int mSum = 0;
		for (FireBall fb : FBs) {
			mSum += fb.m;
		}
		return mSum;
	}
}
