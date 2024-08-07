#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;
int N, M;
int day = 0;
vector <vector <int> > map;
int dy[4] = {0, 0, -1, 1};
int dx[4] = {-1, 1, 0, 0};

bool range (int y, int x) {
    return y >= 0 && x >= 0 && y < M && x < N;
}

void bfs (vector <pair <int, int> > tomato, vector <vector <int> >&map) {
    queue <pair<int, int> > q;
    for (int i = 0; i < tomato.size(); i++) {
        q.push(tomato[i]);
    }
    while (!q.empty()) {
        int y = q.front().first;
        int x = q.front().second;
        q.pop();
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (range(ny, nx) && map[ny][nx] == 0) {
                map[ny][nx] = map[y][x] + 1;
                q.push(make_pair(ny, nx));
            }

        }
    }
    
}

int main() {
    cin >> N >> M;
    vector <pair<int, int> > tomato;
    for (int i = 0; i < M; i++) {
        vector <int> temp (N);
        for (int j = 0; j < N; j++) {
            cin >> temp[j];
            if (temp[j] == 1) tomato.push_back(make_pair(i, j));
        }
        map.push_back(temp);
    }
    if (tomato.size() == N * M) {
        cout << "0" << endl;
        return 0;
    }
    bfs(tomato, map);
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            if (!map[i][j]) {
                cout << "-1" << endl;
                return 0;
            }
            day = max(day, map[i][j]);
        }
    }
    cout << day - 1;
}
