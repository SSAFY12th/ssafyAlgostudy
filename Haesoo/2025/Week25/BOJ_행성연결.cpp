#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

struct Info {
    int next;
    long long cost;
    bool operator <(const Info &i) const {
        return i.cost < cost;
    }
};

int main() {
    int N;
    cin >> N;
    vector <vector <Info>> relation (N);
  
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            long long cost;
            cin >> cost;
            if (j >= i + 1) {
                relation[i].push_back({j, cost});
                relation[j].push_back({i, cost});
            }
        }
    } // 입력
  
    priority_queue <Info> pq;
    bool visited[1000] = {false, };
  
    int curNode = 0;
    long long curCost = 0;
    pq.push({curNode, curCost}); // 임의의 시작 노드 지정
  
    long long ans = 0;
    while (!pq.empty()) {
        curNode = pq.top().next;
        curCost = pq.top().cost;
        pq.pop();
        if (!visited[curNode]) {
            ans += curCost;
            visited[curNode] = true;
            for (int i = 0; i < relation[curNode].size(); i++) {
                if (!visited[relation[curNode][i].next]) {
                    pq.push({relation[curNode][i].next, relation[curNode][i].cost});
                }
            }
        }
        
    }
    cout << ans;
}
