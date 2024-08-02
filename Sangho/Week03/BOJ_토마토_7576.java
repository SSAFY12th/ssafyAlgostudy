import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int N;
    public static int M;
    public static int answer;

    public static int[][] board;

    public static Queue<int[]> q;

    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};

    public static void BFS(){
        while(!q.isEmpty()){
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 맵을 벗어나지 않으며
                if(nx >= 0 && ny >= 0 && nx < N && ny < M){
                    // 안익은 토마토를 만났을 경우
                    if (board[nx][ny] == 0){
                        // 기존 익었던 토마토의 숫자 + 1
                        board[nx][ny] = board[x][y] + 1;
                        // 큐에 넣어줌
                        q.add(new int[]{nx,ny});
                    }
                }
            }
        }
    }

    public static boolean check(){
        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                if(board[r][c] == 0){
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();

        q = new LinkedList<>();

        board = new int[N][M];

        // 보드에 값을 입력 받으면서, 익은 토마토 일 경우 큐에 미리 넣어두는거임
        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                board[r][c] = sc.nextInt();
                if(board[r][c] == 1){
                    q.add(new int[]{r, c});
                }
            }
        }

        // BFS를 여러 번 돌려야 한다.
        BFS();

        // 트루가 아니면 (안익은 토마토가 남았으면) 실패 -1
        if(!check()){
            System.out.println(-1);
        } else {
            answer = Integer.MIN_VALUE;
            for(int r = 0; r < N; r++){
                for(int c = 0; c < M; c++){
                    if(answer < board[r][c]){
                        answer = board[r][c];
                    }
                }
            }
            // 실제 걸린 일수는 최댓값에서 1을 뺀 값
            System.out.println(answer-1);
        }
    }
}
