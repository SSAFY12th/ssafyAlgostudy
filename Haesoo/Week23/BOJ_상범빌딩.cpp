#include <iostream>
#include <queue>
#include <tuple>
#include <vector>
#include <string.h>
using namespace std;
int l, r, c;
char building[31][31][31] = {0, };
bool visited[31][31][31] = {false, };

int dx[] = {1,-1,0,0,0,0};
int dy[] = {0,0,-1,1,0,0};
int dz[] = {0,0,0,0,1,-1};

vector<int> result;
bool flag = false;

bool range (int nz, int ny, int nx) {
    return nx >= 0 && ny >= 0 && nz >= 0 && nx < c && ny < r && nz < l;
}

void bfs(int z1, int y1, int x1, int c1) {
    queue <tuple<int, int, int, int>> q;
    q.push(make_tuple(z1, y1, x1, c1));
    visited[z1][y1][x1] = true;
    
    while (!q.empty()) {
        int z = get<0>(q.front());
        int y = get<1>(q.front());
        int x = get<2>(q.front());
        int cnt = get<3>(q.front());

        if (building[z][y][x] == 'E') {
            flag = true;  
            result.push_back(cnt);
            break; // 탈출
        }
        q.pop();
        
        for (int i = 0; i < 6; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];
            int nz = z + dz[i];
 
            if (range(nz, ny, nx)){
                if(!visited[nz][ny][nx] && building[nz][ny][nx] != '#') {
                    q.push(make_tuple(nz, ny, nx, cnt + 1));
                    visited[nz][ny][nx] = true;
                } 
            }
        }
    }
}
int main() {
    while(1) {
        cin >> l >> r >> c;
        if (l == 0 && r == 0 && c == 0) break;
        int start_x, start_y, start_z;
        memset(visited, false, sizeof(visited));
        flag = false;
 
        for (int i = 0; i < l;i++) {
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    cin >> building[i][j][k];
                    if (building[i][j][k] == 'S') {
                        start_x = k;
                        start_y = j;
                        start_z = i; 
                    }
                }
            }
        }
        bfs(start_z, start_y, start_x, 0);
        if (!flag) result.push_back(-1);
    }
    for (int i = 0; i < result.size(); i++) {
        if (result[i] == -1) cout << "Trapped!" << '\n';
        else cout << "Escaped in " << result[i] << " minute(s)." << '\n';
    }
    return 0;
}
