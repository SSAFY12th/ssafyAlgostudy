import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    // 가로 세로 사이즈
    static int N,M;

    // 맵 배열
    static int[][] map;

    // 각 방향 !
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    static List<CCTV> cctvList;

    static int answer = Integer.MAX_VALUE;

    public static class CCTV {
        // 좌표, 타입
        int x;
        int y;
        int type;

        CCTV(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        cctvList = new ArrayList<>();

        for(int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] >= 1 && map[r][c] <= 5) {
                    cctvList.add(new CCTV(r,c,map[r][c]));
                }
            }
        }

        DFS(0);

        System.out.println(answer);
    }

    // CCTV 조합 구하는 DFS
    public static void DFS(int depth){
        if(depth == cctvList.size()){
            // 카메라 다 깔았으면 맵 체크
            answer = Math.min(answer,checkBlind());
            return;
        }

        // 현재 깊이의 CCTV 고르기
        CCTV cctv = cctvList.get(depth);

        for(int dir = 0; dir < 4; dir++){
            int[][] backUP = new int[N][M];

            // 현재 맵을 기억하기
            for(int i = 0; i < N; i++){
                backUP[i] = map[i].clone();
            }

            // 현재 씨씨티비를 4가지 방향으로 탐색 (색칠)
            Monitor(cctv, dir);

            // 다음 방향으로 진행
            DFS(depth + 1);

            // 맵 복원하기
            for(int i = 0; i < N; i++){
                map[i] = backUP[i].clone();
            }


        }
    }

    // 카메라 까는 메서드
    // 현재 CCTV 와 방향 입력 받기
    public static void Monitor(CCTV cctv, int direction){
        // 현재 씨씨티비 타입 구분
        int type = cctv.type;

        // 카메라 타입별 탐색
        switch (type) {
            case 1:
                // 한 방향 감시
                Watch(cctv.x,cctv.y,direction);
                break;
            case 2:
                // 바라보는 방향과 뒷 방향 감시
                Watch(cctv.x,cctv.y,direction);
                Watch(cctv.x,cctv.y,(direction+2) % 4);
                break;
            case 3:
                // 바라보는 방향과 한 방향 + 1 까지 감시
                Watch(cctv.x,cctv.y,direction);
                Watch(cctv.x,cctv.y,(direction+1) % 4);
                break;
            case 4:
                // 바라보는 방향 + 1.
                Watch(cctv.x,cctv.y,direction);
                Watch(cctv.x,cctv.y,(direction+1) % 4);
                Watch(cctv.x,cctv.y,(direction+3) % 4);
                break;
            case 5:
                // 모든 방향 다바라봄
                Watch(cctv.x,cctv.y,0);
                Watch(cctv.x,cctv.y,1);
                Watch(cctv.x,cctv.y,2);
                Watch(cctv.x,cctv.y,3);
                break;
        }

    }

    // 해당 좌표에서 해당 방향으로 감시 시작
    public static void Watch(int x, int y, int dir) {
        while (true) {
            x += dx[dir];
            y += dy[dir];

            // 맵을 벗어나거나 벽을 만나면 그만
            if(x < 0 || y < 0 || x >= N || y >= M || map[x][y] == 6) break;

            // 빈 공간을 만나면
            if(map[x][y] == 0) {
                // 감시 된 공간임을 표시
                map[x][y] = 7;
            }
        }
    }

    // 맵에 사각지대 체크 하자
    public static int checkBlind(){
        int count = 0;

        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                if(map[r][c] == 0){
                    count++;
                }
            }
        }

        return count;
    }
}

// 감시 카메라의 모든 방향 조합을 구해서
// 전체 맵을 검증하면 끝
// 백트래킹, 깔았다가 카메라 회수
// 만들어야 하는 메서드
// DFS 조합 구하는 메서드
// 해당 방향으로 카메라 까는 메서드
// 깔린 카메라 회수하는 메서드
