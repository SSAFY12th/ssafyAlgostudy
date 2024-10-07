import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static double[][] spreadSend = {{0, 0, 0.02, 0, 0},     // 퍼지는 양만큼 미리 저장해두기.
                                {0, 0.1, 0.07, 0.01, 0},
                                {0.05, 0, 0, 0, 0},
                                {0, 0.1, 0.07, 0.01, 0},
                                {0, 0, 0.02, 0, 0}};
    static int[] dr = {0, 1, 0, -1};    // 좌, 하, 우, 상
    static int[] dc = {-1, 0, 1, 0};
    static int[] now;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        now = new int[] {N/2, N/2}; // 현재 위치(시작 위치)
        visited[now[0]][now[1]] = true;

        int dir = 3;    // 방향 시작.
        while(true) {
            if(now[1] == 0 && now[0] == 0) break;   // 0, 0까지 다 돌았으면 break;
            int newDir = (dir+1) % 4;   // 현재 방향에서 왼쪽으로 꺾은 뒤 앞으로 이동
            int newR = now[0]+dr[newDir];
            int newC = now[1]+dc[newDir];
            if(visited[newR][newC]) {   // 만약 이미 방문했었다면 왼쪽으로 꺾지않고 직진한다.
                newR = now[0]+dr[dir];
                newC = now[1]+dc[dir];
            }
            else {
                dir = newDir;   // 방문 안했다면 왼쪽으로 꺾는 것을 확정하여 방향을 변경한다.
            }
            now[0] = newR;
            now[1] = newC;
            visited[newR][newC] = true; // 이동한 칸에 방문처리

            // 이동한 자리에 모래가 있다면?
            // 모래바람!
            if(map[newR][newC] > 0) {
                spread(dir, new int[] {newR, newC});
            }
        }

        System.out.println(result);
    }

    public static void spread(int dir, int[] center) {
        int startR = center[0]-2;
        int startC = center[1]-2;
        int send = map[center[0]][center[1]];   // 모래의 양
        int subSend = 0;
        // 방향에 따라 모래 흩날리는 배열을 알맞게 회전한다.
        if(dir == 0) {
            int r = -1;
            for (int i = startR; i < startR+5; i++) {
                r++;
                int c = -1;
                for (int j = startC; j < startC+5; j++) {
                    c++;
                    // 만약 맵 안이라면, 흩어진 모래를 맵에 더한다.
                    // 흩어진 모래만큼 subSend에 저장한다.
                    if(i >= 0 && j >= 0 && i < N && j < N && spreadSend[r][c] > 0) {
                        map[i][j] += send * spreadSend[r][c];
                        subSend += send * spreadSend[r][c];
                    } else if(spreadSend[r][c] > 0) {   // 만약 맵 밖이라면, result에 흩어진 모래만큼 더한다.
                        result += send * spreadSend[r][c];
                        subSend += send * spreadSend[r][c];
                    }
                }
            }
            // a의 위치에 y-흩어진 모래의 양 만큼 저장한다.
            if(center[1]-1 >= 0)
                map[center[0]][center[1]-1] += send-subSend;
            else
                result += send-subSend;
        }
        else if(dir == 1) {
            int c = 5;
            for (int i = startR; i < startR+5; i++) {
                c--;
                int r = -1;
                for (int j = startC; j < startC+5; j++) {
                    r++;
                    if(i >= 0 && j >= 0 && i < N && j < N && spreadSend[r][c] > 0) {
                        map[i][j] += send * spreadSend[r][c];
                        subSend += send * spreadSend[r][c];
                    } else if(spreadSend[r][c] > 0) {
                        result += send * spreadSend[r][c];
                        subSend += send * spreadSend[r][c];
                    }
                }
            }
            if(center[0]+1 < N)
                map[center[0]+1][center[1]] += send-subSend;
            else
                result += send-subSend;
        }
        else if(dir == 2) {
            int r = 5;
            for (int i = startR; i < startR+5; i++) {
                r--;
                int c = 5;
                for (int j = startC; j < startC+5; j++) {
                    c--;
                    if(i >= 0 && j >= 0 && i < N && j < N && spreadSend[r][c] > 0) {
                        map[i][j] += send * spreadSend[r][c];
                        subSend += send * spreadSend[r][c];
                    } else if(spreadSend[r][c] > 0) {
                        result += send * spreadSend[r][c];
                        subSend += send * spreadSend[r][c];
                    }
                }
            }
            if(center[1]+1 < N)
                map[center[0]][center[1]+1] += send-subSend;
            else
                result += send-subSend;
        }
        else if(dir == 3) {
            int c = -1;
            for (int i = startR; i < startR+5; i++) {
                c++;
                int r = 5;
                for (int j = startC; j < startC+5; j++) {
                    r--;
                    if(i >= 0 && j >= 0 && i < N && j < N && spreadSend[r][c] > 0) {
                        map[i][j] += send * spreadSend[r][c];
                        subSend += send * spreadSend[r][c];
                    } else if(spreadSend[r][c] > 0) {
                        result += send * spreadSend[r][c];
                        subSend += send * spreadSend[r][c];
                    }
                }
            }
            if(center[0]-1 >= 0)
                map[center[0]-1][center[1]] += send-subSend;
            else
                result += send-subSend;
        }
        // 모래가 다 흩어지고 나면, y자리를 0으로 처리한다.
        map[center[0]][center[1]] = 0;
    }
}
