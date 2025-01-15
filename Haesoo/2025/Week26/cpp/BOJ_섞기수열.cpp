#include <iostream>
#include <numeric>
#include <cstring>
using namespace std;

int N;
int parents[20005];

int find(int x) {
    if (parents[x] < 0) return x;
    return parents[x] = find(parents[x]);
}

int main() {
    cin >> N;
    memset(parents, -1, sizeof(p));
    for (int i; i < N; i++) {
        int x;
        cin >> x;
        x--;
        int a = find(x);
        int b = find(i);
        if (a == b) continue;
        parents[a] += parents[b];
        parents[b] = a;
    }

    int ans = 1;
    for (int i = 0; i < N; i++) {
        if (parents[i] < 0) ans = lcm(ans, -parents[i]);
    }
    cout << ans;
    return 0;
}
