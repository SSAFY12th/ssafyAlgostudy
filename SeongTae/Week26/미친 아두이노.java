import java.util.*;
import java.io.*;
public class Main {
	static int R,C;
	static char[][] board;
	static int[][] AduinoCnt;
	static int[] dr = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int[] dc = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
	static Position My;
	static List<Position> CrazyAduinos;
	static int[] nums;
	static class Position {
		int r;
		int c;
		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		CrazyAduinos = new ArrayList<>();
		board = new char[R][C];
		AduinoCnt = new int[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = s.charAt(j);
				if(board[i][j] == 'I') My = new Position(i,j);
				else if(board[i][j] == 'R') {
					CrazyAduinos.add(new Position(i,j));
				}
			}
		}
		
		String s = br.readLine();
		nums = new int[s.length()];
		for(int i = 0; i < nums.length; i++) {
			nums[i] = s.charAt(i) - '0';
		}
		
		for(int i = 0; i < nums.length; i++) {
			if(!moveMyAduino(nums[i]) || !moveCrazyAduino()) {
				System.out.println("kraj "+ (i + 1));
				return;
			}
		}
		
		printMap();
	}
	
	static boolean moveMyAduino(int d) {
		// 내 아두이노의 이동 로직 구현
		int nr = My.r + dr[d];
		int nc = My.c + dc[d];
		if(board[nr][nc] != 'R') {
			board[My.r][My.c] = '.';
			My.r = nr;
			My.c = nc;
			board[My.r][My.c] = 'I';
		} else if(board[nr][nc] == 'R') return false;
		return true;
	}
	
	static boolean moveCrazyAduino() {
		for(int i = 0; i < R; i++) {
	        Arrays.fill(AduinoCnt[i], 0);  // AduinoCnt 배열 초기화 추가
	    }
	    for(Position p : CrazyAduinos) {
	        board[p.r][p.c] = '.';
	    }
	    
	    for(Position CA : CrazyAduinos) {
	        int minD = Integer.MAX_VALUE;
	        int dir = 0;
	        for(int d = 1; d <= 9; d++) {
	            int nr = CA.r + dr[d];
	            int nc = CA.c + dc[d];
	            int checkD = Math.abs(My.r - nr) +  Math.abs(My.c - nc);
	            if(minD > checkD) {
	                minD = checkD;
	                dir = d;
	            }
	        }
	        
	        int nr = CA.r + dr[dir];
	        int nc = CA.c + dc[dir];
	        if(board[nr][nc] == 'I') {
	            return false;
	        }
	        CA.r = nr;
	        CA.c = nc;
	        AduinoCnt[nr][nc]++;  // 새 위치에만 카운트 증가
	    }
		
		// 미친 아두이노가 2개 이상인 위치 찾아서 해당 영역의 아두이노 제거
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(AduinoCnt[i][j] > 1) {
					for(int k = CrazyAduinos.size() - 1; k >= 0; k--) {
						Position p = CrazyAduinos.get(k);
						if(p.r == i && p.c == j) {
							CrazyAduinos.remove(p);
						}
					}
					AduinoCnt[i][j] = 0;
				}
			}
		}
		
		// 제거 후 다시 아두이노 세팅
		for(Position p : CrazyAduinos) {
			board[p.r][p.c] = 'R';
		}
		
		return true;
	}
	
	static void printMap() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}
