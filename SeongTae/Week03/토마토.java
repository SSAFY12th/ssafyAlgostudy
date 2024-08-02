import java.io.*;
import java.util.*;

public class Main {
    public static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static int N, M;
    public static int[][] arr;
    public static int[] dx = { -1, 1, 0, 0 };
    public static int[] dy = { 0, 0, -1, 1 };
    // 1은 익은 토마토, 0은 안익은 토마토, -1은 토마토가 없는 위치
    public static List<Point> tomatoX = new ArrayList<>();
    public static List<Point> tomatoO = new ArrayList<>();
    public static Queue<Point> queue = new LinkedList<>();
    public static int BFS(int day) {
        while(!queue.isEmpty()){
            int size = queue.size(); // 이것도 아주 중요!
            for(int i = 0; i < size; i++){
                Point tomato = queue.poll();
                int x = tomato.x;
                int y = tomato.y;
                for(int j = 0; j < 4; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if(nx >= 0 && nx < N && ny >= 0 && ny < M && arr[nx][ny] == 0){
                        arr[nx][ny] = 1;
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
            if(queue.size() != 0){ // 이 조건문이 매우 중요!
                day++;
            }
        }
        return day;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int day = 0;
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1) {
                    tomatoO.add(new Point(i, j));
                    queue.offer(new Point(i, j));
                } else if (arr[i][j] == -1) {
                    tomatoX.add(new Point(i, j));
                } 
            }
        }

        day = BFS(day);
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    day = -1;
                    break;
                }
            }
        }
        if (N * M == tomatoO.size() + tomatoX.size()) {
            day = 0;
        }

        System.out.println(day);
    }
}
