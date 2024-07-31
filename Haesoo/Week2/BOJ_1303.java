import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.Math.pow;

public class BOJ1303 {
    static int N, M, cntB = 0, cntW = 0;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    public static boolean range(int y, int x) {
        return y >= 0 && x >= 0 && y < M && x < N;
    }
    public static void dfs (int y, int x, char [][] c, boolean [][] v) {
        if (v[y][x]) return;
        v[y][x] = true;
        if (c[y][x] == 'B') cntB++;
        else cntW++;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (range(ny, nx) && c[y][x] == c[ny][nx] && !v[ny][nx]) dfs (ny, nx, c, v);
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char [][] colors = new char [M][N];
        boolean [][] visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                colors[i][j] = s.charAt(j);
            }
        }
        int W = 0, B = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                cntB = 0;
                cntW = 0;
                dfs(i, j, colors, visited);
                W += pow(cntW, 2);
                B += pow(cntB, 2);
            }
        }
        System.out.println(W + " " + B);
    }
}


/*
#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
using namespace std;

// 대각선 인접은 취급 X, 상하좌우에 대해서만 인정
//가로 N (열), 세로 M (행)

int N, M;
int dy[4] = {0, 0, -1, 1};
int dx[4] = {-1, 1, 0, 0};
bool range (int y, int x) {
    return y >= 0 && x >= 0 && y < M && x < N;
}
int cntB, cntW;
void dfs (int y, int x, vector <vector <char> > & c, vector <vector <bool> > & v) {
    if (v[y][x]) return;
    v[y][x] = true;
    if (c[y][x] == 'B') cntB++;
    else cntW++;
    for (int i = 0; i < 4; i++) {
        int ny = y + dy[i];
        int nx = x + dx[i];
        if (range(ny, nx) && c[y][x] == c[ny][nx] && !v[ny][nx]) dfs (ny, nx, c, v);
    }
}

int main() {

    cin >> N >> M;
    vector <vector <char> > colors (M, vector <char> (N));
    vector <vector <bool> > visited (M, vector <bool> (N, false));
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            cin >> colors[i][j];
        }
    }
    int W = 0, B = 0;
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            cntB = 0, cntW = 0;
            dfs(i, j, colors, visited);
            W += pow(cntW, 2);
            B += pow(cntB, 2);
        }
    }
    cout << W << " " << B << endl; // 아군이 White 적군이 Blue

}
*/
