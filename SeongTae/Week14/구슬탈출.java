import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬탈출 {
	static int N, M;
	static int[][] map;

	static class Node {
		Ball blueBall; // blue ball 좌표
		Ball redBall;// red ball 좌표
		int cnt; // 몇 번째 인지

		public Node(Ball redBall, Ball blueBall, int cnt) {
			this.blueBall = blueBall;
			this.redBall = redBall;
			this.cnt = cnt;
		}

	}

	static class Ball {
		int r, c;

		public Ball(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static Ball blueBallStart;
	static Ball redBallStart;
	static boolean[][][][] visited; // max value 10*10*10*10
	static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][N][M];
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				if (tmp.charAt(j) == '#')
					map[i][j] = -1;
				else if (tmp.charAt(j) == '.')
					map[i][j] = 0;
				else if (tmp.charAt(j) == 'O')
					map[i][j] = 1;
				else if (tmp.charAt(j) == 'R') {
					map[i][j] = 0;
					redBallStart = new Ball(i, j);
				} else {
					map[i][j] = 0;
					blueBallStart = new Ball(i, j);
				}
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		ArrayDeque<Node> q = new ArrayDeque<>();
		visited[redBallStart.r][redBallStart.c][blueBallStart.r][blueBallStart.c] = true;
		q.offer(new Node(redBallStart, blueBallStart, 0));
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.cnt >= 10)
				break;
			for (int i = 0; i < 4; i++) { // 상 하 좌 우
				boolean redExit = false;
				boolean blueExit = false;
				
				Ball nextRedBall = new Ball(cur.redBall.r, cur.redBall.c);
				Ball nextBlueBall = new Ball(cur.blueBall.r, cur.blueBall.c);
				
				// 빨간 볼 부터
				while (map[nextRedBall.r + dr[i]][nextRedBall.c + dc[i]] != -1) {
					nextRedBall.r += dr[i];
					nextRedBall.c += dc[i];
					if (map[nextRedBall.r][nextRedBall.c] == 1) {
						redExit = true;
						break;
					}
				}
				// 파란 볼
				while (map[nextBlueBall.r + dr[i]][nextBlueBall.c + dc[i]] != -1) {
					nextBlueBall.r += dr[i];
					nextBlueBall.c += dc[i];
					if (map[nextBlueBall.r][nextBlueBall.c] == 1) {
						blueExit = true;
						break;
					}
				}

				if (blueExit)
					continue; // 둘다 일 경우는 그냥 넘기기
				else if (redExit && !blueExit)
					return 1;

				// 겹치는 경우 처리
				if (nextBlueBall.r == nextRedBall.r && nextBlueBall.c == nextRedBall.c) {
					if (i == 0) { // 위쪽으로 기울이는 경우
						if (cur.redBall.r < cur.blueBall.r) {
							nextBlueBall.r++;
						} else
							nextRedBall.r++;
					} else if (i == 1) { // 아래쪽으로 기울이는 경우
						if (cur.redBall.r < cur.blueBall.r) {
							nextRedBall.r--;
						} else
							nextBlueBall.r--;
					} else if (i == 2) { // 왼쪽으로 기울이는 경우
						if (cur.redBall.c < cur.blueBall.c) {
							nextBlueBall.c++;
						} else
							nextRedBall.c++;
					} else { // 오른쪽으로 기울이는 경우
						if (cur.redBall.c < cur.blueBall.c) {
							nextRedBall.c--;
						} else
							nextBlueBall.c--;
					}
				}
				if (!visited[nextRedBall.r][nextRedBall.c][nextBlueBall.r][nextBlueBall.c]) {
					visited[nextRedBall.r][nextRedBall.c][nextBlueBall.r][nextBlueBall.c] = true;
					q.offer(new Node(nextRedBall, nextBlueBall, cur.cnt + 1));
				}

			}
		}
		return 0;
	}
}
