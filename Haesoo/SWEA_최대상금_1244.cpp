#include<iostream>
#include<string>
#include<algorithm>
using namespace std;
string s;
int max_cnt, answer;
void dfs(int idx, int cnt) {
    if (cnt == max_cnt) {
        answer = max(answer, stoi(s));
        return;
    }
    for (int i = idx; i < s.length() - 1; i++) {
        for (int j = i + 1; j < s.length(); j++) {
            if (s[i] <= s[j]) {
                swap(s[i], s[j]);
                dfs(i, cnt + 1);
                swap(s[i], s[j]);
            }
            if (i == s.length() - 2 && j == s.length() - 1) {
                swap(s[i], s[j]);
                dfs(i, cnt + 1);
                swap(s[i], s[j]);
            }
        }
    }
}
  
int main(int argc, char** argv) {
    int test_case;
    int T;
    cin >> T;
    for (test_case = 1; test_case <= T; ++test_case) {
        cin >> s >> max_cnt;
        answer = 0;
        dfs(0, 0);
        cout << "#" << test_case << " " << answer << "\n";
    }
    return 0;
}
