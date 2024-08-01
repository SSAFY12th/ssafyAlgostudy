import java.util.*;
import java.io.*;

public class Main {
    public static class Point{
        private int r;
        private int c;

        Point(int x,int y){
            this.r = x;
            this.c = y;
        }
    }
    public static int max = 0;
    public static int N;
    public static int M;    
    public static int[] dx = {-1,1,0,0};
    public static int[] dy = {0,0,-1,1};
    public static Queue<Point> queue = new LinkedList<>();
    
    static void install_wall(int[][] arr, int depth){
        if(depth == 3){
            int[][] temp = new int[N][M];
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    temp[i][j] = arr[i][j];
                }
            }
            int result = spread_virus(temp);
            max = Math.max(max, result);
            return;
        }
        
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(arr[i][j]== 0){
                    arr[i][j] = 1;
                    install_wall(arr, depth+1);
                    arr[i][j] = 0;
                }
            }
        }
    }
    static int spread_virus(int[][] temp){
        int sum = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(temp[i][j] == 2){
                    queue.offer(new Point(i,j));

                    while(!queue.isEmpty()){
                        Point p =queue.poll();

                        for(int k=0;k<4;k++){
                            int nx = p.r + dx[k];
                            int ny = p.c + dy[k];
                            if(nx>=0 && nx<N && ny>=0 && ny<M && temp[nx][ny] == 0){
                                temp[nx][ny] = 2;
                                queue.offer(new Point(nx, ny));
                            }
                        }
                    }
                }
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(temp[i][j] == 0){
                    sum++;
                }
            }
        }
        return sum;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        install_wall(arr, 0);
        System.out.println(max);
    }
}
