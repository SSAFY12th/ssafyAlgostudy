import java.io.*;
import java.util.*;

public class 퇴사2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dp = new int[1500050];
        int[] P = new int[1500050];
        int[] T = new int[1500050];
        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            if (dp[i] < dp[i - 1]) dp[i] = dp[i - 1];
            dp[i + T[i] - 1] = Math.max(dp[i + T[i] - 1], dp[i - 1] + P[i]);
        }
        System.out.println(dp[N]);
    }
}

/*
#include <iostream>
using namespace std;

int dp[1500055];
int P[1500055];
int T[1500055];

int main() {
	int N;
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> T[i] >> P[i];
	}

	for (int i = 1; i <= N; i++) {
		if (dp[i] < dp[i - 1]) dp[i] = dp[i - 1];
		dp[i + T[i] - 1] = max(dp[i + T[i] - 1], dp[i - 1] + P[i]);
	}
	cout << dp[N];
}
 */
