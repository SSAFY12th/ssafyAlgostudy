#include <string>
#include <vector>
#include <iostream>
#include <set>
using namespace std;

int n, m;
int arr[510][510];
bool visited[510][510] = {false, };

int dy[4] = {-1, 1, 0, 0};
int dx[4] = {0, 0, -1, 1};

int dp[501];

int dfs(int y, int x, int cnt, set<int>& s) {
    bool flag = false;
    for (int i = 0 ; i < 4 ; i++) {
        int ny = y + dy[i];
        int nx = x + dx[i];
        
        if (arr[ny][nx] && !visited[ny][nx]) {
            flag = true;
            visited[ny][nx] = true;
            s.insert(nx);
        	cnt = dfs(ny, nx, cnt + 1, s);
        }
    }
    if (!flag) return cnt;
}

int solution(vector<vector<int>> land) {
    int answer = 0;
    
    n = land.size();
    m = land[0].size();
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            arr[i + 1][j + 1] = land[i][j];
        }
    }
    
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (arr[i][j] && !visited[i][j]) {
                visited[i][j] = true;
                set <int> s;
                s.insert(j);
                int cnt = dfs(i, j, 1, s);
                for (auto iter = s.begin() ; iter != s.end() ; iter++)
                    dp[*iter] += cnt;
            }
        }
    }
    
    for (int i = 1; i <= m; i++) {
        answer = max(answer, dp[i]);
    }
    
    return answer;
}
