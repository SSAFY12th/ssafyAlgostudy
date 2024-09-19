import java.util.*;
import java.io.*;
public class SWEA2112 {
    static int D, W, K, mincnt;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            mincnt = 100000;
            if (K == 1) {
                System.out.println("#" + t + " " + 0);
                break;
            }
            map = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[][] tempmap = map;
            dfs(0, -1, -1, tempmap);
            System.out.println("#" + t + " " + mincnt);
            }
        }
    static void dfs(int cnt, int depth, int val, int[][] maps) {
        //System.out.println(mincnt);
        System.out.println(Arrays.deepToString(maps));
        if (cnt >= K) {
            mincnt = Math.min(mincnt, K);
            return;
        }
        if (depth == D) return;
        int [][] tempv = maps;
        if (depth >= 0 && val >= 0) {
            for (int i = 0; i < W; i++) {
                tempv[depth][i] = val;
            }
        }
        int colcnt = 0;
        for (int i = 0; i < W; i++) {
            int a = 0, b = 0;
            int maxa = 0, maxb = 0;
            for (int j = 0; j < D; j++) {
                maxa = Math.max(maxa, a);
                maxb = Math.max(maxb, b);
                if (tempv[j][i] == 1) {
                    if (a != 0) a = 0;
                    b++;
                }
                else if (tempv[j][i] == 0) {
                    if (b != 0) b = 0;
                    a++;
                }
                maxa = Math.max(maxa, a);
                maxb = Math.max(maxb, b);
            }
            if (maxa < K && maxb < K) break;
            else colcnt++;
        }
        if (colcnt == W) {
            mincnt = Math.min(mincnt, cnt);
            return;
        }
        dfs(cnt + 1, depth + 1, 1, tempv);
        dfs(cnt + 1, depth + 1, 0, tempv);
        dfs(cnt, depth + 1, -1, tempv);
    }
}


/*
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int D, W, K;
vector <vector <int>> v;
int mincnt = 10000;
void dfs(int cnt, int depth, int val, vector <vector <int>> param) {
	if (cnt >= K) {
		mincnt = min(mincnt, K);
		return;
	}
	if (depth == D) return;
	vector <vector <int>> tempv = param;
	if (depth >= 0 && val >= 0) {
		for (int i = 0; i < W; i++) {
			tempv[depth][i] = val;
		}
	}
	int colcnt = 0;
	
	for (int i = 0; i < W; i++) {
		int a = 0, b = 0;
		int maxa = 0, maxb = 0;
		for (int j = 0; j < D; j++) {
			maxa = max(maxa, a);
			maxb = max(maxb, b);
			if (tempv[j][i] == 1) {
				if (a != 0) a = 0;
				b++;
			}
			else if (tempv[j][i] == 0) {
				if (b != 0) b = 0;
				a++;
			}
			maxa = max(maxa, a);
			maxb = max(maxb, b);
		}
		if (maxa < K && maxb < K) break;
		else colcnt++;
	}
	if (colcnt == W) {
		mincnt = min(mincnt, cnt);
		return;
	}
	dfs(cnt + 1, depth + 1, 1, tempv);
	dfs(cnt + 1, depth + 1, 0, tempv);
	dfs(cnt, depth + 1, -1, tempv);
}

int main() {
	int T;
	cin >> T;
	for (int t = 1; t <= T; t++) {
		cin >> D >> W >> K;
		mincnt = 100000;
		if (K == 1) {
			cout << "#" << t << " " << 0 << endl;
			break;
		}
		v.assign(D, vector <int>(W, 0));
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				cin >> v[i][j]; // 0이면 A, 1이면 B
			}
		}
		dfs(0, -1, -1, v);
		cout << "#" << t << " " << mincnt << endl;
	}
}



*/
