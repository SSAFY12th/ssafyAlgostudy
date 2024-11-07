#include <bits/stdc++.h>
#include <iostream>
using namespace std;

const int MAX = 21;
int h, w, n, dir[4][2] = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} }, len = INT_MAX;
char m[MAX][MAX], rev[4] = {'W', 'D', 'S', 'A'};
bool visited[MAX][MAX];
pair<int, int> s;
vector<int> ans;

int convert(char c) {
    switch (c) {
    case 'W':
        return 0;
    case 'D':
        return 1;
    case 'S':
        return 2;
    case 'A':
        return 3;
    }
}

void func(vector<pair<int, int>>& v, vector<int>& path, pair<int, int> now, int idx) {
    if (m[now.first][now.second] == 'Z') {
        if (path.size() < len) {
            len = path.size();
            ans = path;
        }

    return;
}

if (idx == v.size())
    return;

int nr = now.first + dir[v[idx].first][0];
int nc = now.second + dir[v[idx].first][1];

if (nr >= 1 && nr <= h && nc >= 1 && nc <= w && m[nr][nc] != '@') {
    path.push_back(v[idx].first);
    func(v, path, { nr, nc }, idx + 1);
    path.pop_back();
}

nr = now.first + dir[v[idx].second][0];
nc = now.second + dir[v[idx].second][1];

if (nr >= 1 && nr <= h && nc >= 1 && nc <= w && m[nr][nc] != '@') {
    path.push_back(v[idx].second);
    func(v, path, { nr, nc }, idx + 1);
    path.pop_back();
}
}

int main(void) {
    ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

cin >> h >> w;
for (int i = 1; i <= h; i++) {
    for (int j = 1; j <= w; j++) {
        char c;
        cin >> c;
        if (c == 'D') {
            s = { i, j };
            m[i][j] = '.';
            continue;
        }

        m[i][j] = c;
    }
}

cin >> n;
vector<pair<int, int>> v(n);
for (auto& i : v) {
    char c1, c2;
    cin >> c1 >> c2;
    i = { convert(c1), convert(c2) };
}

vector<int> path;
func(v, path, s, 0);
if (len == INT_MAX) {
    cout << "NO\n";
    return 0;
}

cout << "YES\n";
for (auto& i : ans)
    cout << rev[i];

cout << endl;
return 0;
}
