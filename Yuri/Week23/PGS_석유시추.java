import java.util.*;

class Solution {

    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] land;
    static int count;
    static int max = 0;
    static boolean[] visited;
    static ArrayList<Integer> size = new ArrayList<>();

    public int solution(int[][] arr) {

        land = arr;

        // 0번과 1번 인덱스는 사용 x
        size.add(0);
        size.add(0);

        int num = 2;

        // 번호매기기 및 각 번호 별 석유 개수 알아내기.
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if(land[i][j] == 1) {
                    land[i][j] = num;
                    count = 1;
                    dfs(i, j, num++);
                    size.add(count);
                }
            }
        }

      // 모든 열에서 아래로 내려가보면서 최대값 찾기
        for (int j = 0; j < land[0].length; j++)  {
            visited = new boolean[num];
            int sum = 0;
            for (int i = 0; i < land.length; i++) {
                if(land[i][j] != 0 && !visited[land[i][j]]) {
                    visited[land[i][j]] = true;
                    sum += size.get(land[i][j]);
                }
            }
            max = Math.max(max, sum);
        }

        return max;
    }

    public static void dfs(int r, int c, int num) {
        int newR, newC;
        for (int i = 0; i < 4; i++) {
            newR = r + dr[i];
            newC = c + dc[i];
            if(newR >= 0 && newC >= 0 && newR < land.length && newC < land[0].length && land[newR][newC] == 1) {
                land[newR][newC] = num;
                count++;
                dfs(newR, newC, num);
            }
        }
    }
}
