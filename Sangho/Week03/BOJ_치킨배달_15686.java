import java.util.*;

public class 치킨배달 {
    static int N;
    static int M;
    static int answer;
    static int[][] board;
    static List<int[]> homeList;
    static List<int[]> chickenList;
    static boolean[] selected;

    // 도시에 치킨집 M개만 남기는 함수 (조합)
    public static void DFS(int depth, int start) {
        if (depth == M) {
            BFS();
            return;
        }

        // 치킨집 좌표를 돌면서
        for (int i = start; i < chickenList.size(); i++) {
            // 선택되지 않은 치킨집이라면
            if (!selected[i]) {
                selected[i] = true;
                DFS(depth + 1, i + 1);
                selected[i] = false;
            }
        }
    }

    // 치킨집과 가정집의 거리를 구하는 함수
    public static void BFS() {
        int totalDistance = 0;

        // 가정집 위치를 돌면서
        for (int[] home : homeList) {
            int minDistance = Integer.MAX_VALUE;
            // 치킨집을 돌면서
            for (int i = 0; i < chickenList.size(); i++) {
                if (selected[i]) {
                    int[] chicken = chickenList.get(i);
                    int distance = Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
                    minDistance = Math.min(minDistance, distance);
                }
            }
            totalDistance += minDistance;
        }

        answer = Math.min(answer, totalDistance);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        board = new int[N][N];
        homeList = new ArrayList<>();
        chickenList = new ArrayList<>();

        // 가정집 위치와, 치킨집 위치를 구분해서 입력받되
        // list (배열을 담고있는) 로 받음
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();
                if (board[i][j] == 1) {
                    homeList.add(new int[]{i, j});
                } else if (board[i][j] == 2) {
                    chickenList.add(new int[]{i, j});
                }
            }
        }

        // 선택된 치킨집을 담는 배열 visited 배열이라고 생각하면 됨
        selected = new boolean[chickenList.size()];
        // 정답임
        answer = Integer.MAX_VALUE;

        DFS(0, 0);

        System.out.println(answer);
    }
}
