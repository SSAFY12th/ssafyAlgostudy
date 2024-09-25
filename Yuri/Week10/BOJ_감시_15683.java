import java.util.*;
import java.io.*;

public class Main {

    static int N, M, result;
    static int originBlankNum = 0;
    static int[][] map, copyMap;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    // 0: 상, 1: 하, 2: 좌, 3: 우
    static int[][][] cameraDir = {{{}},
            {{0}, {1}, {2}, {3}},                           // 1번
            {{0, 1}, {2, 3}},                               // 2번
            {{0, 3}, {3, 1},{1, 2}, {2, 0}},                // 3번
            {{0, 2, 3}, {0, 1, 3}, {1, 2, 3}, {0, 1, 2}},   // 4번
            {{0, 1, 2, 3}}                                  // 5번
    };
    static List<int[]> cameras = new ArrayList<>();
    static int[][] cameraCombi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        copyMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0)
                    originBlankNum++;
                else if(map[i][j] < 6)
                    cameras.add(new int[] {i, j, map[i][j]});
            }
        }

        result = originBlankNum;
        cameraCombi = new int[cameras.size()][];

        getCombi(0);
        System.out.println(result);

    }

    public static void getCombi(int cnt) {
        if(cnt == cameras.size()) { // 각 카메라의 방향이 조합으로 다 뽑히면
            check();                // cctv 확인
            return;
        }

        for (int i = cnt; i < cameras.size(); i++) {
            int[] nowCamera = cameras.get(i);
            for (int j = 0; j < cameraDir[nowCamera[2]].length; j++) {
                cameraCombi[i] = cameraDir[nowCamera[2]][j];    // 현재 카메라가 갈 수 있는 모든 방향을 조합으로 뽑기.
                getCombi(i+1);
            }
        }
    }

    public static void check() {
        for (int i = 0; i < N; i++) {
            copyMap[i] = map[i].clone();
        }

        // 조합으로 고른 카메라 방향을 하나씩 가져와서 맵 체인지
        // 인덱스 활용해서 cameras에서 카메라 위치 가져오기.
        // 맵 체인지 끝나면 최소값 갱신.

        int changeValue = 0;
        for (int i = 0; i < cameras.size(); i++) {
            int[] nc = cameras.get(i);
            for (int j = 0; j < cameraCombi[i].length; j++) {
                changeValue += mapChange(nc[0], nc[1], cameraCombi[i][j], 7);
            }
        }

        result = Math.min(result, originBlankNum-changeValue);

    }

    public static int mapChange(int r, int c, int dir, int changeValue) {
        int newR = r;
        int newC = c;
        int changeNum = 0;
        while(true) {
            newR += dr[dir];
            newC += dc[dir];
            if(newR >= 0 && newC >= 0 && newR < N && newC < M && copyMap[newR][newC] != 6) {
                if(copyMap[newR][newC] > 0) continue;
                if(copyMap[newR][newC] == changeValue) continue;
                copyMap[newR][newC] = changeValue;
                changeNum++;
            }
            else break;
        }
        return changeNum;
    }
}
