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
	// 디버깅 완료, 문제 없음 잘 돌아감
	public static void tornado() {

		// 토네이도 시작점은 정중앙
		int r = N / 2;
		int c = N / 2;

		// 초기 방향은 왼쪽이다.
		int direction = 0;

		// i = 달팽이 회전 마다 길이가 변화한다.
		// 최종 진행 길이는 N - 1 ex) 5x5 맵 일경우 방향 바꾼 후 4만큼 진행하는것이 최종.
		for (int i = 1; i < N; i++) {
			// 각 길이마다 2번씩 반복된다
			for (int j = 0; j < 2; j++) {
				// 현재 길이 만큼 토네이도가 진행한다.
				for (int k = 0; k < i; k++) {
					// 모래가 흩뿌려진 뒤 격자 밖으로 이동된 모래 양을 누적한다
					// r , c 에서 direction 방향으로 토네이도 진행됨
					lostSand += spread(r, c, direction);
							
					// 현재 방향 대로 진행한다
					r += dx[direction];
					c += dy[direction];
					
				}
				// 한 방향 진행했으면 방향 바꿔준다 좌 하 우 상
				direction = (direction + 1) % 4;
			}
		}

		// 마지막 행 N만큼 한 줄 진행해준다. ex) 1 1 2 2 3 3 4 4 5 5 "5"에 해당하는 마지막 진행
		for (int i = 0; i < N - 1; i++) {
			lostSand += spread(r, c, direction);
			r += dx[direction];
			c += dy[direction];
		}
	}
	
	public static void snapShot() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

	// 손실된 모래양을 리턴함 토네이도의 위치와 방향을 입력받아 동작함
	public static int spread(int r, int c, int direction) {

		// 현재 토네이도가 격자 밖으로 날린, 모래양 변수(리턴값)
		int totalSand = 0;

		// 격자 밖으로 나갔든, 다른 블록에 쌓이든, 퍼진 총량을 담는 변수임
		int subSand = 0;

		// 현재 위치 모래양 초기화, 토네이도로 영향을 받는 위치에 있는 모래의 양 (흩뿌릴 양 계산 위해 초기화)
		int currentSand = 0;

		switch (direction) {
		// 왼쪽으로 바람이 불었을 경우
		case 0:
			// 토네이도의 왼쪽 인덱스가 영향을 받는 인덱스임, 영향 받는 인덱스에 담긴 모래양을 저장해둠
			currentSand = map[r][c - 1];
			// 영향 받는 인덱스의 모래양은 다 날아가니 0으로 초기화 해둠
			map[r][c - 1] = 0;

			// 위쪽 한칸 벗어나면
			if (r - 1 < 0) {
				// 전체 모래양의 7퍼센트를 격자 밖으로 나간 모래양에 추가한다. 타입 캐스팅으로 소숫점을 버린다.
				totalSand += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			} else {
				// 위쪽으로 한칸을 벗어나지 않았다면
				// 그 인덱스에 모래를 추가해준다.
				map[r - 1][c - 1] += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			}

			// 위쪽으로 두칸이 벗어났을 경우
			if (r - 2 < 0) {
				// 이하 동문
				totalSand += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			} else {
				map[r - 2][c - 1] += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			}

			// 아래쪽으로 벗어나면
			if (r + 1 >= N) {
				totalSand += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			} else {
				map[r + 1][c - 1] += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			}

			if (r + 2 >= N) {
				totalSand += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			} else {
				map[r + 2][c - 1] += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			}

			if (r - 1 < 0 || c >= N) {
				totalSand += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			} else {
				map[r - 1][c] += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			}

			if (r + 1 >= N || c >= N) {
				totalSand += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			} else {
				map[r + 1][c] += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			}

			if (c - 2 < 0 || r - 1 < 0) {
				totalSand += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			} else {
				map[r - 1][c - 2] += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			}

			if (c - 2 < 0 || r + 1 >= N) {
				totalSand += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			} else {
				map[r + 1][c - 2] += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			}

			if (c - 3 < 0) {
				totalSand += (int) (currentSand * (0.05));
				subSand += (int) (currentSand * (0.05));
			} else {
				map[r][c - 3] += (int) (currentSand * (0.05));
				subSand += (int) (currentSand * (0.05));
			}

			// 알파가 맵을 벗어나지 않는다면
			if (c - 2 >= 0) {
				// 기존 모래양에서 흩날려진 총 모래양을 뺀 값을 맵에 쌓음
				map[r][c - 2] += currentSand - subSand;
			} else {
				// 맵밖으로 나가면 기저 모래양에서 흩날린 모래양을 뺀 값을, 격자 밖으로 벗어난 모래양에 더해줌
				totalSand += currentSand - subSand;
			}
			
			
			break;

		// 아래로 바람이 불었을 경우
		case 1:
			currentSand = map[r + 1][c];
			map[r + 1][c] = 0;
			if (c + 1 >= N) {
				totalSand += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			} else {
				map[r + 1][c + 1] += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			}

			if (c + 2 >= N) {
				totalSand += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			} else {
				map[r + 1][c + 2] += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			}

			if (c - 1 < 0) {
				totalSand += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			} else {
				map[r + 1][c - 1] += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			}

			if (c - 2 < 0) {
				totalSand += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			} else {
				map[r + 1][c - 2] += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			}

			if (r < 0 || c - 1 < 0) {
				totalSand += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			} else {
				map[r][c - 1] += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			}

			if (r < 0 || c + 1 >= N) {
				totalSand += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			} else {
				map[r][c + 1] += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			}

			if (r + 2 >= N || c - 1 < 0) {
				totalSand += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			} else {
				map[r + 2][c - 1] += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			}

			if (r + 2 >= N || c + 1 >= N) {
				totalSand += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			} else {
				map[r + 2][c + 1] += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			}

			if (r + 3 >= N) {
				totalSand += (int) (currentSand * (0.05));
				subSand += (int) (currentSand * (0.05));
			} else {
				map[r + 3][c] += (int) (currentSand * (0.05));
				subSand += (int) (currentSand * (0.05));
			}

			if (r + 2 < N) {
				map[r + 2][c] += currentSand - subSand;
			} else {
				totalSand += currentSand - subSand;
			}
			break;
		// 오른쪽으로 바람이 불었을 경우
		case 2:
			currentSand = map[r][c + 1];
			map[r][c + 1] = 0;
			// 위쪽 한칸 벗어나면
			if (r - 1 < 0) {
				totalSand += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			} else {
				map[r - 1][c + 1] += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			}

			if (r - 2 < 0) {
				totalSand += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			} else {
				map[r - 2][c + 1] += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			}

			if (r + 1 >= N) {
				totalSand += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			} else {
				map[r + 1][c + 1] += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			}

			if (r + 2 >= N) {
				totalSand += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			} else {
				map[r + 2][c + 1] += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			}

			if (r - 1 < 0 || c + 2 >= N) {
				totalSand += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			} else {
				map[r - 1][c + 2] += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			}

			if (r + 1 >= N || c + 2 >= N) {
				totalSand += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			} else {
				map[r + 1][c + 2] += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			}

			if (r - 1 < 0 || c < 0) {
				totalSand += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			} else {
				map[r - 1][c] += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			}

			if (r + 1 >= N || c < 0) {
				totalSand += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			} else {
				map[r + 1][c] += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			}

			if (c + 3 >= N) {
				totalSand += (int) (currentSand * (0.05));
				subSand += (int) (currentSand * (0.05));
			} else {
				map[r][c + 3] += (int) (currentSand * (0.05));
				subSand += (int) (currentSand * (0.05));
			}

			if (c + 2 < N) {
				map[r][c + 2] += currentSand - subSand;
			} else {
				totalSand += currentSand - subSand;
			}

			break;
		// 위로 바람이 불었을 경우
		case 3:
			currentSand = map[r - 1][c];
			map[r - 1][c] = 0;
			if (c + 1 >= N) {
				totalSand += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			} else {
				map[r - 1][c + 1] += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			}

			if (c + 2 >= N) {
				totalSand += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			} else {
				map[r - 1][c + 2] += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			}

			if (c - 1 < 0) {
				totalSand += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			} else {
				map[r - 1][c - 1] += (int) (currentSand * (0.07));
				subSand += (int) (currentSand * (0.07));
			}

			if (c - 2 < 0) {
				totalSand += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			} else {
				map[r - 1][c - 2] += (int) (currentSand * (0.02));
				subSand += (int) (currentSand * (0.02));
			}

			if (r - 2 < 0 || c - 1 < 0) {
				totalSand += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			} else {
				map[r - 2][c - 1] += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			}

			if (r - 2 < 0 || c + 1 >= N) {
				totalSand += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			} else {
				map[r - 2][c + 1] += (int) (currentSand * (0.1));
				subSand += (int) (currentSand * (0.1));
			}

			if (r >= N || c - 1 < 0) {
				totalSand += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			} else {
				map[r][c - 1] += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			}

			if (r >= N || c + 1 >= N) {
				totalSand += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			} else {
				map[r][c + 1] += (int) (currentSand * (0.01));
				subSand += (int) (currentSand * (0.01));
			}

			if (r - 3 < 0) {
				totalSand += (int) (currentSand * (0.05));
				subSand += (int) (currentSand * (0.05));
			} else {
				map[r - 3][c] += (int) (currentSand * (0.05));
				subSand += (int) (currentSand * (0.05));
			}

			if (r - 2 >= 0) {
				map[r - 2][c] += currentSand - subSand;
			} else {
				totalSand += currentSand - subSand;
			}
			break;
		default:
			break;
		}

		// 방향에 따라 계산된 손실 모래양을 리턴
		return totalSand;
	}

}
