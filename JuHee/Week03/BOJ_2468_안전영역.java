import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

  static int[] dx = {1, -1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static int n;
  static int[][] arr;
  static boolean[][] visited;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    arr = new int[n][n];

    int maxHeight = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        arr[i][j] = sc.nextInt();
        maxHeight = Math.max(maxHeight, arr[i][j]);
      }
    }

    int maxSafeAreas = 0;
    for (int h = 0; h <= maxHeight; h++) {
      visited = new boolean[n][n];
      int safeAreas = 0;

      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (arr[i][j] > h && !visited[i][j]) {
            bfs(i, j, h);
            safeAreas++;
          }
        }
      }

      maxSafeAreas = Math.max(maxSafeAreas, safeAreas);
    }

    System.out.println(maxSafeAreas);
  }

  public static void bfs(int x, int y, int h) {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{x, y});
    visited[x][y] = true;

    while (!q.isEmpty()) {
      int[] current = q.poll();
      int a = current[0];
      int b = current[1];

      for (int i = 0; i < 4; i++) {
        int nx = a + dx[i];
        int ny = b + dy[i];

        if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
          if (!visited[nx][ny] && arr[nx][ny] > h) {
            q.add(new int[]{nx, ny});
            visited[nx][ny] = true;
          }
        }
      }
    }
  }
}
