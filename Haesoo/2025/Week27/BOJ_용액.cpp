#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>
using namespace std;
 
int cmp(int a, int b) {
	return abs(a) < abs(b);
}
int Min = 2000000000, sum;
int ans[2];
 
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	int N, num, i;
	cin >> N;
	vector<int> arr;
	for (i = 0; i < N; i++) {
		cin >> num;
		arr.push_back(num);
	}
	sort(arr.begin(), arr.end(), cmp);
	for (i = 0; i < N - 1; i++) {
		sum = abs(arr[i] + arr[i + 1]);
		if (sum < Min) {
			Min = sum;
			ans[0] = arr[i];
			ans[1] = arr[i + 1];
		}
	}
	sort(ans, ans + 2);
	cout << ans[0] << ' ' << ans[1];
}
