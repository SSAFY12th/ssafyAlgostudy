import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static StringTokenizer st;

    static int T, N, W, H;

    static int[][] board;
    static int[] plans;

    static int answer;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());

            // 최소 벽돌 개수
            answer = Integer.MAX_VALUE;

            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            board = new int[H][W]; 

            // 공을 어디서 몇개 떨굴지 정하는 플랜
            plans = new int[N];

            for(int r = 0; r < H; r++){
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < W; c++){
                    board[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            // 중복 순열을 통한 완전 탐색
            DFS(0);

            System.out.println("#"+tc+" "+answer);
        }
    }

    // 중복 순열을 통해 어디서 몇 개의 돌을 떨굴지 정하기
    public static void DFS(int depth){
        // 깊이가 공의 개수에 도달하면
        if(depth == N){
            // 정해진 플랜대로 공을 떨구는 시뮬레이션
            int[][] tempBoard = copyBoard(board); // 원본 보드를 복사
            for(int i = 0; i < N; i++){
                simulate(tempBoard, plans[i]);
                updateBoard(tempBoard);
            }
            answer = Math.min(answer, countRemainingBricks(tempBoard));
            return;
        }

        // 중복 순열 생성
        for(int i = 0; i < W; i++){
            plans[depth] = i;
            DFS(depth + 1);
        }
    }

    // 벽돌이 모두 부서진 후 중력에 의한 보드 업데이트
    public static void updateBoard(int[][] tempBoard){
        for(int c = 0; c < W; c++){
            for(int r = H - 1; r >= 0; r--){
                if(tempBoard[r][c] == 0){
                    int nr = r;
                    while(nr > 0 && tempBoard[nr][c] == 0) {
                        nr--;
                    }
                    tempBoard[r][c] = tempBoard[nr][c];
                    tempBoard[nr][c] = 0;
                }
            }
        }
    }

    // 구슬을 떨어뜨려 벽돌을 깨는 시뮬레이션
    public static void simulate(int[][] tempBoard, int col){
        for(int r = 0; r < H; r++){
            if(tempBoard[r][col] > 0){
                brokenBlock(tempBoard, r, col);
                break;
            }
        }
    }

    // 벽돌 깨기 구현
    public static void brokenBlock(int[][] tempBoard, int x, int y){
        int range = tempBoard[x][y];
        tempBoard[x][y] = 0;

        if(range == 1) return;

        for(int d = 0; d < 4; d++){
            int nx = x;
            int ny = y;

            for(int i = 1; i < range; i++){
                nx += dx[d];
                ny += dy[d];

                if(nx >= 0 && ny >= 0 && nx < H && ny < W && tempBoard[nx][ny] > 0){
                    brokenBlock(tempBoard, nx, ny);
                }
            }
        }
    }

    // 남은 벽돌의 개수를 세는 메서드
    public static int countRemainingBricks(int[][] tempBoard){
        int count = 0;
        for(int r = 0; r < H; r++){
            for(int c = 0; c < W; c++){
                if(tempBoard[r][c] > 0){
                    count++;
                }
            }
        }
        return count;
    }

    // 보드 복사
    public static int[][] copyBoard(int[][] original){
        int[][] newBoard = new int[H][W];
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                newBoard[i][j] = original[i][j];
            }
        }
        return newBoard;
    }
}
