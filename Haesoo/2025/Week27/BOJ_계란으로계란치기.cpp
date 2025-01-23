//BOJ 계란으로 계란치기 25-01-23
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;
struct Egg {
    int durabilty;
    int weight;
    bool isBreak;
};

Egg eggs[8];

int ans = 0;
void dfs (int idx) {
    if (idx >= N) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (eggs[i].durabilty <= 0) cnt++;
            if (cnt > ans) ans = cnt;
        }
        return;
    }
    if (eggs[idx].isBreak == true) dfs(idx + 1);
    
    else for (int i = 0; i < N; i++) {
        if (i == idx) continue;
        if (!eggs[i].isBreak) {
            eggs[idx].durabilty -= eggs[i].weight;
            eggs[i].durabilty -= eggs[idx].weight;
            // 깨고
        }
        dfs(idx + 1);
        eggs[idx].durabilty += eggs[i].weight;
        eggs[i].durabilty += eggs[idx].weight;
        // 원복
    }

}

int main() {
    cin.tie(0);cout.tie(0);
    ios::sync_with_stdio(0);
    cin >> N;
    for (int i = 0; i < N; i++) {
        int a, b;
        cin >> a >> b;
        eggs[i] = {a, b, false};
    }
    dfs(0);
    cout << ans;
}
