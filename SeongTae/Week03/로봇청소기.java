import java.util.*;
import java.io.*;

public class Main{
	static class Point{
		int x;
		int y;
		public Point(int r,int c) {
			this.x = r;
			this.y = c;
		}
	}
	public static int[][] arr;
	public static int N , M, r, c, d;
	public static int[] dr = {-1,0,1,0};
	public static int[] dc = {0,1,0,-1};
	public static int count = 0;
	public static boolean[][] visited;
	public static List<Point> wall = new ArrayList<>();
	public static Queue<Point> clear = new LinkedList<>();
	public static void BFS(int r, int c, int d) {
		clear.offer(new Point(r,c));
		visited[r][c] = true;
		count++;
		
		while(!clear.isEmpty()) {
			Point p = clear.poll();
			int x = p.x;
			int y = p.y;
            boolean moved = false; // 4방향 탐색이 안되었을 경우를 위한 변수
            for(int i=0;i<4;i++) {
                d = (d+3)%4;
								int nr = x + dr[d];
								int nc = y + dc[d];                
                if(nr >=0 && nr <N && nc >=0 && nc < M && !visited[nr][nc] && arr[nr][nc] == 0) {
									clear.offer(new Point(nr,nc));
					        visited[nr][nc] = true;
					        count++;
                  moved = true;
                  break;		

				}
			}
            if(!moved){
                int back_x = x + dr[(d+2)%4];
                int back_y = y + dc[(d+2)%4];
                if(back_x >=0 && back_x <N && back_y >=0 && back_y <M && arr[back_x][back_y] != 1) {
                    clear.offer(new Point(back_x, back_y)); // back할때는 벽이 없는지만 확인
                } else {
                    return;
                }
            }
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M];
		st = new StringTokenizer(br.readLine()); 
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) {
					wall.add(new Point(i,j));
				}
			}
		}
		BFS(r,c,d);
		System.out.println(count);
	}
}
