#include <iostream>
#include <vector>
using namespace std;
int N, M;
int parents[201] = {0, };

int find(int a) {
    if (parents[a] == a) return a;
    else return parents[a] = find(parents[a]);
} 

bool uni(int a, int b) {
    a = find(a);
    b = find(b);
    if (a == b) return false;
    else parents[b] = a;
    return true;
}

int main() {
    cin >> N;
    cin >> M;
    for (int i = 1; i <= N; i++) {
        parents[i] = i;
    }
    int num;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> num;
            if (j >= i) if (num == 1) uni(i, j);
            
        }
    }
    vector <int> plan(M);
    for (int i = 0; i < M; i++) {
        cin >> plan[i];
    }
    int start = find(parents[plan[0]]);
    for (int i = 1; i < M; i++) {
        if (start != find(parents[plan[i]])) {
            cout << "NO";
            return 0;
        }
    }
     cout << "YES";
}
