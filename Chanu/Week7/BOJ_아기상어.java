	
import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] arr;
    static int[][] visited;
    static int cnt =0; // 상어 이동 횟수
    static int sharkSize = 2;
    static int eat =0;
    static int sharkX = -1;
    static int sharkY = -1;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int  minX;
    static int  minY;
    static int minVisit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];


        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for(int j=0; j<n; j++){

                int num = Integer.parseInt(st.nextToken());

                if(num == 9){
                    arr[i][j] = 0;
                    sharkX = i;
                    sharkY = j;
                }else{
                    arr[i][j] = num;
                }

            }
        }

        while (true){

            minVisit =Integer.MAX_VALUE;
            minX =Integer.MAX_VALUE;
            minY =Integer.MAX_VALUE;
            visited = new int[n][n];

            bfs(sharkX,sharkY);

            if(minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE){
                eat++;
                sharkX = minX;
                sharkY = minY;
                arr[minX][minY] = 0;
                cnt += visited[minX][minY];

                if(sharkSize == eat){
                    sharkSize++;
                    eat = 0;
                }
            }else {
                break;
            }

        }

        System.out.println(cnt);

    }

    public static void bfs(int x, int y){

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});

        while (!queue.isEmpty()){

            int[] node = queue.poll();

            x = node[0];
            y = node[1];

            for(int i=0; i <4; i++){

                int nx = x+dx[i];
                int ny = y+dy[i];

                if(0 <= nx && nx < n && 0<=ny && ny < n && visited[nx][ny] == 0
                        && arr[nx][ny] <= sharkSize){

                    visited[nx][ny] = visited[x][y] + 1;

                    if(arr[nx][ny] < sharkSize && arr[nx][ny] != 0){

                        if(minVisit > visited[nx][ny]){
                            minVisit = visited[nx][ny];
                            minX = nx;
                            minY = ny;

                        }else if(minVisit == visited[nx][ny]){

                            if(minX == nx){
                                if(minY > ny){
                                    minX = nx;
                                    minY = ny;
                                }

                            }else if(minX > nx){
                                minX = nx;
                                minY = ny;
                            }

                        }

                    }

                    queue.offer(new int[]{nx,ny});

                }



            }



        }


    }
}
