import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RobotCleaner {
    static int N;
    static int M;

    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0}; // 북, 동, 남, 서
    static int[] dy = {0, 1, 0, -1};

    static Queue<int[]> q;

    static int answer;

    public static int turn(int direction){
        direction--;
        if(direction < 0){
            direction = 3;
        }
        return direction;
    }

    public static void BFS(int x, int y, int direction){
        q = new LinkedList<>();
        q.offer(new int[]{x, y, direction});

        while (!q.isEmpty()){
            int[] temp = q.poll();
            int cx = temp[0];
            int cy = temp[1];
            int cd = temp[2];

            if (map[cx][cy] == 0 && !visited[cx][cy]) {
                visited[cx][cy] = true;
                answer++;
            }

            // 청소가 가능한지 판단하는 변수
            // 이게 키임
            boolean cleaned = false;

            for(int i = 0; i < 4; i++){
                cd = turn(cd);
                int nx = cx + dx[cd];
                int ny = cy + dy[cd];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M){
                    if(map[nx][ny] == 0 && !visited[nx][ny]){
                        q.offer(new int[]{nx, ny, cd});
                        cleaned = true;
                        break;
                    }
                }
            }

            // 4 공간 중에 청소 할게 없다 ?
            // 후진 하는거임
            if (!cleaned) {
                int backDir = (cd + 2) % 4;
                int bx = cx + dx[backDir];
                int by = cy + dy[backDir];

                if (bx >= 0 && by >= 0 && bx < N && by < M && map[bx][by] != 1) {
                    q.offer(new int[]{bx, by, cd});
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        int x = sc.nextInt();
        int y = sc.nextInt();

        int direction = sc.nextInt();

        answer = 0;

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                map[r][c] = sc.nextInt();
            }
        }

        BFS(x, y, direction);

        System.out.println(answer);
    }
}
