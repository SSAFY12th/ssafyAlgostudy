import java.util.*;
import java.io.*;

public class Main {

	static StringTokenizer st;

	static int N;

	static int[][] map;

	// 좌 하 우 상
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	// 격자 밖으로 나간 모래의 총 량
	static int lostSand = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		tornado();

		System.out.println(lostSand);

	}

	// 달팽이 모양으로 토네이도 돌리기
	public static void tornado() {

		// 토네이도 시작점은 정중앙
		int r = N / 2;
		int c = N / 2;

		int direction = 0;

		// i = 달팽이 회전 마다 길이
		for (int i = 1; i < N; i++) {
			// 두번씩 반복 하더라
			for (int j = 0; j < 2; j++) {
				// 길이 만큼 토네이도 진행함
				for (int k = 0; k < i; k++) {
					// 뿌려서 흘린 모래량 더해줌
					lostSand += spread(r, c, direction);
					r += dx[direction];
					c += dy[direction];
				}
				// 한 방향 진행했으면 방향 바꿔준다
				direction = (direction + 1) % 4;
			}
		}

		// 마지막 행 N만큼
		for (int i = 0; i < N - 1; i++) {
			lostSand += spread(r, c, direction);
			r += dx[direction];
			c += dy[direction];
		}
	}

	// 손실된 모래양을 리턴함
	public static int spread(int r, int c, int direction) {

		// 현재 시점에서 잃어버린 모래양
		int totalSand = 0;

		// 현재 위치 모래양 초기화
		int currentSand = 0;

		// 현재 위치 날림
		map[r][c] = 0;

		switch (direction) {
		// 왼쪽으로 바람이 불었을 경우
		case 0:
			currentSand = map[r][c - 1];
			map[r][c - 1] = 0;
			// 위쪽 한칸 벗어나면
			if (r - 1 < 0) {
				// 버려진 모래양에 현재 위치 모래양의 7퍼센트를 추가한다.
				totalSand += (int) (currentSand * (0.07));
			} else {
				// 칸을 벗어나지 않았다면
				// 그 위치에 7퍼센트의 모래양을 추가한다.
				map[r - 1][c - 1] += (int) (currentSand * (0.07));
			}

			// 위쪽으로 두칸이 벗어났을 경우
			if (r - 2 < 0) {
				totalSand += (int) (currentSand * (0.02));
			} else {
				map[r - 2][c - 1] += (int) (currentSand * (0.02));
			}

			// 아래쪽으로 벗어나면
			if (r + 1 >= N) {
				totalSand += (int) (currentSand * (0.07));
			} else {
				map[r + 1][c - 1] += (int) (currentSand * (0.07));
			}

			if (r + 2 >= N) {
				totalSand += (int) (currentSand * (0.02));
			} else {
				map[r + 2][c - 1] += (int) (currentSand * (0.02));
			}

			if (c >= N || r - 1 < 0) {
				totalSand += (int) (currentSand * (0.01));
			} else {
				map[r - 1][c] += (int) (currentSand * (0.01));
			}

			if (c >= N || r + 1 >= N) {
				totalSand += (int) (currentSand * (0.01));
			} else {
				map[r + 1][c] += (int) (currentSand * (0.01));
			}

			if (c - 2 < 0 || r - 1 < 0) {
				totalSand += (int) (currentSand * (0.1));
			} else {
				map[r - 1][c - 2] += (int) (currentSand * (0.1));
			}

			if (c - 2 < 0 || r + 1 >= N) {
				totalSand += (int) (currentSand * (0.1));
			} else {
				map[r + 1][c - 2] += (int) (currentSand * (0.1));
			}

			if (c - 3 < 0) {
				totalSand += (int) (currentSand * (0.05));
			} else {
				map[r][c - 3] += (int) (currentSand * (0.05));
			}

			if (c - 2 > 0) {
				map[r][c - 2] += currentSand - totalSand;
			} else {
				totalSand += currentSand - totalSand;
			}

			break;

		// 아래로 바람이 불었을 경우
		case 1:
			currentSand = map[r + 1][c];
			map[r + 1][c] = 0;
			if (c + 1 >= N) {
				totalSand += (int) (currentSand * (0.07));
			} else {
				map[r + 1][c + 1] += (int) (currentSand * (0.07));
			}

			if (c + 2 >= N) {
				totalSand += (int) (currentSand * (0.02));
			} else {
				map[r + 1][c + 2] += (int) (currentSand * (0.02));
			}

			if (c - 1 < 0) {
				totalSand += (int) (currentSand * (0.07));
			} else {
				map[r + 1][c - 1] += (int) (currentSand * (0.07));
			}

			if (c - 2 < 0) {
				totalSand += (int) (currentSand * (0.02));
			} else {
				map[r + 1][c - 2] += (int) (currentSand * (0.02));
			}

			if (c - 1 < 0 || r < 0) {
				totalSand += (int) (currentSand * (0.01));
			} else {
				map[r][c - 1] += (int) (currentSand * (0.01));
			}

			if (c + 1 >= N || r < 0) {
				totalSand += (int) (currentSand * (0.01));
			} else {
				map[r][c + 1] += (int) (currentSand * (0.01));
			}

			if (c - 1 < 0 || r + 2 >= N) {
				totalSand += (int) (currentSand * (0.1));
			} else {
				map[r + 2][c - 1] += (int) (currentSand * (0.1));
			}

			if (c + 1 >= N || r + 2 >= N) {
				totalSand += (int) (currentSand * (0.1));
			} else {
				map[r + 2][c + 1] += (int) (currentSand * (0.1));
			}

			if (r + 3 >= N) {
				totalSand += (int) (currentSand * (0.05));
			} else {
				map[r + 3][c] += (int) (currentSand * (0.05));
			}

			if (r + 2 < N) {
				map[r + 2][c] += currentSand - totalSand;
			} else {
				totalSand += currentSand - totalSand;
			}
			break;
		// 오른쪽으로 바람이 불었을 경우
		case 2:
			currentSand = map[r][c + 1];
			map[r][c + 1] = 0;
			// 위쪽 한칸 벗어나면
			if (r - 1 < 0) {
				totalSand += (int) (currentSand * (0.07));
			} else {
				map[r - 1][c + 1] += (int) (currentSand * (0.07));
			}

			if (r - 2 < 0) {
				totalSand += (int) (currentSand * (0.02));
			} else {
				map[r - 2][c + 1] += (int) (currentSand * (0.02));
			}

			if (r + 1 >= N) {
				totalSand += (int) (currentSand * (0.07));
			} else {
				map[r + 1][c + 1] += (int) (currentSand * (0.07));
			}

			if (r + 2 >= N) {
				totalSand += (int) (currentSand * (0.02));
			} else {
				map[r + 2][c + 1] += (int) (currentSand * (0.02));
			}

			if (c + 2 >= N || r - 1 < 0) {
				totalSand += (int) (currentSand * (0.1));
			} else {
				map[r - 1][c + 2] += (int) (currentSand * (0.1));
			}

			if (c + 2 >= N || r + 1 >= N) {
				totalSand += (int) (currentSand * (0.1));
			} else {
				map[r + 1][c + 2] += (int) (currentSand * (0.1));
			}

			if (c < 0 || r - 1 < 0) {
				totalSand += (int) (currentSand * (0.01));
			} else {
				map[r - 1][c] += (int) (currentSand * (0.01));
			}

			if (c < 0 || r + 1 >= N) {
				totalSand += (int) (currentSand * (0.01));
			} else {
				map[r + 1][c] += (int) (currentSand * (0.01));
			}

			if (c + 3 >= N) {
				totalSand += (int) (currentSand * (0.05));
			} else {
				map[r][c + 3] += (int) (currentSand * (0.05));
			}

			if (c + 2 < N) {
				map[r][c + 2] += currentSand - totalSand;
			} else {
				totalSand += currentSand - totalSand;
			}

			break;
		// 위로 바람이 불었을 경우
		case 3:
			currentSand = map[r - 1][c];
			map[r - 1][c] = 0;
			if (c + 1 >= N) {
				totalSand += (int) (currentSand * (0.07));
			} else {
				map[r - 1][c + 1] += (int) (currentSand * (0.07));
			}

			if (c + 2 >= N) {
				totalSand += (int) (currentSand * (0.02));
			} else {
				map[r - 1][c + 2] += (int) (currentSand * (0.02));
			}

			if (c - 1 < 0) {
				totalSand += (int) (currentSand * (0.07));
			} else {
				map[r - 1][c - 1] += (int) (currentSand * (0.07));
			}

			if (c - 2 < 0) {
				totalSand += (int) (currentSand * (0.02));
			} else {
				map[r - 1][c - 2] += (int) (currentSand * (0.02));
			}

			if (c - 1 < 0 || r - 2 < 0) {
				totalSand += (int) (currentSand * (0.1));
			} else {
				map[r - 2][c - 1] += (int) (currentSand * (0.1));
			}

			if (c + 1 >= N || r - 2 < 0) {
				totalSand += (int) (currentSand * (0.1));
			} else {
				map[r - 2][c + 1] += (int) (currentSand * (0.1));
			}

			if (c - 1 < 0 || r >= N) {
				totalSand += (int) (currentSand * (0.01));
			} else {
				map[r][c - 1] += (int) (currentSand * (0.01));
			}

			if (c + 1 >= N || r >= N) {
				totalSand += (int) (currentSand * (0.01));
			} else {
				map[r][c + 1] += (int) (currentSand * (0.01));
			}

			if (r - 3 < 0) {
				totalSand += (int) (currentSand * (0.05));
			} else {
				map[r - 3][c] += (int) (currentSand * (0.05));
			}

			if (r - 2 > 0) {
				map[r - 2][c] += currentSand - totalSand;
			} else {
				totalSand += currentSand - totalSand;
			}
			break;
		default:
			break;
		}

		return totalSand;
	}

}

// 가운데 부터 달팽이 모양으로 그려 나가는 메서드
// 그 인덱스에서 모래 퍼져나가는 메서드 (모래가 맵밖으로 벗어난다면 카운팅 리턴)
// 개 어렵다 그냥 하기 싫다 그냥

// 달팽이 규칙성..
// 1 1 2 2 3 3 4 4 5 5 6 6 6 왜 마지막은 세번인데
