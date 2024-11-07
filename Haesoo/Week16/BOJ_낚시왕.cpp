#include <iostream>
#include <string>
#include <cmath>
#include <set>
#include <unordered_map>
#include <stack>
#include <queue>
#include <vector>
#include <algorithm>


#define MAX 105
#define LL long long
#define INF 1e9

using namespace std;
struct SHARK {
    int R, C, S, D, size;
    bool isLive = true;
};

int R, C, M;
vector<SHARK> Shark;
int Fisher = 0;
int MAP[MAX][MAX];
int dy[5] = { 0,-1,1,0,0 };
int dx[5] = { 0,0,0,1,-1 };
int answer = 0;

bool range(int ny, int nx) { 
    return ny >= 1 && nx >= 1 && ny <= R && nx <= C;
}

void Shark_Moving(int IDX) {
    int Len = Shark[IDX].S;
    int Dir = Shark[IDX].D;
    int y = Shark[IDX].R;
    int x = Shark[IDX].C;
    if ((Dir == 1) || (Dir == 2)) { // 상어가 위아래로 움직이는 경우
        Len %= ((R - 1) * 2);
    }
    else if ((Dir == 3) || (Dir == 4)) { // 상어가 좌우로 움직이는 경우
        Len %= ((C - 1) * 2);
    }
    while (Len--) {
        int ny = y + dy[Dir];
        int nx = x + dx[Dir];
        if (range(ny, nx)) {
            if (Dir == 1) Dir = 2;
            else if (Dir == 2) Dir = 1;
            else if (Dir == 3) Dir = 4;
            else if (Dir == 4) Dir = 3;
            ny = y + dy[Dir];
            nx = x + dx[Dir];
        }
        y = ny;
        x = nx;
    };
    Shark[IDX].D = Dir;
    Shark[IDX].R = y;
    Shark[IDX].C = x;
    MAP[y][x]++;
}

void Shark_Eating(int Y, int X) {
    int Big = -1;
    int Big_IDX = 0;
    for (int i = 1; i <= M; i++) {
        if ((Shark[i].isLive) && (Shark[i].R == Y) && (Shark[i].C == X)) {
            if (Big < Shark[i].size) {
                Big = Shark[i].size;
                Big_IDX = i;
            }
        }
    }
    for (int i = 1; i <= M; i++) {
        if ((Shark[i].isLive) && (Shark[i].R == Y) && (Shark[i].C == X)) {
            if (Big != Shark[i].size) Shark[i].isLive = false;
        }
    }
    MAP[Y][X] = 1;
}

int main() {
    cin.tie(NULL); cout.tie(NULL); ios::sync_with_stdio(false);
    cin >> R >> C >> M;
    Shark.resize(M + 1);
    for (int i = 1; i <= M; i++) {
        int R, C, S, D, size;
        cin >> Shark[i].R >> Shark[i].C >> Shark[i].S >> Shark[i].D >> Shark[i].size;
        MAP[Shark[i].R][Shark[i].C]++;
    }
    while (1) {
        Fisher++; //낚시왕 한 칸 이동
        if (Fisher > C) { 
            break;
        }
        for (int i = 1; i <= R; i++) { 
            if (MAP[i][Fisher] == 1) {
                int Cur_Shark; 
                for (int j = 1; j <= M; j++) {
                    if ((Shark[j].isLive) && (Shark[j].R == i) && (Shark[j].C == Fisher)) {
                        Cur_Shark = j;
                        break;
                    }
                }
                Shark[Cur_Shark].isLive = false; // 상어를 잡고
                answer += Shark[Cur_Shark].size; // 상어의 크기를 더함
                break;
            }
        }
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                MAP[i][j] = 0;
            }
        }
        for (int i = 1; i <= M; i++) {
            if (Shark[i].isLive) { // 살아있다면 이동
                int nextY = Shark[i].R + moveY[Shark[i].D];
                int nextX = Shark[i].C + moveX[Shark[i].D];
                Shark_Moving(i);
            }
        }
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (MAP[i][j] >= 2) {
                    Shark_Eating(i, j);
                }
            }
        }
    };

cout << answer << "\n";
return 0;
}
