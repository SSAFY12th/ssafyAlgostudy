import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N, M;
    static int result = 0;
    static int[][] map;
    static List<int[]> blank = new ArrayList<>();
    static List<int[]> virus = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    blank.add(new int[]{i, j});
                } else if (map[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
            }
        }

        comb(0, 0, new int[3]);
        System.out.println(result);
    }

    public static void comb(int start, int depth, int[] selected) {
        if (depth == 3) {

            int[][] tempMap = copyMap();
            for (int i : selected) {
                int[] point = blank.get(i);
                tempMap[point[0]][point[1]] = 1;
            }

            result = Math.max(result, getSafeArea(tempMap));
            return;
        }

        for (int i = start; i < blank.size(); i++) {
            selected[depth] = i;
            comb(i + 1, depth + 1, selected);
        }
    }

    public static void spreadVirus(int[][] tempMap) {
        Queue<int[]> q = new LinkedList<>();
        for (int[] v : virus) {
            q.offer(v);
        }

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int x = pos[0];
            int y = pos[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && tempMap[nx][ny] == 0) {
                    tempMap[nx][ny] = 2;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }


    public static int getSafeArea(int[][] tempMap) {
        spreadVirus(tempMap);
        int safeArea = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempMap[i][j] == 0) {
                    safeArea++;
                }
            }
        }
        return safeArea;
    }

    public static int[][] copyMap() {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp[i] = map[i].clone();
        }
        return temp;
    }
}
