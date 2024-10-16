import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, D;
    static int[][] map, tmpMap;
    static int[] archer = new int[3]; //궁수의 위치(조합)
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        tmpMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                tmpMap[i][j] = map[i][j];
            }
        }

        //궁수 위치 뽑기
        ans = 0;
        putArcher(0, 0);
        System.out.println(ans);
    }

    private static void putArcher(int idx, int depth) {
        if (depth == 3) {
            chooseEnemy();
            return;
        }
        for (int i = idx; i < M; i++) {
            archer[depth] = i;
            putArcher(i + 1, depth + 1);
        }
    }

    private static void chooseEnemy() {

        copyMap(); // 맵복사
        //게임은 맵 안에 적들이 있을 때만 진행됨.
        /*
        1. 궁수가 타겟으로 하는 적위 위치를 정해야 함.
            - 조건1 : 거리가 D 이내중 가장 가까워야 함. minDistance
            - 조건2 : 만약 조건1이 여러개라면 궁수 기준으로 제일 왼쪽에 있는 적
         */
        int totalKill = 0;
        while (existEnemy()) {
            //적의 위치 가져오기
            List<Target> curTargets = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        curTargets.add(new Target(i, j));
                    }
                }
            }
            
            //목표 타겟 담을 자료구조
            Set<Target> willRemoveEnemy = new HashSet<>(); // 같은 타겟을 공격할 수 있기 때문에 Set으로 중복처리
            //각 궁수당 타겟을 정함.
            for (int i = 0; i < archer.length; i++) {
                int minDistance = Integer.MAX_VALUE;
                int minTargetColumn = Integer.MAX_VALUE;
                Target selectedTarget = null;
                for (int j = 0; j < curTargets.size(); j++) {
                    Target target = curTargets.get(j);
                    int distance = calDistance(N, archer[i], target.r, target.c);
                    if (distance <= D) {
                        if (distance < minDistance || (distance == minDistance && target.c < minTargetColumn)) {
                            minDistance = distance;
                            minTargetColumn = target.c;
                            selectedTarget = target;
                        }
                    }
                }
                //타겟이 널이 아닐때만 타겟에 넣어주기
                if (selectedTarget != null) willRemoveEnemy.add(selectedTarget);
                //System.out.println(minDistance);
            }
            //타겟 제거
            for (Target target : willRemoveEnemy) {
                //System.out.println(target);
                map[target.r][target.c] = 0;
                totalKill++;
            }
            
            downEnemy(); //적 내리기
        }
        ans = Math.max(ans, totalKill);
    }

    private static boolean existEnemy() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) return true;
            }
        }
        return false;
    }
    
    private static int calDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    private static void downEnemy() {
        for (int i = N - 1; i > 0; i--) {
            map[i] = Arrays.copyOf(map[i - 1], M);
        }
        Arrays.fill(map[0], 0);
    }

    private static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = tmpMap[i][j];
            }
        }
    }

    static class Target {
        int r;
        int c;

        public Target(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
