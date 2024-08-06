import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int maxval = -1;
    static boolean [][] visited;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static int [][] region;

    public static boolean range (int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static void dfs (int y, int x, int limit) {
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if (range(ny, nx) && region[ny][nx] > limit && !visited[ny][nx]) dfs(ny, nx, limit);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        region = new int[N][N];
        visited = new boolean[N][N];
        int maxlimit = -1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                region[i][j] = Integer.parseInt(st.nextToken());
                maxlimit = Math.max(maxlimit, region[i][j]);
            }
        }

        for (int i = 0; i < maxlimit; i++) {
            int cnt = 0;
            for (int a = 0; a < N; a++) {
                Arrays.fill(visited[a], false);
            }
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (!visited[j][k] && region[j][k] > i) {
                        dfs(j, k, i);
                        cnt++;
                    }
                    else visited[j][k] = true;
                }
            }
            maxval = Math.max(maxval, cnt);
        }
        System.out.println(maxval);
    }
}

/* 20ms
#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
using namespace std;
int N;
int maxval = -1;
int visited[101][101];
vector<vector<int> > region;
int dy[4] = {0, 0, -1, 1};
int dx[4] = {-1, 1, 0, 0};
bool range(int y, int x) {
    return y >= 0 && x >= 0 && y < N && x < N;
}
void dfs(int y, int x, int limit) {
    visited[y][x] = true;
    for (int i = 0; i < 4; i++) {
        int ny = dy[i] + y;
        int nx = dx[i] + x;
        if (range(ny, nx) && region[ny][nx] > limit && !visited[ny][nx]) dfs(ny, nx, limit);
    }
}

int main() {
    cin >> N;
    int maxlimit = -1, minlimit = 100;
    for (int i = 0; i < N; i++) {
        vector<int> temp(N);
        for (int j = 0; j < N; j++) {
            cin >> temp[j];
            maxlimit = max(maxlimit, temp[j]);
            minlimit = min(minlimit, temp[j]);
        }
        region.push_back(temp);
    }
    //for (int i = minlimit; i < maxlimit; i++) {
      for (int i = 0; i < maxlimit; i++) {
        int cnt = 0;
        memset(visited, false, sizeof(visited));
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                if (!visited[j][k] && region[j][k] > i) {
                    dfs(j, k, i);
                    cnt++;
                }
                else visited[j][k] = true;
            }
        }
        maxval = max(maxval, cnt);
    }
    cout << maxval << endl;
}

*/
