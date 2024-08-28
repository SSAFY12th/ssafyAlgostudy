import java.util.*;
import java.io.*;

public class Solution {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N, W, H, min;
	static int[][] map;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			min = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			nums = new int[N];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			Perm(0);
			System.out.println("#" + test_case + " "+min);
		}
	}
	static void Perm(int cnt) {
		if (cnt == N) {
			boom();
			return;
		}

		for (int i = 0; i < W; i++) {
			nums[cnt] = i;
			Perm(cnt + 1);
		}
	}

	static void boom() {
	    int[][] temp = new int[H][W];
	    for (int i = 0; i < H; i++) {
	        temp[i] = map[i].clone();
	    }
	    for (int i = 0; i < N; i++) {
	        int fall_position = nums[i];
	        for (int j = 0; j < H; j++) {
	            if (temp[j][fall_position] != 0) {
	                spread(temp, j, fall_position);
	                clean(temp);
	                break;
	            }
	        }
	    }
	    update(temp);
	}

	static void spread(int[][] temp, int SR, int SC) {
	    Queue<int[]> queue = new ArrayDeque<>();
	    boolean[][] visited = new boolean[H][W];
	    queue.offer(new int[] {SR, SC, temp[SR][SC]});
	    visited[SR][SC] = true;
	    temp[SR][SC] = 0;

	    while (!queue.isEmpty()) {
	        int[] cur = queue.poll();
	        int r = cur[0];
	        int c = cur[1];
	        int range = cur[2];
	        for (int d = 0; d < 4; d++) {
	            for (int i = 1; i < range; i++) {
	                int nr = r + dr[d] * i;
	                int nc = c + dc[d] * i;
	                if (check(nr, nc) && !visited[nr][nc]) {
	                    queue.offer(new int[] {nr, nc, temp[nr][nc]});
	                    visited[nr][nc] = true;
	                    temp[nr][nc] = 0;
	                }
	            }
	        }
	    }
	}

	static void clean(int[][] temp) {
		Stack<Integer> stack = new Stack<>();
	    for (int i = 0; i < W; i++) {
	        for (int j = 0; j < H; j++) {
	            if (temp[j][i] != 0) {
	                stack.push(temp[j][i]);
	                temp[j][i] = 0;
	            }
	        }
	        int size = stack.size();
	        for (int j = 0; j < size; j++) {
	            temp[H - 1 - j][i] = stack.pop();
	        }
	    }
	}
	static boolean check(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
	static void update(int[][] temp) {
		int count = 0;
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				if(temp[i][j] != 0) {
					count++;
				}
			}
		}
		min = Math.min(min, count);
	}
}
