import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class PossibleArea {
        int r;
        int c;

        public PossibleArea(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ")";
        }
    }

    static class State {
        int r;
        int c;
        int time;
        boolean isGreen;

        public State(int r, int c, int time, boolean isGreen) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.isGreen = isGreen;
        }
    }

    static int N, M ,G, R, ans;
    static int[][] map;
    static List<PossibleArea> possibleAreaList;
    static int[] isSelected;
    static int[] greenSelected;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        possibleAreaList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    possibleAreaList.add(new PossibleArea(i, j));
                }
            }
        }
        
        //배양할 수 있는 모든 땅 중에 G+R개 뽑기
        isSelected = new int[G + R];
        greenSelected = new int[G];
        ans = 0;
        combinationFluidArea(0, 0);
        System.out.println(ans);
    }

    private static void combinationFluidArea(int idx, int depth) {
        if (depth == G + R) {
            //초록색 뽑기
            selectGreen(0, 0);
            return;
        }

        for (int i = idx; i < possibleAreaList.size(); i++) {
            isSelected[depth] = i;
            combinationFluidArea(i + 1, depth + 1);
        }
    }

    private static void selectGreen(int idx, int depth) {
        if (depth == G) {

            spread();
            return;
        }

        for (int i = idx; i < G + R; i++) {
            greenSelected[depth] = isSelected[i];
            selectGreen(i + 1, depth + 1);
        }
    }

    private static void spread() {
        Queue<State> q = new LinkedList<>();
        int[][][] visited = new int[N][M][2]; //0층은 시간을 나타냄. //1층은 상태를 나타냄. 0=빈칸, 1=초록, 2=빨강, 3=꽃

        boolean[] isGreen = new boolean[possibleAreaList.size()];
        for (int green : greenSelected) {
            isGreen[green] = true;
        }

        for (int i = 0; i < isSelected.length; i++) {
            PossibleArea possibleArea = possibleAreaList.get(isSelected[i]);

            if (isGreen[isSelected[i]]) {
                q.offer(new State(possibleArea.r, possibleArea.c, 0, true));
                visited[possibleArea.r][possibleArea.c][0] = 0;
                visited[possibleArea.r][possibleArea.c][1] = 1;
            } else {
                q.offer(new State(possibleArea.r, possibleArea.c, 0, false));
                visited[possibleArea.r][possibleArea.c][0] = 0;
                visited[possibleArea.r][possibleArea.c][1] = 2;
            }
        }

        int flowers = 0;

        while (!q.isEmpty()) {
            State state = q.poll();
            int r = state.r;
            int c = state.c;
            int time = state.time;

            if (visited[r][c][1] == 3) continue;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (0 > nr || nr >= N || 0 > nc || nc >= M || map[nr][nc] == 0) continue;

                if (visited[nr][nc][1] == 0) {
                    visited[nr][nc][0] = time + 1;
                    visited[nr][nc][1] = state.isGreen ? 1 : 2;
                    q.offer(new State(nr, nc, time + 1, state.isGreen));
                } else if (visited[nr][nc][0] == time + 1) {
                    if ((state.isGreen && visited[nr][nc][1] == 2) || (!state.isGreen && visited[nr][nc][1] == 1)) {
                        visited[nr][nc][1] = 3;
                        flowers++;
                    }
                }
            }
        }
        ans = Math.max(ans, flowers);
    }
}
