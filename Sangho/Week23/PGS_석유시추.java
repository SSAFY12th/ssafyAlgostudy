import java.util.*;

class Solution {

    static boolean[][] visited;
    static int N, M;
    static int[][] land;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public int solution(int[][] land) {
        this.land = land;
        N = land.length;
        M = land[0].length;
        
        // 덩어리 번호를 기록할 배열
        int[][] group = new int[N][M];
        // 각 덩어리의 크기를 기록할 리스트
        List<Integer> groupSizes = new ArrayList<>();
        int groupId = 1;

        // 덩어리 크기 계산
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    int size = bfs(i, j, groupId, group);
                    groupSizes.add(size);
                    groupId++;
                }
            }
        }

        // 각 열에 대해 해당 열에서 얻을 수 있는 최대 석유량 계산
        int maxOil = 0;
        for (int c = 0; c < M; c++) {
            Set<Integer> visitedGroups = new HashSet<>();
            int totalOil = 0;
            for (int r = 0; r < N; r++) {
                if (land[r][c] == 1 && !visitedGroups.contains(group[r][c])) {
                    totalOil += groupSizes.get(group[r][c] - 1);
                    visitedGroups.add(group[r][c]);
                }
            }
            maxOil = Math.max(maxOil, totalOil);
        }

        return maxOil;
    }

    // BFS로 덩어리 크기 계산
    private int bfs(int x, int y, int groupId, int[][] group) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        group[x][y] = groupId;
        int size = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny] || land[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                group[nx][ny] = groupId;
                q.add(new int[]{nx, ny});
                size++;
            }
        }
        return size;
    }
}
