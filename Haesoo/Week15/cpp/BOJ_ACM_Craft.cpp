#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <map>
using namespace std;
#define fastio ios::sync_with_stdio(0), cin.tie(0)

int main() {
	int T;
	cin >> T;
	for (int j = 0; j < T; j++) {
		int N, K, W;
		cin >> N >> K;
		//vector <int> depth(N);
		int depth[1000] = { 0, };
		vector <vector <int>> relation(N);
		//map <int, int> cost;
		//map <int, int> result;
		int cost[1000] = { 0, };
		int result[1000] = { 0, };
		for (int i = 0; i < N; i++) {
			cin >> cost[i];
			result[i] = cost[i];
		}
		for (int i = 0; i < K; i++) {
			int prev, next;
			cin >> prev >> next;
			depth[next - 1]++;
			relation[prev - 1].push_back(next - 1);
		}
		cin >> W;

		queue <int> q;
		for (int i = 0; i < N; i++) {
			if (i == W - 1) continue;
			if (depth[i] == 0) q.push(i);
		}
		while (!q.empty()) {
			int curNode = q.front();
			q.pop();
			for (int i = 0; i < relation[curNode].size(); i++) {
				int next = relation[curNode][i];
				result[next] = max(result[next], result[curNode] + cost[next]);
				if (--depth[next] == 0) q.push(next);
			}
		}
		cout << result[W - 1] << endl;
	}
}
