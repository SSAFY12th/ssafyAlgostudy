#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;
char vowel[5] = {'a', 'e', 'i', 'o', 'u'};
int main() {
    int L, C;
    cin >> L >> C;
    vector <char> s(C);
    for (int i = 0; i < C; i++) {
        cin >> s[i];
    }
    sort(s.begin(), s.end());

    vector <bool> visited(C);
    fill(visited.begin(), visited.begin() + L, true);
    do {
        string ans;
        int a = 0;
        for (int i = 0; i < C; i++) {
            if (visited[i]) {
                ans += s[i];
                for (int j = 0; j < 5; j++) {
                    if (s[i] == vowel[j]) a++;
                }
            }
        }
        if (a >= 1 && L - a >= 2) cout << ans << endl;
    } while (prev_permutation(visited.begin(), visited.end()));
}
