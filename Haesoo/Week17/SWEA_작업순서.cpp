#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;

void bfs(int curNode, vector <vector <int>> relation, vector <int> &depth, vector <bool> &visited) {
	queue <int> q;
	q.push(curNode);
	while (!q.empty()) {
		curNode = q.front();
		q.pop();
		visited[curNode] = true;
		cout << curNode + 1 << " ";
		for (int i = 0; i < relation[curNode].size(); i++) {
			depth[relation[curNode][i]]--;
			if (depth[relation[curNode][i]] == 0) {
				q.push(relation[curNode][i]);
			}
		}
	}
}

int main() {
	for (int t = 1; t <= 10; t++) {
		int V, E;
		cin >> V >> E;
		vector <vector <int>> relation(V);
		vector <int> depth(V, 0);
		for (int i = 0; i < E; i++) {
			int from = 0, to = 0;
			cin >> from >> to;
			relation[from - 1].push_back(to - 1);
			depth[to - 1]++;
		}
		//cout << "depth: ";
		vector <bool> visited(V, false);
		cout << "#" << t << " ";
		for (int i = 0; i < V; i++) {
			//cout << depth[i] << " ";
			if (depth[i] == 0 && !visited[i]) bfs(i, relation, depth, visited);
		}
		cout << endl;
	}
}
