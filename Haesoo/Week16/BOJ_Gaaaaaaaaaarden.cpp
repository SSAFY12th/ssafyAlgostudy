#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

int g[50][50] = { 0, };
//bool visited[50][50] = { 0, };
int N, M, R, G;
int dy[4] = { -1, 1, 0, 0 };
int dx[4] = { 0, 0, -1, 1 };
struct Info {
	int y;
	int x;
	int color;
};

bool cmp(int a, int b) {
	return a > b;
}

bool range(int ny, int nx) {
	return ny >= 0 && nx >= 0 && ny < N && nx < M;
}

void copy(int(&org)[50][50], int(&temp)[50][50]) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			temp[i][j] = org[i][j];
		}
	}
}
queue <Info> q;
void spread(int(&gg)[50][50], int& curcnt, int (&visited)[50][50]) {
	int depth = 1;
	while (!q.empty()) {
		int qlen = q.size();
		for (int i = 0; i < qlen; i++) {
			int cy = q.front().y;
			int cx = q.front().x;
			int curColor = q.front().color;
            if (gg[cy][cx] == -1) continue;
			if (curColor == 3) {
				if (visited[cy][cx] == 2) {
					curcnt++;
					visited[cy][cx] = -1;
					gg[cy][cx] = -1;
				} else {
                    visited[cy][cx] = 1;
					gg[cy][cx] = 3;
                }
			}
			else {
				if (visited[cy][cx] == 1) {
					curcnt++;
					visited[cy][cx] = -1;
					gg[cy][cx] = -1;
				} else {
                    visited[cy][cx] = 2;
					gg[cy][cx] = 4;	
                }
			}
			q.pop();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] > 0) {
					for (int k = 0; k < 4; k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						if (range(ny, nx)) {
							if (visited[ny][nx] == 0) {
								if (gg[ny][nx] == 1 || gg[ny][nx] == 2) q.push({ ny, nx, gg[i][j] });
							}
						}
					}
					visited[i][j] = -1;
				}
			}
		}
		depth++;
	}
}

int main() {
	cin >> N >> M >> R >> G;
	vector <pair <int, int>> possible;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> g[i][j];
			if (g[i][j] == 2) possible.push_back({ i, j });
		}
	}
	vector <bool> visit1(possible.size()); // 2인 땅의 총 개수
	
	fill(visit1.begin(), visit1.begin() + R, true);
	
	int cnt = 0;
	int maxflower = 0;
	do {
		
		int temp[50][50] = { 0, };
		vector<pair <int, int>> redCoord;
		vector <pair <int, int>> temppos;
		temppos = possible;
		copy(g, temp);
		vector <int> tempv;
		for (int i = 0; i < visit1.size(); i++) {
			if (visit1[i]) {
				redCoord.push_back(temppos[i]);
				tempv.push_back(i);
			}
		}
		sort(tempv.begin(), tempv.end(), cmp);
		for (int i = 0; i < tempv.size(); i++) {
			temppos.erase(temppos.begin() + tempv[i]);
		}
		vector <bool> visit2(temppos.size()); // 거기서 R 개만큼 뽑고 남은 개수
		fill(visit2.begin(), visit2.begin() + G, true);
        
		do {
			vector <pair <int, int>> greenCoord;
			copy(g, temp);
			q = queue <Info>();
			int visited[50][50] = { 0, };
			for (int i = 0; i < redCoord.size(); i++) {
				int cy = redCoord[i].first;
				int cx = redCoord[i].second;
				temp[cy][cx] = 3;
				q.push({ cy, cx, 3 });
			}

			for (int i = 0; i < visit2.size(); i++) {
				
				if (visit2[i]) {
					greenCoord.push_back(temppos[i]);
					int cy = temppos[i].first;
					int cx = temppos[i].second;
					temp[cy][cx] = 4;
					q.push({ cy, cx, 4 });
				}
			}
			int curcnt = 0;
			spread(temp, curcnt, visited);
			maxflower = max(curcnt, maxflower);
		} while (prev_permutation(visit2.begin(), visit2.end()));	
	} while (prev_permutation(visit1.begin(), visit1.end()));
	cout << maxflower;
}
