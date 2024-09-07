#include <iostream>
#include <vector>
#include <cstring>
#include <queue>
#include <algorithm>
#define MAX 21
using namespace std;
int dy[4] = {0, 0, 1, -1};
int dx[4] = {1, -1, 0, 0};

bool visited[MAX][MAX] = {false};
int board[MAX][MAX];
int d[MAX][MAX] = {0, };
int N, x, y, eatx, eaty, ssize = 2, fcnt = 2;

vector <vector<int>> v;

void bfs() {
    queue <pair<int, int>> q;
    q.push({y, x});
    visited[y][x] = true;

    while(!q.empty()) {
        int y1 = q.front().first;
        int x1 = q.front().second;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int ny = y1 + dy[i];
            int nx = x1 + dx[i];

            if (ny < 0 || ny >= N || nx < 0 || nx >= N || v[ny][nx] > ssize) continue;
            if (!visited[ny][nx] && v[ny][nx] <= ssize) { // 상어 크기가 먹이 크기 (v[ny][nx]) 보다 더 크면 push (먹을 수 있는 먹이)
                q.push({ny, nx});
                d[ny][nx] = d[y1][x1] + 1; // ny, nx에 있는 먹이가 먹을 수 있는 먹이니까 해당 먹이까지의 거리 저장
                visited[ny][nx] = true;

            }
        }
    }
}

int main() {
    int cnt_time = 0;
    cin >> N;
    v.assign(N, vector<int>(N, 0));

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> v[i][j];
            if (v[i][j] == 9) {
                y = i, x = j; // 현재 위치 받기
                v[i][j] = 0; // 상어 크기가 9보다 커졌을 때 무한루프 방지를 위해 0으로 초기화
            }
        }
    }

    while(1) {
        // 중요!! MEMSET으로 d(거리) 랑 visited (방문여부) 초기화
        memset(d, 0, sizeof(d));
        memset(visited, false, sizeof(visited));
        bfs();
        int temp_d = 1000;
        int a = 0;

         for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (v[i][j] != 0 && v[i][j] < ssize){
                    a = 1;
                    if (d[i][j] != 0 && d[i][j] < temp_d){
                        temp_d = d[i][j];
                        eaty = i;
                        eatx = j;
                    }
                }
            }
        }
        if (a == 0) break;
        if (temp_d == 1000) break; 
        fcnt--;
        if (fcnt == 0){
            ssize++;
            fcnt = ssize;    
        }
        cnt_time += temp_d;
        y = eaty;
        x = eatx;
        v[eaty][eatx]=0;
    }

    cout << cnt_time;

}
