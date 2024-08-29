import java.io.*;
import java.util.*;
 
public class Solution {
 
    static int[][] map = new int[15][12];
    static int n, w,h;
    static int[][] copyMap;
    static int[] result = new int[4];
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static Queue<int[]> queue = new ArrayDeque<>();
    static int min;
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int T = Integer.parseInt(st.nextToken());
 
        for (int test_case=1; test_case <=T; test_case++ ){
 
            st = new StringTokenizer(br.readLine());
 
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            min = Integer.MAX_VALUE;
 
            for (int i=0; i<h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j =0; j<w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            back(0);
 
            if (min == Integer.MAX_VALUE) {
                System.out.println("#"+test_case+" "+0);
            }else {
                System.out.println("#"+test_case+" "+min);
            }
 
 
 
 
        }
    }
 
    static void downWall() {
 
        for (int i=0; i<w; i++) {
            for (int j = h-1; j>=0; j--) {
 
                if (copyMap[j][i] >= 1) {
                    continue;
                }
                int index = j;
                while (index >= 0){
 
                    if (copyMap[index][i] >= 1) {
                        copyMap[j][i] = copyMap[index][i];
                        copyMap[index][i] = 0;
                        break;
                    }
 
                    index--;
 
                }
            }
        }
 
 
    }
 
    static void back(int depth) {
 
        if (depth == n) {
 
            copyMap = new int[15][12];
 
            for (int i=0; i<h; i++) {
                for (int j =0; j<w; j++) {
                    copyMap[i][j] = map[i][j];
                }
            }
 
            for (int i=0; i<n; i++) {
                boolean check = crashWall(result[i]);
                if (!check) {
                    return;
                }
                downWall();
            }
 
            int sum = 0;
            for (int k = 0; k<h; k++) {
                for (int j =0; j<w; j++) {
                    if (copyMap[k][j] != 0) {
                        sum++;
                    }
 
                }
 
            }
 
            min = Math.min(min,sum);
            return;
        }
 
        for (int i=0; i<w; i++) {
            result[depth] = i;
            back(depth+1);
        }
    }
 
    static boolean crashWall(int col) {
 
        queue.clear();
 
        int x = -1;
        int y = -1;
 
 
        for (int i=0; i<h; i++) {
            if (copyMap[i][col] >= 1) {
                x = i;
                y = col;
                break;
            }
        }
 
        if (x == -1 || y == -1) {
            return false;
        }
 
        queue.offer(new int[]{x,y,copyMap[x][y]});
        copyMap[x][y] = 0;
 
        while (!queue.isEmpty()) {
 
            int[] node = queue.poll();
 
            x  = node[0];
            y  = node[1];
            int num = node[2];
 
            if (num == 1 || num == 0) {
                copyMap[x][y] = 0;
                continue;
            }
 
            for (int i=0; i<4; i++) {
 
                int nx = x;
                int ny = y;
 
                for (int j=0; j<num-1; j++) {
 
                    nx += dx[i];
                    ny += dy[i];
 
                    if (nx >=0 && ny >= 0 && nx <h && ny < w && copyMap[nx][ny] != 0) {
 
                        if (copyMap[nx][ny] == 1) {
                            copyMap[nx][ny] = 0;
                        }else {
                            queue.offer(new int[]{nx,ny,copyMap[nx][ny]});
                            copyMap[nx][ny] = 0;
                        }
 
                    }
 
                }
            }
 
        }
 
        return true;
 
 
    }
}
