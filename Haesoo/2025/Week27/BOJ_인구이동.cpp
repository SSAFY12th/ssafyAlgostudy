#include <iostream>
#include <queue>
#include <string.h>
using namespace std;

int n, l, r, sum = 0;
int arr[50][50] = {0, };
bool visited[50][50] = {false, };
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, 1, 0, -1};
vector<pair<int,int>> v;

bool range (int ny, int nx) {
    return nx < 0 || ny < 0 || nx >= n || ny >= n;
}

void dfs(int x, int y) {
    v.push_back(make_pair(x, y));
    visited[x][y] = true;
    sum += arr[x][y];

    for (int i = 0 ; i < 4 ; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (range(ny, nx) || visited[nx][ny]) continue;
        if (abs(arr[nx][ny] - arr[x][y]) < l || abs(arr[nx][ny] - arr[x][y]) > r) continue;
        dfs(nx, ny);
    }
}

int main() {
    ios_base::sync_with_stdio(0); 
    cin.tie(0); cout.tie(0);

    int ans = 0;
    cin >> n >> l >> r;

    for(int i = 0 ; i < n ; i++) {
        for(int j = 0 ; j < n ; j++) {
            cin >> arr[i][j];
        }
    }

    while(1) {
        memset(visited, false, sizeof(visited));
        vector<pair<pair<int,int>, int>> record;
        int cnt = 0;

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(!visited[i][j]) {
                    v.clear();
                    sum = 0;
                    dfs(i, j);

                    if(v.size() == 1) {
                        visited[i][j] = false;
                        cnt++;
                        continue;
                    }

                    int num = sum / v.size();
                    for(int i = 0 ; i < v.size() ; i++) {
                        record.push_back({{v[i].first, v[i].second}, num});
                    }
                }
            }
        }
        if(cnt == n * n) break;
        for(int i = 0 ; i < record.size() ; i++) {
            int y = record[i].first.first;
            int x = record[i].first.second;
            int num = record[i].second;
            arr[y][x] = num;
        }
        ans++;
    }
    cout << ans;
    return 0;
}
