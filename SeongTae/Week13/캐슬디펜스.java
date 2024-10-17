import java.util.*;
import java.io.*;

public class Main {
	static int T, N, M, D, sum; // N : 행 , M : 열 , D: 사거리
	static int[][] map, temp;
	static int[] nums;
	static List<Point> attacker = new ArrayList<>();
	static List<Point> enermys;

	static class Point implements Comparable<Point> {
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public Point(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}

		int r;
		int c;
		int d;

		@Override
		public int compareTo(Point o) {
			int num = this.d - o.d;
			if (num == 0)
				return this.c - o.c;
			return num;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		sum = 0;
		map = new int[N + 1][M];
		temp = new int[N + 1][M];
		nums = new int[3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		DFS(0, 0);
		System.out.println(sum);
	}

	private static void DFS(int cnt, int start) {
		if (cnt == 3) { // 궁수 위치를 선정하기 위해 DFS 수행
			for (int i = 0; i < N; i++) {
				temp[i] = map[i].clone(); // 맵도 복사해줘야 함
			}
			simulation(temp);
			return;
		}

		for (int i = start; i < M; i++) {
			nums[cnt] = i;
			DFS(cnt + 1, i + 1);
		}
	}

	static void simulation(int[][] temp) {
		for (int i = 0; i < nums.length; i++) {
			attacker.add(new Point(N, nums[i])); // 궁수 배치
		}

		int kill = 0;
		while (true) {
			if (checkMap(temp)) {
				sum = Math.max(sum, kill); // 맵에 적이 없으면 sum값을 kill로 비교하여 업데이트
				break;
			}
			kill += attack(temp);
			moveEnermy(temp);
		}
		attacker.clear(); // 반복이 끝나면 궁수 위치 초기화
	}

	private static int attack(int[][] temp) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		enermys = new ArrayList<>();
		searchEnermy(temp);

		int count = 0;
		for (int i = 0; i < attacker.size(); i++) { // 궁수의 위치와 적들의 위치를 기준으로 거리 구함
			int ar = attacker.get(i).r;
			int ac = attacker.get(i).c;
			for (int j = 0; j < enermys.size(); j++) {
				int er = enermys.get(j).r;
				int ec = enermys.get(j).c;
				int dist = Math.abs(ar - er) + Math.abs(ac - ec);
				if (dist <= D) { // D보다 작거나 같은 적들만 PQ에 담아서 정렬
					pq.offer(new Point(er, ec, dist));
				}
			}
			if (!pq.isEmpty()) { 
				Point p = pq.poll(); // 가장 앞에 있는 적을 뽑아서 타켓으로 선정
				if(temp[p.r][p.c] == 1) { // 있으면 쏘고 count + 1
					count++;
					temp[p.r][p.c] = 0;
				}
				pq.clear();
			}
		}
		enermys.clear(); 
		return count;
	}

	private static void moveEnermy(int[][] temp) {	//적들의 위치를 한칸씩 내림
		for (int i = 0; i < M; i++) {
			for (int j = N; j > 0; j--) {
				temp[j][i] = temp[j - 1][i];
			}
		}

		for (int i = 0; i < M; i++) {
			temp[0][i] = 0;
			if (temp[N][i] == 1) {
				temp[N][i] = 0;
			}
		}
		
	}

	private static void searchEnermy(int[][] temp) { // 적 위치를 리스트에 저장
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (temp[i][j] != 0) {
					enermys.add(new Point(i, j));
				}
			}
		}
	}

	static boolean checkMap(int[][] temp) { // 맵에 적이 있는지 확인
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (temp[i][j] != 0)
					return false;
			}
		}
		return true;
	}
}
