import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;

    static int N;
    static int sharkSize = 2;

    // 아기상어가 먹는데 걸리는 시간
    static int time = 0;

    // 아기상어가 먹은 물고기 개수
    static int count = 0;

    static int[][] board = new int[20][20];

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static boolean[][] visited;

    static Queue<Fish> q;

    static class Fish {
        int x;
        int y;
        int dist;

        Fish(int x,int y,int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }


    public static Fish BFS(int r, int c){
        q = new LinkedList<>();

        q.add(new Fish(r,c,0));
        visited[r][c] = true;

        Fish target = null;
        int minDistance = Integer.MAX_VALUE;

        while (!q.isEmpty()){
            Fish fish = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = fish.x + dx[i];
                int ny = fish.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || board[nx][ny] > sharkSize) continue;

                visited[nx][ny] = true;

                // 먹을 수 있는 물고기 발견했을때
                if(board[nx][ny] != 0 && board[nx][ny] < sharkSize){
                    // 현재 닿을 수 있는 물고기 중에 최소인가 ?
                    if(fish.dist + 1 < minDistance){
                        minDistance = fish.dist + 1;
                        target = new Fish(nx,ny, fish.dist + 1);
                    // 최소와 같다면
                    } else if (fish.dist + 1 == minDistance){
                        // 더 위에 있거나, 높이가 같다면, 가장 왼쪽 물고기 타게팅
                        if(nx < target.x || (nx == target.x && ny < target.y)){
                            target = new Fish(nx,ny,fish.dist + 1);
                        }
                    }
                }

                q.add(new Fish(nx,ny, fish.dist + 1));

            }
        }

        return target;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        // 아기 상어의 좌표이다.
        int sharkX = 0;
        int sharkY = 0;

        for(int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++){
                board[r][c] = Integer.parseInt(st.nextToken());
                if(board[r][c] == 9){
                    sharkX = r;
                    sharkY = c;
                    // 아기상어 초기위치 비워주자
                    board[r][c] = 0;
                }
            }
        }

        while (true) {
            visited = new boolean[N][N];

            Fish target = BFS(sharkX,sharkY);

            if(target == null) break;

            sharkX = target.x;
            sharkY = target.y;
            time += target.dist;
            board[sharkX][sharkY] = 0;

            count++;

            if(count == sharkSize) {
                sharkSize++;
                count = 0;
            }
        }

        System.out.println(time);

    }
}

// 설계

// 맵사이즈 N 물고기 마리수 M
// 아기 상어 크기 관리 변수 int sharkSize
// 물고기 맵 N * N
// 상하좌우 인접한 한 칸 BFS 델타, 큐, 방문 배열
// 근데 일반적인 BFS는 안되겠네 ?

// 물고기 먹는 규칙
// 1. 아기상어의 크기가 물고기 보다 커야함
// 2
