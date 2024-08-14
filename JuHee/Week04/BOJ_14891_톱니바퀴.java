import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int K;
	public static boolean[] visited;
	public static int[][] gears = new int[4][8];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for (int i = 0; i < 4; ++i) {
			String input = br.readLine();
			
			for (int j = 0; j < 8; ++j) {
				gears[i][j] = input.charAt(j) - '0';
			}
		}
		
		K = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int gearNumber = Integer.parseInt(st.nextToken()) - 1;
			int direction = Integer.parseInt(st.nextToken());
			
			visited = new boolean[4];
			
			if (direction == 1) {
				turn(gearNumber);
			}
			else if (direction == -1) {
				reverseTurn(gearNumber);
			}
		}
		
		int answer = 0;
		for (int i = 0; i < 4; ++i) {
			if (gears[i][0] == 1) {
				answer += Math.pow(2, i);
			}
		}
		
		System.out.println(answer);
	}
	
	public static void turn(int n) {
		visited[n] = true;
		
		if (n - 1 >= 0 && !visited[n - 1] && gears[n - 1][2] != gears[n][6]) {
			reverseTurn(n - 1);
		}
		
		if (n + 1 < 4 && !visited[n + 1] && gears[n + 1][6] != gears[n][2]) {
			reverseTurn(n + 1);
		}
		
		int temp = gears[n][7];
		
		for (int i = 7; i > 0; i--) {
			gears[n][i] = gears[n][i - 1];
		}
		
		gears[n][0] = temp;		
	}
	
	public static void reverseTurn(int n) {
		visited[n] = true;
		
		if (n - 1 >= 0 && !visited[n - 1] && gears[n - 1][2] != gears[n][6]) {
			turn(n - 1);
		}
		
		if (n + 1 < 4 && !visited[n + 1] && gears[n + 1][6] != gears[n][2]) {
			turn(n + 1);
		}
		
		int temp = gears[n][0];
		
		for (int i = 0; i < 7; i++) {
			gears[n][i] = gears[n][i + 1];
		}
		
		gears[n][7] = temp;
	}
}
