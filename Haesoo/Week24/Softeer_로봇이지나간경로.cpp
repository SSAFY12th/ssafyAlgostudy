#include <iostream>
#include <string>
using namespace std;

int H, W;
int map[26][26];
bool visited[26][26] = {false, };
int dy[4] = {0, -1, 0, 1};
int dx[4] = {1, 0 ,-1, 0};
int sy, sx;

string dir = ">^<v";
int curDir;

bool range (int ny, int nx) {
    return nx >= 0 && ny >= 0 && ny < H && nx < W;
}
void start() {
	for (int i = 0; i < H; i++) {
		for (int j = 0; j < W; j++) {
			int num = 0;
			for (int k = 0; k < 4; k++) {
				int ny = i + dy[k];
				int nx = j + dx[k];
				if (range(ny, nx)) {
					if (map[i][j] && map[ny][nx]) num++;
				}
			}
            
			if (num == 1) { // 현 좌표에서 이동 가능한 #이 1개일 때
				sy = i;
				sx = j;
				for (int k = 0; k < 4; k++) {
					int ny = sy + dy[k];
					int nx = sx + dx[k];
					if (range(ny, nx)) {
						if (map[i][j] && map[ny][nx]) curDir = k;
					} // 그 연결된 # 방향으로 curDir 설정
				}
				return;
			}
		}
	}
}

void dfs(int y, int x) {
	visited[y][x] = true; // 현 좌표 방문 처리
	x += dx[curDir];
	y += dy[curDir];
	cout << "A";
	visited[y][x] = true; // 두 칸 이동 후 방문 처리

	for (int k = 0; k < 4; k++) { 
		int ny = y + dy[k];
		int nx = x + dx[k];
		if (range(ny, nx)) {
			if (map[ny][nx] && !visited[ny][nx]) {
				int move = curDir - k;
				if (move == -1 || move == 3) cout << "L";
				else if (move == 1 || move == -3) cout << "R";
				curDir = k;
				dfs(ny, nx);
			} // 현 좌표에서 인접한 #에 대해 좌 or 우로 방향 전환 필요한지 확인 후 재귀
		}
	}
}

int main(int argc, char** argv) {
	cin >> H >> W;
	string input;
    
	for (int i = 0; i < H; i++) {
		cin >> input;
		for (int j = 0; j < W; j++) {
			if (input[j] == '#') map[i][j] = 1;
			else if (input[j] == '.') map[i][j] = 0;
		}
	}

	start();
	cout << sy + 1 << " " << sx + 1 << "\n";
	cout << dir[curDir] << "\n";
	visited[sy][sx] = true;
	dfs(sy + dy[curDir], sx + dx[curDir]);
	return 0;
}
