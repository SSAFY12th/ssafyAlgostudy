#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <math.h>
using namespace std;
int main() {
    int N, M, mincal = 100000, ans = 0;
    vector <vector <int> > cities;
    vector <pair <int, int> > home;
    vector <pair <int, int> > combi;
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		vector <int> temp(N);
		for (int j = 0; j < N; j++) {
			cin >> temp[j];
			if (temp[j] == 2) combi.push_back(make_pair(i, j));
            if (temp[j] == 1) home.push_back(make_pair(i, j));
		}
		cities.push_back(temp);
	}
	vector <bool> visit(combi.size());
	fill(visit.begin(), visit.begin() + M, true);
    int count = 0;
	do {
		vector <pair <int, int> > chicken;
		ans = 0;
		for (int i = 0; i < visit.size(); i++) if (visit[i]) chicken.push_back(combi[i]);
		for (int i = 0; i < home.size(); i++) {
            int tempsum = 100000;
            for (int j = 0; j < M; j++) {
                tempsum = min(tempsum, (abs(home[i].first - chicken[j].first) + abs(home[i].second - chicken[j].second)));
            }
            ans += tempsum;          
        }
        count++;
		mincal = min(ans, mincal);
	} while (prev_permutation(visit.begin(), visit.end()));
    cout << mincal << endl;
}
