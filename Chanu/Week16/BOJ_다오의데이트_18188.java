
import java.io.*;
import java.util.*;

public class Baek18188 {
	
	static char[][] map;
	static boolean[][] visited;
	static int n;
	static int m;
	static int cnt;
	static int[] dao = new int[2];
	static int[] diz = new int[2];
	static List<char[]> move = new ArrayList<>();
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		visited = new boolean[n][m];
		
		for (int i=0; i<n; i++) {
			 st = new StringTokenizer(br.readLine());
			 String str = st.nextToken();
			for (int j=0; j<m; j++) {
				map[i][j] = str.charAt(j);
				
				if (map[i][j] == 'D') {
					dao[0] =i;
					dao[1] =j;
					
				}else if (map[i][j] == 'Z') {
					diz[0] =i;
					diz[1] =j;
				}
				
			}
		}
		
		st = new StringTokenizer(br.readLine());
		cnt = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<cnt; i++) {
			st = new StringTokenizer(br.readLine());
			move.add(new char[] {st.nextToken().charAt(0),st.nextToken().charAt(0)});
			
		}
		

		dfs(dao[0], dao[1], "", 0);
		System.out.println("NO");

	}
	
	
	static void dfs(int x, int y, String result, int depth) {
		
		if (x == diz[0] && y == diz[1]) {
			System.out.println("YES");
			System.out.println(result);
			System.exit(0);
			return;
		}
		
		if (depth == cnt) {
			return;
		}

		
		char[] arr = move.get(depth);
		
		for (int d=0; d<2; d++) {
			int nx = x;
			int ny = y;
			char w=' ';
			
			if (arr[d] == 'W') {
				
				nx += dx[0];
				ny += dy[0];
				w = 'W';
			}else if (arr[d] == 'A') {
				nx += dx[3];
				ny += dy[3];
				w = 'A';
				
			}else if (arr[d] == 'S') {
				nx += dx[2];
				ny += dy[2];
				w = 'S';
				
			}else if (arr[d] == 'D') {
				nx += dx[1];
				ny += dy[1];
				w = 'D';
				
			}
			
			if (nx < 0 || nx >=n || ny <0 || ny >=m || map[nx][ny] == '@' ) {
				continue;
			}
			
			
			dfs(nx, ny, result+w,depth+1);
			
			
		}
			
		
		
	}
	
	

}
