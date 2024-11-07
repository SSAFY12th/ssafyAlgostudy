#include <iostream>
#include <queue>
#include <vector>
using  namespace std;

struct Edge {
	int nextNode;
	int cost;
	bool operator< (const Edge &next) const {
		return cost < next.cost;
	}
};

void prim(Edge start) {

}

int main() {
	int N, M;
	cin >> N >> M;
	vector <vector <Edge>> relation(N);
	vector <bool> visited(N, false);
	for (int i = 0; i < M; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		relation[a - 1].push_back({b - 1, c});
		relation[b - 1].push_back({a - 1, c});
	}

	vector <int> exitCost(N);
	Edge start = {0, 10000};
	for (int i = 0; i < N; i++) {
		cin >> exitCost[i];
		if (start.cost < exitCost[i]) {
			start = {i, exitCost[i]};
		}
	}
	
	priority_queue <Edge> pq;
	int time = 0;

	for (int i = 0; i < N; i++) {
		if (relation[i].size() == 0) {
			time += exitCost[i];
			visited[i] = true;
		}
	}
	start = {0, 10000};
	for (int i = 0; i < N; i++) {
		if (!visited[i]) {
			if (start.cost < exitCost[i]) start = {i, exitCost[i]};
		}
		pq.push(start);
		//prim(start);
	}
	//pq.push(start);
	while (!pq.empty()) {
		int nextNode = pq.top().nextNode;
		int curCost = pq.top().cost;
		pq.pop();

		if (!visited[nextNode]) {
			visited[nextNode] = true;
			if (curCost < exitCost[nextNode]) time += curCost;
			else time += exitCost[nextNode];

			for (auto& i : relation[nextNode]) {
				if (!visited[i.nextNode]) pq.push(i);
			}
		}
	}
	cout << time;
}
