import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.Math.abs;

public class BOJ9663 {
    static int N, cnt = 0;
    static boolean prove (int row, int[] r) {
        for (int i = 0; i < row; i++)
            if (r[i] == r[row] || abs(r[i] - r[row]) == abs(row - i)) return false;
        return true;
    }
    static void backtracking (int cur, int[] r) {
        if (cur == N) {
            cnt++;
            return;
        }
        for (int i = 0; i < N; i++) {
            r[cur] = i;
            if (prove(cur, r)) backtracking(cur + 1, r);
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int [] rows = new int[N];
        backtracking(0, rows);
        System.out.print(cnt);
    }
}

/*
#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

int N, cnt = 0;

bool prove (int row, vector <int>& v) {
    for (int i = 0; i < row; i++) {
        if (v[i] == v[row] || abs(v[i] - v[row]) == abs(row - i)) return false;
    }
    return true;
}

void backtracking (int cur, vector <int>& v) {
    if (cur == N) {
        cnt++;
        return;
    }
    //cout << cnt << endl;
    for (int i = 0; i < N; i++) { // 해당 행의 열 탐색
        v[cur] = i; // cur번째 가로 줄의 i번째 칸에 퀸 일단 배치
        if (prove(cur, v)) backtracking (cur + 1, v);
    }
}

int main() {

    cin >> N;
    vector <int> row(N); // 행마다 탐색할 것이므로 가로 크기에 맞는 1차원 배열 생성 row[idx] = val 일 때 val행 idx열에 퀸이 존재
    backtracking(0, row);
    cout << cnt << endl;

}
*/
