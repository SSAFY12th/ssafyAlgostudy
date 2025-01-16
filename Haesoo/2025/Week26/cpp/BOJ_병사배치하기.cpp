#include <iostream>
using namespace std;
int N;
int dp[2001];
int arr[2001] = {0, };
int main() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
        dp[i] = 1;
    }

    for (int i = 1; i < N; ++i) {
		  for (int j = 0; j < i; ++j) {
			  if (arr[i] < arr[j]) dp[i] = max(dp[i], dp[j] + 1);
		  }
    }
	
	int ans = 0;
	for (int i = 0; i < N; i++)	{
		if (dp[i] > ans) ans = dp[i];
  }

	if (ans == N) cout << 0 << endl;
	else cout << N - ans << endl;
  
}
