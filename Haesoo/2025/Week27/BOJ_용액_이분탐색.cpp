#include <iostream>

using namespace std;

int list[100001];
int main() {
	int N;
	cin >> N;
	for(int i = 0; i < N; i++) {
		cin >> list[i];
	}

	int left = 0, right = N - 1;
	long long sum = (long long) list[right] + list[left];
	int temp1 = left, temp2 = right;

	while(left < right){
		long long tmp = (long long)list[right] + list[left];
		if (abs(tmp) < abs(sum)){
			sum = tmp;
			temp1 = left;
			temp2 = right;
		}
		if (abs(list[right-1] + list[left]) > abs(list[right] + list[left+1])) left++;
		else right--;
	}
	
	cout << list[temp1] << " " << list[temp2];
    return 0;
}
