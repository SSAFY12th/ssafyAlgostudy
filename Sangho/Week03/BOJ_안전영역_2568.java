import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SafeZone {
    static int N;

    static int[][] map;

    static int[][] temp_map;

    static int safe;

    static int max_safe;

    static int max_value;

    static Queue<int[]> q;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void BFS(int r, int c){
        q = new LinkedList<>();

        q.offer(new int[]{r,c});

        while (!q.isEmpty()){
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < N && ny < N){
                    if(temp_map[nx][ny] != 0){
                        temp_map[nx][ny] = 0;
                        q.offer(new int[]{nx,ny});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        map = new int[N][N];

        max_value = Integer.MIN_VALUE;

        max_safe = Integer.MIN_VALUE;

        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                map[r][c] = sc.nextInt();
                if(max_value < map[r][c]){
                    max_value = map[r][c];
                }
            }
        }

        // 물 높이 전체 탐색
        for(int i = 0; i < max_value; i++){
            temp_map = new int[N][N];
            safe = 0;

            for(int r = 0; r < N; r++){
                for(int c = 0; c < N; c++){
                    // 물에 잠김 ㅅㄱ
                    if(map[r][c] <= i){
                        temp_map[r][c] = 0;
                    // 물에 안잠김 ㅅㄱ
                    } else {
                        temp_map[r][c] = 1;
                    }
                }
            }

            for(int r = 0; r < N; r++){
                for(int c = 0; c < N; c++){
                    if(temp_map[r][c] == 1){
                        BFS(r,c);
                        safe++;
                    }
                }
            }
            if (safe > max_safe){
                max_safe = safe;
            }
        }

        System.out.println(max_safe);

    }
}
