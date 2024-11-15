#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;
int N, maxAns = -1e9;
bool visited[10] = { false, };
void dfs(int idx, vector <int> nums, vector <char> params) {
	if (idx >= nums.size() - 1) {
		int curAns = 0;
		bool visited2[10] = { false, };
		for (int i = 0; i < nums.size() - 1; i++) {
			if (visited[i]) {
				if (params[i] == '+') {
					int temp = nums[i] + nums[i + 1];
					//curAns += temp;
					nums[i] = temp;
					nums[i + 1] = temp;
				}
				if (params[i] == '*') {
					int temp = nums[i] * nums[i + 1];
					nums[i] = temp;
					nums[i + 1] = temp;
				}
				if (params[i] == '-') {
					int temp = nums[i] - nums[i + 1];
					nums[i] = temp;
					nums[i + 1] = temp;
				}
				visited2[i] = true; // params visited
				i++;
			}
		}
		for (int i = 0; i < nums.size() - 1; i++) {
			if (!visited2[i]) {
				if (params[i] == '+') {
					int temp = nums[i] + nums[i + 1];
					curAns += temp;
					nums[i] = temp;
					nums[i + 1] = temp;
					if (i != nums.size() - 2 && visited[i + 1]) nums[i + 2] = temp;
				}
				if (params[i] == '*') {
					int temp = nums[i] * nums[i + 1];
					curAns += temp;
					nums[i] = temp;
					nums[i + 1] = temp;
					if (i != nums.size() - 2 && visited[i + 1]) nums[i + 2] = temp;
				}
				if (params[i] == '-') {
					int temp = nums[i] - nums[i + 1];
					curAns += temp;
					nums[i] = temp;
					nums[i + 1] = temp;
					if (i != nums.size() - 2 && visited[i + 1]) nums[i + 2] = temp;
				}
				visited2[i] = true; // params visited
			}
		}
		maxAns = max(maxAns, nums[nums.size() - 1]);
		return;
	}
	visited[idx] = true;
	visited[idx + 1] = true;
	dfs(idx + 2, nums, params);
	visited[idx] = false;
	visited[idx + 1] = false;
	dfs(idx + 1, nums, params);
 }

int main() {
	cin.tie(0); cout.tie(0); ios::sync_with_stdio(false);
	cin >> N;
	string s;
	cin >> s;
	vector <int> nums;
	vector <char> params;
	for (int i = 0; i < s.length(); i++) {
		if (s[i] >= 48 && s[i] < 58) nums.push_back(s[i] - 48);
		else params.push_back(s[i]);
	}
	dfs(0, nums, params);
	cout << maxAns;
}
