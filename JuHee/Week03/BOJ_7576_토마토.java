import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
  
  static int[] dx = {1, -1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static int n;
  static int m;
  static int[][] arr;
  static Queue<int[]> q = new LinkedList<>();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    m = sc.nextInt();
    n = sc.nextInt();
    arr = new int[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        arr[i][j] = sc.nextInt();
        if(arr[i][j] == 1) q.add(new int[]{i, j});
      }
    }

    int result = bfs();
   
    System.out.println(result);
  }

  public static int bfs() {
    while (!q.isEmpty()) {
      int[] num = q.poll();
      int a = num[0];
      int b = num[1];
      for (int i = 0; i < 4; i++) {
        int x = a + dx[i];
        int y = b + dy[i];
        if(x >= 0 && x < n && y >= 0 && y < m) {
          if(arr[x][y] == 0) {
            q.add(new int[]{x, y});
            arr[x][y] = arr[a][b] + 1;
          }
        }
      }
    }
    
    int day = Integer.MIN_VALUE;
    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if(arr[i][j] == 0) return -1;
        day = Math.max(day, arr[i][j]);
      }
    }
    return day - 1;
  }
}
