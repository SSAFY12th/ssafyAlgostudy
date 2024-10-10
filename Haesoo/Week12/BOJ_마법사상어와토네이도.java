import java.util.*;
import java.io.*;

public class BOJ20057 {
    static int[] dy = { 0, 1, 0, -1 };
    static int[]dx = { -1, 0, 1, 0 }; // 좌, 우, 하, 상 순서
    static int N, remainSand = 0;
    static int[][] sand;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        sand = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                sand[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 입력
        int tornadoY = N / 2, tornadoX = N / 2;
        int cnt = 1; // 해당 방향으로 몇 번 진행할 지?
        int dir = 0; // 방향
        int i = 1; // 2번마다 cnt를 증가시키기 위한 변수
        while (true) {
            if (i == N * 2) break; // 달팽이 다 돌았으면 종료하기
            for (int k = 1; k <= cnt; k++) {
                tornadoY += dy[dir];
                tornadoX += dx[dir];
                if (tornadoY == 0 && tornadoX == 0) { // 마지막 0,0 도달할 경우
                    moveHorizon(tornadoY, tornadoX, dir);
                    break; // 마지막으로 밀어주고 종료
                }
                if (dir % 2 == 0) moveHorizon(tornadoY,tornadoX, dir); // 좌, 우로 탐색
                else moveVertical(tornadoY, tornadoX, dir); // 상, 하로 탐색
            }
            dir = (dir + 1) % 4;
            if (i % 2 == 0) cnt++; // 2번마다 횟수를 늘려줘야 하므로 (1 1, 2 2, 3 3 .. )
            i++;
        }
        System.out.println(remainSand);
    }
    public static void moveVertical(int cy, int cx, int dir) {
        int temp = 0;
        int ny = cy - dy[dir];
        // 1 x 1
        int nx = cx + 1;
        if (range(ny, nx)) sand[ny][nx] += sand[cy][cx] / 100;
        else remainSand += sand[cy][cx] / 100;
        temp += sand[cy][cx] / 100;

        nx = cx - 1;
        if (range(ny, nx))
            sand[ny][nx] += sand[cy][cx] / 100;
        else remainSand += sand[cy][cx] / 100;
        temp += sand[cy][cx] / 100;

        // 2 7 y 7 2
        ny = cy;
        nx = cx + 1;
        if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 7) / 100;
        else remainSand += (sand[cy][cx] * 7) / 100;
        temp += (sand[cy][cx] * 7) / 100;

        nx = cx - 1;
        if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 7 ) / 100;
        else remainSand += (sand[cy][cx] * 7) / 100;
        temp += (sand[cy][cx] * 7) / 100;
        // -----------------------------------------
        nx = cx + 2;
        if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 2) / 100;
        else remainSand += (sand[cy][cx] * 2) / 100;
        temp += (sand[cy][cx] * 2) / 100;
        nx = cx - 2;
        if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 2) / 100;
        else remainSand += (sand[cy][cx] * 2) / 100;
        temp += (sand[cy][cx] * 2) / 100;

        // 5
        ny = cy + dy[dir] * 2;
        nx = cx;
        if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 5) / 100;
        else remainSand += (sand[cy][cx] * 5) / 100;
        temp += (sand[cy][cx] * 5) / 100;

        // 10 a 10
        ny = cy + dy[dir];
        nx = cx + 1;
        if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 10) / 100;
        else remainSand += (sand[cy][cx] * 10) / 100;
        temp += (sand[cy][cx] * 10) / 100;

        nx = cx - 1;
        if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 10) / 100;
        else remainSand += (sand[cy][cx] * 10) / 100;
        temp += (sand[cy][cx] * 10) / 100;
        nx = cx;
        if (range(ny, nx)) sand[ny][nx] += sand[cy][cx] - temp;
        else remainSand += sand[cy][cx] - temp;
        sand[cy][cx] = 0;
    }
    public static void moveHorizon(int cy, int cx, int dir) {
        int temp = 0;
        int nx = cx - dx[dir];
        // 1 x 1
        int ny = cy + 1;
        if (range(ny, nx)) sand[ny][nx] += sand[cy][cx] / 100;
        else remainSand += sand[cy][cx] / 100;
        temp += sand[cy][cx] / 100;

        ny = cy - 1;
        if (range(ny, nx))
            sand[ny][nx] += sand[cy][cx] / 100;
        else remainSand += sand[cy][cx] / 100;
        temp += sand[cy][cx] / 100;

        // 2 7 y 7 2
        nx = cx;
        ny = cy + 1;
        if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 7) / 100;
        else remainSand += (sand[cy][cx] * 7) / 100;
        temp += (sand[cy][cx] * 7) / 100;

        ny = cy - 1;
        if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 7) / 100;
        else remainSand += (sand[cy][cx] * 7) / 100;
        temp += (sand[cy][cx] * 7) / 100;
        // -----------------------------------------
        ny = cy + 2;
        if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 2) / 100;
        else remainSand += (sand[cy][cx] * 2) / 100;
        temp += (sand[cy][cx] * 2) / 100;
        ny = cy - 2;
        if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 2) / 100;
        else remainSand += (sand[cy][cx] * 2) / 100;
        temp += (sand[cy][cx] * 2) / 100;

        // 5
        nx = cx + dx[dir] * 2;
        ny = cy;
        if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 5) / 100;
        else remainSand += (sand[cy][cx] * 5) / 100;
        temp += (sand[cy][cx] * 5) / 100;

        // 10 a 10
        nx = cx + dx[dir];
        ny = cy + 1;
        if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 10) / 100;
        else remainSand += (sand[cy][cx] * 10) / 100;
        temp += (sand[cy][cx] * 10) / 100;
        ny = cy - 1;
        if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 10) / 100;
        else remainSand += (sand[cy][cx] * 10) / 100;
        temp += (sand[cy][cx] * 10) / 100;

        ny = cy;
        if (range(ny, nx)) sand[ny][nx] += sand[cy][cx] - temp;
        else remainSand += sand[cy][cx] - temp;
        sand[cy][cx] = 0;
    }
    public static boolean range(int ny, int nx) {
        return ny >= 0 && nx >= 0 && ny < N && nx < N;
    }
}

/* C++
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
int N, remainSand = 0;
//일단 토네이도의 이동을 달팽이 맵으로 구현하기


int dy[4] = { 0, 1, 0, -1 }; // 좌, 하, 우, 상 순서
int dx[4] = { -1, 0, 1, 0 };
int sand[499][499] = { 0, };
bool range(int ny, int nx) {
	return ny >= 0 && nx >= 0 && ny < N && nx < N;
}

void moveVertical(int cy, int cx, int dir) {
	int temp = 0;
	int ny = cy - dy[dir];
	 // 1 x 1
	int nx = cx + 1;
	if (range(ny, nx)) sand[ny][nx] += sand[cy][cx] / 100;
	else remainSand += sand[cy][cx] / 100;
	temp += sand[cy][cx] / 100;

	nx = cx - 1;
	if (range(ny, nx))
	sand[ny][nx] += sand[cy][cx] / 100;
	else remainSand += sand[cy][cx] / 100;
	temp += sand[cy][cx] / 100;

	// 2 7 y 7 2
	ny = cy;
	nx = cx + 1;
	if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 7) / 100;
	else remainSand += (sand[cy][cx] * 7) / 100;
	temp += (sand[cy][cx] * 7) / 100;

	nx = cx - 1;
	if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 7 ) / 100;
	else remainSand += (sand[cy][cx] * 7) / 100;
	temp += (sand[cy][cx] * 7) / 100;
	// -----------------------------------------
	nx = cx + 2;
	if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 2) / 100;
	else remainSand += (sand[cy][cx] * 2) / 100;
	temp += (sand[cy][cx] * 2) / 100;
	nx = cx - 2;
	if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 2) / 100;
	else remainSand += (sand[cy][cx] * 2) / 100;
	temp += (sand[cy][cx] * 2) / 100;

	// 5
	ny = cy + dy[dir] * 2;
	nx = cx;
	if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 5) / 100;
	else remainSand += (sand[cy][cx] * 5) / 100;
	temp += (sand[cy][cx] * 5) / 100;

	// 10 a 10
	ny = cy + dy[dir] * 1;
	nx = cx + 1;
	if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 10) / 100;
	else remainSand += (sand[cy][cx] * 10) / 100;
	temp += (sand[cy][cx] * 10) / 100;

	nx = cx - 1;
	if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 10) / 100;
	else remainSand += (sand[cy][cx] * 10) / 100;
	temp += (sand[cy][cx] * 10) / 100;
	nx = cx;
	if (range(ny, nx)) sand[ny][nx] += sand[cy][cx] - temp;
	else remainSand += sand[cy][cx] - temp;
	sand[cy][cx] = 0;
}

void moveHorizon(int cy, int cx, int dir) {
		int temp = 0;
		int nx = cx - dx[dir];
		// 1 x 1
		int ny = cy + 1;
		if (range(ny, nx)) sand[ny][nx] += sand[cy][cx] / 100;
		else remainSand += sand[cy][cx] / 100;
		temp += sand[cy][cx] / 100;

		ny = cy - 1;
		if (range(ny, nx))
			sand[ny][nx] += sand[cy][cx] / 100;
		else remainSand += sand[cy][cx] / 100;
		temp += sand[cy][cx] / 100;

		// 2 7 y 7 2
		nx = cx;
		ny = cy + 1;
		if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 7) / 100;
		else remainSand += (sand[cy][cx] * 7) / 100;
		temp += (sand[cy][cx] * 7) / 100;

		ny = cy - 1;
		if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 7) / 100;
		else remainSand += (sand[cy][cx] * 7) / 100;
		temp += (sand[cy][cx] * 7) / 100;
		// -----------------------------------------
		ny = cy + 2;
		if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 2) / 100;
		else remainSand += (sand[cy][cx] * 2) / 100;
		temp += (sand[cy][cx] * 2) / 100;
		ny = cy - 2;
		if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 2) / 100;
		else remainSand += (sand[cy][cx] * 2) / 100;
		temp += (sand[cy][cx] * 2) / 100;

		// 5
		nx = cx + dx[dir] * 2;
		ny = cy;
		if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 5) / 100;
		else remainSand += (sand[cy][cx] * 5) / 100;
		temp += (sand[cy][cx] * 5) / 100;

		// 10 a 10
		nx = cx + dx[dir] * 1;
		ny = cy + 1;
		if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 10) / 100;
		else remainSand += (sand[cy][cx] * 10) / 100;
		temp += (sand[cy][cx] * 10) / 100;
		ny = cy - 1;
		if (range(ny, nx)) sand[ny][nx] += (sand[cy][cx] * 10) / 100;
		else remainSand += (sand[cy][cx] * 10) / 100;
		temp += (sand[cy][cx] * 10) / 100;

		ny = cy;
		if (range(ny, nx)) sand[ny][nx] += sand[cy][cx] - temp;
		else remainSand += sand[cy][cx] - temp;
		sand[cy][cx] = 0;
	}
int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			int curSand;
			cin >> curSand;
			if (curSand != 0) sand[i][j] = curSand;
;		}
	}
	pair <int, int> tornado = make_pair (N / 2, N / 2);
	int cnt = 1;
	int dir = 0;
	int i = 1;
	while (1) {
		if (i == N * 2) break;
		for (int i = 1; i <= cnt; i++) {
			tornado = make_pair(tornado.first + dy[dir], tornado.second + dx[dir]);
			if (tornado.first == 0 && tornado.second == 0) {
				moveHorizon(tornado.first, tornado.second, dir);
				break;
			}
			if (dir % 2 == 0) moveHorizon(tornado.first, tornado.second, dir);
			else moveVertical(tornado.first, tornado.second, dir);
		}
		dir = (dir + 1) % 4;
		if (i % 2 == 0) cnt++;
		i++;
	}
	cout << remainSand;
}

* */
