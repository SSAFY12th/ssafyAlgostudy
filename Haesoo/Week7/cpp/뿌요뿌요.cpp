#include <iostream>
#include <vector>
#include <cstring>
#include <cmath>
#include <stack>
#include <algorithm>
using namespace std;
char field[12][6];
int dy[4] = { -1, 1, 0, 0};
int dx[4] = { 0, 0, -1, 1 };
bool visited[12][6];
int chaincnt = 0;
bool range(int ny, int nx) {
	return ny >= 0 && nx >= 0 && ny < 12 && nx < 6;
}

void count(int y, int x, int& colorcnt, vector <pair <int, int>> &coords) {
	visited[y][x] = true;
	//cout << "dfs 좌표: " << y << " " << x << endl << "개수: " << colorcnt << endl;;
	coords.push_back(make_pair(y, x));
	for (int i = 0; i < 4; i++) {
		int ny = y + dy[i];
		int nx = x + dx[i];
		if (range(ny, nx)) {
			if (!visited[ny][nx] && field[ny][nx] == field[y][x]) {
				colorcnt = colorcnt + 1;
				count(ny, nx, colorcnt, coords);
			}
		}
	}
}

void bomb(vector <pair <int, int>> coords) {
	for (int i = 0; i < coords.size(); i++) {
		int y = coords[i].first;
		int x = coords[i].second;
		field[y][x] = '.';
	}
}

void down() {
	for (int i = 0; i < 6; i++) {
		stack <char> s;
		//cout << i << ": " << endl;
		for (int j = 0; j < 12; j++) {
			if (field[j][i] != '.') {
				s.push(field[j][i]);
				field[j][i] = '.';
			}
		}

			for (int j = 11; j >= 0; j--) {
				if (!s.empty()) {
					char curchar = s.top();
					s.pop();
					field[j][i] = curchar;
				}

			}
		}
	}

int main() {
	memset(field, '.', sizeof(field));
	for (int i = 0; i < 12; i++) {
		for (int j = 0; j < 6; j++) {
			char a;
			cin >> a;
			if (a != '.') field[i][j] = a;
		}
	}
	while (1) {
		memset(visited, false, sizeof(visited));
		int retcnt = 0;
		for (int i = 11; i >= 0; i--) {
			for (int j = 5; j >= 0; j--) {
				int colorcnt = 1;
				vector <pair <int, int>> coords;
				
				if (field[i][j] != '.' && !visited[i][j]) {
					count(i, j, colorcnt, coords);
					//cout << i << ", " << j << ": " << field[i][j] << ", " << colorcnt << endl;
				}
				if (colorcnt >= 4) {
					for (int i = 0; i < coords.size(); i++) {
						//cout << i << "번 째 좌표: " << coords[i].first << coords[i].second << endl;
					}
					bomb(coords);
				}
				retcnt = max(retcnt, colorcnt);
			}
		}

		if (retcnt < 4) break;
		down();

		chaincnt++;
		//if (retcnt++ < 4) break;
	}
	cout << chaincnt;
}
/*
		cout << "한 cycle 끝난 뒤: " << endl;
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				cout << field[i][j] << " ";
			}
			cout << endl;
		}*/
