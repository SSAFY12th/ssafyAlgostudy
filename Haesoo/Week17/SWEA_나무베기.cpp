#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <cstring>
using namespace std;
int N, K, endy, endx, minMove = 100000;
int m[10][10] = { 0, };
bool visited[10][10] = { false, };
int dy[4] = { -1, 0, 1, 0 };
int dx[4] = { 0, 1, 0, -1 };
int subdir[4][4] = { {0, 1, 2, 1}, {1, 0, 1, 2}, {2, 1, 0, 1}, {1, 2, 1, 0} };
bool range(int ny, int nx) {
	return ny >= 0 && nx >= 0 && ny < N && nx < N;
}

void dfs(int y, int x, int cutCnt, int moveCnt, int curdir) {
	if (y == endy && x == endx) {
		minMove = min(minMove, moveCnt);
		return;
	}
	for (int i = 0; i < 4; i++) {
		int ny = y + dy[i];
		int nx = x + dx[i];
		if (range(ny, nx) && !visited[ny][nx]) {
			if (m[ny][nx] == 1) {
				if (cutCnt >= 1) {
					visited[ny][nx] = true;
					moveCnt += subdir[curdir][i];
					cutCnt--;
					moveCnt++;
					dfs(ny, nx, cutCnt, moveCnt, i);
					moveCnt -= subdir[curdir][i];
					moveCnt--;
					cutCnt++;
					visited[ny][nx] = false;
				}
			}
			else {
				visited[ny][nx] = true;
				moveCnt += subdir[curdir][i];
				moveCnt++;
				dfs(ny, nx, cutCnt, moveCnt, i);
				moveCnt--;
				moveCnt -= subdir[curdir][i];
				visited[ny][nx] = false;
			}
		}
	}
}

int main() {
	cin.tie(0); cout.tie(0); ios::sync_with_stdio(false);
	int T;
	cin >> T;

	for (int t = 1; t <= T; t++) {
		minMove = 100000;
		memset(visited, 0, sizeof(visited));
		memset(m, 0, sizeof(m));
		int sy, sx;
		cin >> N >> K;
		for (int i = 0; i < N; i++) {
			string s;
			cin >> s;
			for (int j = 0; j < N; j++) {
				if (s[j] == 'T') m[i][j] = 1;
				else if (s[j] == 'X') {
					sy = i, sx = j;
					visited[i][j] = true;
				}
				else if (s[j] == 'Y') endy = i, endx = j;
			}
		}

		dfs(sy, sx, K, 0, 0);
		if (minMove == 100000) minMove = -1;
		cout << "#" << t << " " << minMove << endl;
	}

}
