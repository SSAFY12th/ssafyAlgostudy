#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

bool cmp(int a, int b) {
	return a > b;
}

struct info {
	int toCity;
	int time;

	bool operator<(const info& i) const{
		return time < i.time;
	}
};

int main() {
	int N, M, X;
	cin >> N >> M >> X;
	vector <vector <info>> cities(N);
	vector <vector <info>> reverse(N);
	for (int i = 0; i < M; i++) {
		int curc, nextc, cost;
		cin >> curc >> nextc >> cost;
		cities[curc - 1].push_back({ nextc - 1, cost });
		reverse[nextc - 1].push_back({ curc - 1, cost });
	}

	priority_queue <info> ppq;
	vector <int> ccost(N, 1000000);
	vector <int> answer(N);
	int curCity = X - 1;
	int curCost = 0;
	ccost[curCity] = curCost;
	ppq.push({ curCity, curCost });
	while (!ppq.empty()) {
		curCity = ppq.top().toCity;
		curCost = ppq.top().time;
		ppq.pop();
		if (ccost[curCity] < curCost) continue;
		for (int i = 0; i < reverse[curCity].size(); i++) {
			int nextCity = reverse[curCity][i].toCity;
			int nextCost = reverse[curCity][i].time;
			if (ccost[nextCity] > nextCost + curCost) {
				ccost[nextCity] = nextCost + curCost;
				ppq.push({ nextCity, ccost[nextCity] });
			}
		}
	}
	for (int i = 0; i < N; i++) {
		answer[i] += ccost[i];
	}


	priority_queue <info> pq;
	vector <int> cost(N, 1000000);
	curCity = X - 1;
	curCost = 0;
	cost[curCity] = curCost;
	pq.push({ curCity, curCost });
	while (!pq.empty()) {
		curCity = pq.top().toCity;
		curCost = pq.top().time;
		pq.pop();
		if (cost[curCity] < curCost) continue;
		for (int i = 0; i < cities[curCity].size(); i++) {
			int nextCity = cities[curCity][i].toCity;
			int nextCost = cities[curCity][i].time;
			if (cost[nextCity] > nextCost + curCost) {
				cost[nextCity] = nextCost + curCost;
				pq.push({ nextCity, cost[nextCity] });
			}
		}
	}
	for (int i = 0; i < N; i++) {
		answer[i] += cost[i];
	}
	sort(answer.begin(), answer.end(), cmp);
	cout << answer[0];
}

// 기존 풀이 : 처음에는 하나의 벡터로, 모든 지점에서 X로 가는 다익스트라 (N-1번 실행) -> 이후 X에서 다른 지점으로 가는 다익스트라 1번 실행 = 4% 시간초과
