#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
using namespace std;

bool visited[200] = {false, };
void dfs(int cur, vector<vector<int>> relation) {
    visited[cur] = true;
    for (int i = 0; i < relation[cur].size(); i++) {
        if (!visited[relation[cur][i]]) dfs(relation[cur][i], relation);
    }
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    vector <vector <int>> relation (n);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i == j) continue;
            if (computers[i][j] == 1) relation[i].push_back(j);
        }
    }
    
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            dfs(i, relation);
            answer++;
        }
    }
    return answer;
}
