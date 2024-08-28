import java.io.*;
import java.util.*;

public class Solution {

    // 터트리는 순서가 중요하기 때문에, 중복순열을 사용한다.

    static int t, n, w, h, map[][], copyMap[][], blockNum, min;
    static boolean visited[][];
    static int[] p;
    static boolean[] visitedP;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= t; test_case++) {

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new int[h][w];
            copyMap = new int[h][w];
            blockNum = 0;
            p = new int[n];
            visitedP = new boolean[n];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] != 0)
                        blockNum++; // 전체 블럭 개수를 찾는다.
                }
            }

            min = blockNum;
            dfs(0);
            System.out.println("#"+test_case+" "+min);

        }

    }

    static public void dfs(int cnt) {
        if(cnt == n) {
            // 맵 원본을 copyMap에 복사한다.
            for (int j = 0; j < h; j++)
                copyMap[j] = Arrays.copyOf(map[j], map[j].length);
            int boomNum = 0;

            for (int num : p) { // 뽑은 중복순열대로 열을 탐색하여 맨 위 블럭을 터트린다.
                // 현재 열에서 가장 위의 블럭을 찾는다.
                Point block = findTopBlock(num);
                if(block == null)   // 만약 맨 위 블럭이 없다면 (n-1번째까지 전부 0이면) 반복 중지.
                    continue;
                // 가장 위의 블럭 터트리기 (연쇄 반응으로 터지는 모든 블럭을 탐색한 후 전부 터트린다.)
                boomNum += bomb(block);
                // 남은 블럭 전부 아래로 내리기.
                down();
            }

            // 전체 블럭 수에서 터진 블럭 수를 빼내어 최소값과 비교한다.
            min = Math.min(min, blockNum-boomNum);
            return;
        }

        if(min == 0)   // 가지치기 (전부 다 터져 더이상 터질게 없는 경우 더 이상 탐색하지 않는다.)
            return;

        // 중복 순열뽑기
        for (int i = 0; i < w; i++) {
            p[cnt] = i;
            dfs(cnt+1);
        }
    }

    static public void down() { // 열마다 다운 작업을 반복
        for (int i = 0; i < w; i++) {
            down(i);
        }
    }

    static public void down(int c) {    // 해당 열에서 0이 아닌 값들을 밑으로 내린다.
        int index = h-1;
        for (int r = h-1; r >= 0; r--) {    // 밑에서부터 탐색.
            if(copyMap[r][c] != 0) {
                copyMap[index--][c] = copyMap[r][c];
            }
        }

        for (int r = index; r >= 0 ; r--) {
            copyMap[r][c] = 0;
        }
    }

    static public Point findTopBlock(int c) {   // 맨 위 블럭찾기.
        Point p = null;
        for (int r = 0; r < h; r++) {
            if(copyMap[r][c] != 0) {
                p = new Point(r, c, copyMap[r][c]);
                break;
            }
        }
        return p;
    }

    static public int bomb(Point start) {   // 터트리기
        visited = new boolean[h][w];
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        int sum = 0;

        while(!queue.isEmpty()) {
            Point p = queue.poll();
            visited[p.r][p.c] = true;
            int power = p.power;
            copyMap[p.r][p.c] = 0;
            sum++;

            if(power == 1)
                continue;

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < power ; j++) {  // 블럭에 적힌 숫자 -1만큼 상하좌우로 제거됨
                    int moveR = p.r+dr[i]*j;
                    int moveC = p.c+dc[i]*j;

                    if(moveR >= 0 && moveC >= 0 && moveR < h && moveC < w && copyMap[moveR][moveC] != 0 && !visited[moveR][moveC]) {
                        queue.add(new Point(moveR, moveC, copyMap[moveR][moveC]));
                        visited[moveR][moveC] = true;
                    }
                }
            }
        }

        return sum; // 부서진 블럭의 총 개수 return
    }
}

class Point {
    int r, c, power;
    public Point(int r, int c, int power) {
        this.r = r;
        this.c = c;
        this.power = power;
    }
}
