import java.util.*;
import java.io.*;
public class BOJ15683 {
    static int N, M, minCount = 65;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] board;// = new int[8][8];
    static boolean[][] visited;// = new boolean[8][8];
    static List <pair> cctvs = new ArrayList<>();
    static int[][] second = {{0, 1}, {2, 3}};
    static int[][] third = { {0, 2}, {1, 2}, {1, 3}, {0, 3} };
    static int[][] fourth = { {0, 1, 2}, {0, 1, 3}, {1, 2, 3}, {0, 2, 3} };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int a = Integer.parseInt(st.nextToken());
                if (a != 0) {
                    board[i][j] = a;
                    visited[i][j] = true;
                    if (a != 6) cctvs.add(new pair(i, j));
                }
            }
        } // 입력 끝
        dfs(0, visited);
        System.out.println(minCount);
    }
    static void dfs(int depth, boolean[][] visited) {
        int curCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) curCount++;
            }
        }
        if (curCount == 0) {
            minCount = 0;
            return;
        }
        if (depth == cctvs.size()) {
            minCount = Math.min(minCount, curCount);
            return;
        }
        int cy = cctvs.get(depth).y;
        int cx = cctvs.get(depth).x;
        int curval = board[cy][cx];
        boolean[][] temp = new boolean[N][M];
        if (curval == 1) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < M; k++) {
                        temp[j][k] = visited[j][k];
                    }
                }
                cctv(i, cy, cx, temp);
                dfs(depth + 1, temp);
            }
        }
        else if (curval == 2) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < M; k++) {
                        temp[j][k] = visited[j][k];
                    }
                }
                cctv(second[i][0], cy, cx, temp);
                cctv(second[i][1], cy, cx, temp);
                dfs(depth + 1, temp);
            }
        }
        else if (curval == 3) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < M; k++) {
                        temp[j][k] = visited[j][k];
                    }
                }
                cctv(third[i][0], cy, cx, temp);
                cctv(third[i][1], cy, cx, temp);
                dfs(depth + 1, temp);
            }
        }
        else if (curval == 4) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < M; k++) {
                        temp[j][k] = visited[j][k];
                    }
                }
                cctv(fourth[i][0], cy, cx, temp);
                cctv(fourth[i][1], cy, cx, temp);
                cctv(fourth[i][2], cy, cx, temp);
                dfs(depth + 1, temp);
            }
        }
        else if (curval == 5) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    temp[j][k] = visited[j][k];
                }
            }
            for (int i = 0; i < 4; i++) {
                cctv(i, cy, cx, temp);
            }
            dfs(depth + 1, temp);
        }
    }
    static void cctv (int dir, int y, int x, boolean[][] visited) {
        int cy = y;
        int cx = x;
        while (true) {
            int ny = cy + dy[dir];
            int nx = cx + dx[dir];
            if (range(ny, nx)) visited[ny][nx] = true;
            else return;
            if (board[ny][nx] == 6) return;
            cy = ny;
            cx = nx;
        }
    }
    static boolean range (int ny, int nx) {
        return ny >= 0 && nx >= 0 && ny < N && nx < M;
    }
    static class pair {
        int y;
        int x;
        pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}


// if문으로 값이 0이 아닐 때만 받는게 빠른지? (300, 34352), (304, 30956)
// -> 여기서는 어차피 if문으로 처리해주는 로직이 있어서 큰 비교는 안될듯
// 미리 8x8로 초기화해놓고 하는게 빠른지? (312, 31256) vs (300, 34352) 메모리는 덜쓰지만 시간은 느리다
// dfs 돌 때마다 0 개수 세서 바로 끝내는게 빠른지? (300, 34352) vs (344, 29120) 0이 없을 경우 바로 끝내는 조건을 추가해주면 빨리 끝나긴 함

/* 벡터 버리자.. 배열이 진짜 훨씬 빠르다 (C++)
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
int N, M;
int dy[4] = { -1, 1, 0, 0 };
int dx[4] = { 0, 0, -1, 1 };
int mincount = 10000000;
int board[8][8];
bool visited[8][8];
vector <pair <int, int>> cctvs;
int third[4][2] = { {0, 2}, {1, 2}, {1, 3}, {0, 3} };
int fourth[4][3] = { {0, 1, 2}, {0, 1, 3}, {1, 2, 3}, {0, 2, 3} };
bool range(int ny, int nx) {
	return ny >= 0 && nx >= 0 && ny < N && nx < M;
}

void cctv(int dir, int y, int x, bool(&visited)[8][8]) {
	int cy = y;
	int cx = x;
	while (1) {
		int ny = cy + dy[dir];
		int nx = cx + dx[dir];
		if (range(ny, nx)) visited[ny][nx] = true;
		else return;
		if (board[ny][nx] == 6) return;
		cy = ny;
		cx = nx;
	}
}

void dfs(int depth, bool (&visited)[8][8]) {
	int curcount = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (visited[i][j] == 0) curcount++;
		}
	}
	if (curcount == 0) {
	mincount = 0;
		return;
	}
	if (depth == cctvs.size()) {
		mincount = min(mincount, curcount);
		return;
	}

	int cury = cctvs[depth].first;
	int curx = cctvs[depth].second;
	int curval = board[cury][curx];
	bool temp[8][8];

	if (curval == 1) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++) {
					temp[j][k] = visited[j][k];
				}
			}
			cctv(i, cury, curx, temp);
			dfs(depth + 1, temp);
		}
	}
	else if (curval == 2) {
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				for (int j = 0; j < 8; j++) {
					for (int k = 0; k < 8; k++) {
						temp[j][k] = visited[j][k];
					}
				}
				cctv(i, cury, curx, temp);
				cctv(i + 1, cury, curx, temp);
				dfs(depth + 1, temp);
			}
			else {
				for (int j = 0; j < 8; j++) {
					for (int k = 0; k < 8; k++) {
						temp[j][k] = visited[j][k];
					}
				}
				cctv(i + 1, cury, curx, temp);
				cctv(i + 2, cury, curx, temp);
				dfs(depth + 1, temp);
			}
		}
	}
	else if (curval == 3) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++) {
					temp[j][k] = visited[j][k];
				}
			}
			cctv(third[i][0], cury, curx, temp);
			cctv(third[i][1], cury, curx, temp);
			dfs(depth + 1, temp);
		}
	}
	else if (curval == 4) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++) {
					temp[j][k] = visited[j][k];
				}
			}
			cctv(fourth[i][0], cury, curx, temp);
			cctv(fourth[i][1], cury, curx, temp);
			cctv(fourth[i][2], cury, curx, temp);
			dfs(depth + 1, temp);
		}
	}
	else if (curval == 5) {
		for (int j = 0; j < 8; j++) {
			for (int k = 0; k < 8; k++) {
				temp[j][k] = visited[j][k];
			}
		}
		for (int i = 0; i < 4; i++) {
			cctv(i, cury, curx, temp);
		}
		dfs(depth + 1, temp);
	}
}
int main() {
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> board[i][j];
			if (board[i][j] >= 1 && board[i][j] <= 5) {
				cctvs.push_back(make_pair(i, j));
				visited[i][j] = true;
			}
			if (board[i][j] == 6) visited[i][j] = true;
		}
	}
	dfs(0, visited);
	cout << mincount << endl;
}
*/
