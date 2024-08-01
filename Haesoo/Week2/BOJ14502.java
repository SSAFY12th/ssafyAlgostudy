import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// next_permutation, prev_permutation 안됨
// pair <int, int> 못씀,,
class Pair {
    Integer x;
    Integer y;
    Pair(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
    public Integer getfirst() {
        return x;
    }
    public Integer getsecond() {
        return y;
    }
}

public class BOJ14502 {
    static int N, M, maxcnt = 0;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};


    public static boolean range (int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }
    public static void spread (int[][] tempmap) {
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(tempmap[i][j] == 2) q.offer(new Pair(i, j));
            }
        }
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int y = p.getfirst();
            int x = p.getsecond();
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (range(ny, nx) && tempmap[ny][nx] == 0) {
                    tempmap[ny][nx] = 2;
                    q.offer(new Pair(ny, nx));
                }
            }


        }

    }
    public static int combination (int[][] orgmap, int[][] tempmap, List<Pair> combi, boolean[] visited, int start, int r) {
        if (r == 0) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    tempmap[i][j] = orgmap[i][j];
                }
            }
            for (int i = 0; i < combi.size(); i++) {
                if (visited[i]) tempmap[combi.get(i).getfirst()][combi.get(i).getsecond()] = 1;
            }
            spread(tempmap);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tempmap[i][j] == 0) cnt++;
                }
            }
            if (cnt > maxcnt) maxcnt = cnt;

        } else {
            for (int i = start; i < combi.size(); i++) {
                visited[i] = true;
                combination(orgmap, tempmap, combi, visited, i + 1, r - 1);
                visited[i] = false;
            }
        }
    return maxcnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int [][] orgmap = new int [N][M];
        int [][] tempmap = new int [N][M];

        //List <Integer[]> orgmap = new ArrayList<Integer[]>();
        //List <Integer[]> tempmap = new ArrayList<Integer[]>();
        List<Pair> combi = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                orgmap[i][j] = Integer.parseInt(st.nextToken());
                if (orgmap[i][j] == 0) combi.add(new Pair(i, j));
            }
        }
        boolean [] visited = new boolean[combi.size()];
        System.out.print(combination(orgmap, tempmap, combi, visited, 0, 3));
    }
}

/* 52ms
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <cstring>
using namespace std;

int N, M;
int dy[4] = {0, 0, -1, 1};
int dx[4] = {-1, 1, 0, 0};

//1. 매번 bfs 하는 벡터 초기화 (tempmap)
//2. 벽 3개 뽑기 (현재 0인 곳의 좌표들만 저장해서, 조합하기) -> maximum 64C3이니까 시간초과 X
//3. 바이러스 확산시키기
//4. 안전구역 카운팅
//5. 벽 3개를 뽑을 수 있는 모든 경우의 수마다 1~4 반복하며 max 갱신

bool range (int y, int x) {
    return y >= 0 && x >= 0 && y < N && x < M;
}

void spread(vector<vector <int> > &tempmap) {
    queue <pair<int, int> > q;
     for(int i = 0; i < N; i++) {
		for(int j = 0; j < M; j++) {
			if(tempmap[i][j] == 2) {
				q.push(make_pair(i, j));
			}
		}
	} // 확산 전 map에서 2인 곳들만 확산 시작점이니까 저장
    while (!q.empty()) {
        int y = q.front().first;
        int x = q.front().second; // 현 좌표 추출
        q.pop();
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (range(ny, nx) && tempmap[ny][nx] == 0) { // 범위 내, 확산 가능한지 (0) 체크
                tempmap[ny][nx] = 2; // 확산시키고
                q.push(make_pair(ny, nx)); // 또 ㄱㄱ
            }

        }
    }

}

int main() {
    cin >> N >> M;
    vector <vector <int> > orgmap(N, vector <int> (M, 0));
    vector <vector <int> > tempmap(N, vector <int> (M, 0));

    vector <pair <int, int> > combi;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> orgmap[i][j];
            if (orgmap[i][j] == 0) combi.push_back(make_pair(i, j));
        } // 0인 곳만 벽 세울 수 있으니까 0일 때만 combi에 좌표 저장
    }
    int comsize = combi.size();
    vector <bool> temp (comsize);
    fill(temp.begin(), temp.begin() + 3, true); // 조합을 위한 bool 배열, 3개 뽑을거니까 3칸만 true
    int maxcnt = 0;

    do {
        tempmap = orgmap; // 경우의 수 바뀔 때마다 temp를 원본 값으로 갱신
        int cnt = 0;
        for (int i = 0; i < combi.size(); i++) {
            if (temp[i]) tempmap[combi[i].first][combi[i].second] = 1;
        } // 벽 3개 세우기, 0인 곳에 대한 좌표 3개씩 뽑아서 1 대입
        spread(tempmap); // 해당 경우의 수에 대해 bfs (바이러스 확산)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!tempmap[i][j]) cnt++;
            }
        } // 확산 끝나고 난 map에서 0이 남아있는지 세기
        if (cnt > maxcnt) maxcnt = cnt; // bfs 끝날 때마다 max 갱신
    } while (prev_permutation(temp.begin(), temp.end())); // begin(), begin() + 3만큼만 true 취급했으므로 prev_permutation하기

    cout << maxcnt << endl;

}
*/
