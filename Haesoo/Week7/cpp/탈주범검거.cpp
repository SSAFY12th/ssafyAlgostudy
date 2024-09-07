#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
int N, M, R, C, L;
int dy[4] = { 0, 0, -1, 1 };
int dx[4] = { 1, -1, 0, 0 };
int cnt = 0;
queue <pair <int, int> > q;
// 우, 좌, 상, 하
bool range(int ny, int nx) {
    return ny >= 0 && nx >= 0 && ny < N && nx < M;
}
 
void up(int ny, int nx, vector <vector <int> > map, vector <vector <bool> >&visit) { // 1,2,5,6  // 바로 위의 cell 값 확인
    int val = map[ny][nx];
    if (val == 1 || val == 2 || val == 5 || val == 6) {
        q.push(make_pair(ny, nx));
        visit[ny][nx] = true;
    }
    else return;
}
void down(int ny, int nx, vector <vector <int> > map, vector <vector <bool> >&visit) { // 1,2,4,7
    int val = map[ny][nx];
    if (val == 1 || val == 2 || val == 4 || val == 7) {
        q.push(make_pair(ny, nx));
        visit[ny][nx] = true;
    }
    else return;
}
 
void left(int ny, int nx, vector <vector <int> > map, vector <vector <bool> >&visit) { // 1,3,4,5
    int val = map[ny][nx];
    if (val == 1 || val == 3 || val == 4 || val == 5) {
        q.push(make_pair(ny, nx));
        visit[ny][nx] = true;
    }
    else return;
}
 
void right(int ny, int nx, vector <vector <int> > map, vector <vector <bool> >&visit) { // 1,3,6,7
    int val = map[ny][nx];
    if (val == 1 || val == 3 || val == 6 || val == 7) {
        q.push(make_pair(ny, nx));
        visit[ny][nx] = true;
    }
    else return;
}
 
 
void bfs(pair<int, int> start, vector <vector <bool> >&visit, vector <vector <int> >map) {
    int hour = 0;
    q.push(start);
    while (!q.empty()) {
        int qlen = q.size();
        cnt += qlen;
        for (int i = 0; i < qlen; i++) {
            int y = q.front().first;
            int x = q.front().second;
            visit[y][x] = true;
            q.pop();
            if (map[y][x] == 1) { // 상하좌우
                for (int j = 0; j < 4; j++) {
                    int ny = y + dy[j];
                    int nx = x + dx[j];
                    if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) {
                        if (j == 0) right(ny, nx, map, visit);
                        else if (j == 1) left(ny, nx, map, visit);
                        else if (j == 2) up(ny, nx, map, visit);
                        else if (j == 3) down(ny, nx, map, visit);
 
                    }
                }
            }
            else if (map[y][x] == 2) {
                for (int j = 2; j < 4; j++) {
                    int ny = y + dy[j];
                    int nx = x + dx[j];
                    if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) {
                        if (j == 2) up(ny, nx, map, visit);
                        else if (j == 3) down(ny, nx, map, visit);
                    }
                }
            }
            else if (map[y][x] == 3) {
                for (int j = 0; j < 2; j++) {
                    int ny = y + dy[j];
                    int nx = x + dx[j];
                    if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) {
                        if (j == 0) right(ny, nx, map, visit);
                        else if (j == 1) left(ny, nx, map, visit);                   
                    }
                }
            }
            else if (map[y][x] == 4) {
                int ny = y + dy[0];
                int nx = x + dx[0];
                if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) {
                    right(ny, nx, map, visit);
                }
                ny = y + dy[2];
                nx = x + dx[2];
                if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) {
                    up(ny, nx, map, visit);
                }
 
            }
            else if (map[y][x] == 5) {
                int ny = y + dy[0];
                int nx = x + dx[0];
                if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) {
                    right(ny, nx, map, visit);
                }
                ny = y + dy[3];
                nx = x + dx[3];
                if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) {
                    down(ny, nx, map, visit);
                }
 
            }
            else if (map[y][x] == 6) {
                int ny = y + dy[3];
                int nx = x + dx[3];
                if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) {
                    down(ny, nx, map, visit);
                }
                ny = y + dy[1];
                nx = x + dx[1];
                if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) {
                    left(ny, nx, map, visit);
                }
 
            }
            else if (map[y][x] == 7) {
                int ny = y + dy[1];
                int nx = x + dx[1];
                if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) {
                    left(ny, nx, map, visit);
                }
                ny = y + dy[2];
                nx = x + dx[2];
                if (range(ny, nx) && !visit[ny][nx] && map[ny][nx] != 0) {
                    up(ny, nx, map, visit);
                }
            }
        }
        hour++;
        if (hour == L) {
            return;
        }
    }
 
}
int main() {
    int T;
    cin >> T;
    for (int t = 1; t <= T; t++) {
        cin >> N >> M >> R >> C >> L;
        pair <int, int> coord = make_pair(R, C);
        vector <vector <int> > maps(N, vector <int>(M, 0));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int a;
                cin >> a;
                if (a != 0) maps[i][j] = a;
            }
        }
        vector <vector <bool> > visit(N, vector <bool>(M, false));
        q = queue <pair<int, int>>();
        cnt = 0;
        bfs(coord, visit, maps);
        cout << "#" << t << " " << cnt << endl;
    }
 
}
