import java.util.*;
import java.io.*;

public class Main {
	static char[][] map;
	static int N, H, W; // N : 최대 움직임 횟수
	static int[] dr = { -1, 1, 0, 0 }; // W 상, S 하, A 좌, D 우
	static int[] dc = { 0, 0, -1, 1 };
	static String answer = "NO";
	static Point Dao, Dizney;
	static Direct[] directions;
	static StringBuilder sb;
	static String result;
	static Map<String, Integer> aToi;

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Direct {
		int first;
		int second;

		public Direct(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		map = new char[H][W];

		for (int i = 0; i < H; i++) {
			String s = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = s.charAt(j);

				if (map[i][j] == 'D') {
					Dao = new Point(i, j);
				}

				if (map[i][j] == 'Z') {
					Dizney = new Point(i, j);
				}
			}
		}

		N = Integer.parseInt(br.readLine());
		directions = new Direct[N];
		aToi = new HashMap<>();

		aToi.put("W", 0);
		aToi.put("S", 1);
		aToi.put("A", 2);
		aToi.put("D", 3);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String first = st.nextToken();
			String second = st.nextToken();

			int fd = aToi.get(first);
			int sd = aToi.get(second);
			directions[i] = new Direct(fd, sd);
		}

		sb = new StringBuilder();

		move(0, Dao.r, Dao.c);

		System.out.println(answer);
		if (answer == "YES") {
			System.out.println(result);
		}
	}

	public static void move(int cnt, int r, int c) {
		if (answer.equals("YES"))
			return;

		if (r == Dizney.r && c == Dizney.c) {
			answer = "YES";
			result = sb.toString();
			return;
		}

		if (check(r, c) || cnt == N) {
			return;
		}

		if(directions[cnt].first == 0) sb.append('W');
		if(directions[cnt].first == 1) sb.append('S');
		if(directions[cnt].first == 2) sb.append('A');
		if(directions[cnt].first == 3) sb.append('D');
		
		move(cnt + 1, r + dr[directions[cnt].first] , c + dc[directions[cnt].first]);
		sb.setLength(sb.length() - 1);
		
		if(directions[cnt].second == 0) sb.append('W');
		if(directions[cnt].second == 1) sb.append('S');
		if(directions[cnt].second == 2) sb.append('A');
		if(directions[cnt].second == 3) sb.append('D');
		
		move(cnt + 1, r + dr[directions[cnt].second], c + dc[directions[cnt].second]);
		sb.setLength(sb.length() - 1);
	}

	static boolean check(int r, int c) {
		return r < 0 || r >= H || c < 0 || c >= W || map[r][c] == '@';
	}
}
