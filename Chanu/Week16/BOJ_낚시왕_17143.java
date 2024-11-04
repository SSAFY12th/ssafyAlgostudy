
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek1234 {
	
	static int n;
	static int m;
	static int cnt;
	static Result[][] map;
	static int answer = 0;
	static Queue<int[]> queue = new ArrayDeque<>();
	static int[] di = {0,0,2,1,3};
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static class Result{
		int velocity;
		int direction;
		int power;
		
		public Result(int velocity, int direction, int power) {
			this.velocity = velocity;
			this.direction = direction;
			this.power = power;
		}
		
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cnt = Integer.parseInt(st.nextToken());
		map = new Result[n][m];
		
		for (int i=0; i<cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = di[Integer.parseInt(st.nextToken())];
			int p = Integer.parseInt(st.nextToken());
			map[x][y] = new Result(s, d, p);			
		}
		
		
		for (int i=0; i<m; i++) {
			catchShark(i);
			addShark();
			
			int len = queue.size();
			for (int j=0; j<len; j++) {
				int[] shark = queue.poll();

				int x = shark[0];
				int y = shark[1];
				int s = shark[2];
				int d = shark[3];
				int p = shark[4];
				
				if (d == 0 || d == 2) {
					s = s % ((n-1)*2);
				}else {
					s = s % ((m-1)*2);
				}
				
				for (int k=0; k<s; k++) {
					
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if (nx < 0 || nx >=n || ny < 0 || ny >=m ) {
						d = (d+2) % 4;
						x += dx[d];
					    y += dy[d];
						continue;
					}
					
					x = nx;
					y = ny;
					
				}

				
				if (map[x][y] != null) {
					
					if (map[x][y].power < p) {
						map[x][y].velocity = s;
						map[x][y].direction = d;
						map[x][y].power = p;
					}
					
				}else {
					map[x][y] = new Result(s, d, p);
				}
				
			}
				
			
		}
		
		System.out.println(answer);
	}
	
	static void catchShark(int y) {
		for (int x=0; x<n; x++) {
			if (map[x][y] != null) {
				answer += map[x][y].power;
				map[x][y] = null;
				//System.out.println("=== 상어 냠냠=== "+ y );
				return;
			}
		}
		
	}
	
	static void addShark() {
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (map[i][j] != null) {
					queue.offer(new int[] {i,j,map[i][j].velocity,map[i][j].direction,map[i][j].power});
					map[i][j] = null;
				}
				
			}
		}
	}
	
	

}
