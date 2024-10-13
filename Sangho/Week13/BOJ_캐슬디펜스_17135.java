import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    // 맵 크기 사거리
    static int N,M,D;

    static int[][] map;

    static int[] ashePosition;

    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        // 궁수의 포지션을 저장
        ashePosition = new int[3];

        for(int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 궁수 위치 중복 순열로 구현
        DFS(0,0);

        System.out.println(answer);

    }

    public static void DFS(int depth, int start) {
        // 궁수 3명을 뽑는 경우
        if(depth == 3){
            simulate();
            return;
        }

        // 열의 개수 만큼 중복순열로 뽑음
        for(int i = start; i < M; i++){
            ashePosition[depth] = i;
            DFS(depth + 1, i+1);
        }
    }

    // 저장된 궁수 조합으로 시뮬레이션 돌리기
    public static void simulate() {
        // 원본맵 복사
        int[][] newMap = copymap(map);

        int totalKill = 0;

        for(int round = 0; round < N; round++) {
            // 타겟 셋으로 관리 중복되지 않게
            Set<int[]> targets = new HashSet<>();

            // 모든 궁수 포지션 돌아봄
            for(int ashe : ashePosition) {
                // 타겟 찾아오기
                int[] target = findTarget(newMap, ashe);
                if(target != null) {
                    // 타겟 목록에 추가
                    targets.add(target);
                }
            }


            // 타겟을 돌아보며
            for (int[] target : targets) {
                if(newMap[target[0]][target[1]] == 1){
                    totalKill++;
                    newMap[target[0]][target[1]] = 0;
                }
            }

            updateMap(newMap);
        }



        answer = Math.max(answer,totalKill);

    }

    // 한 시점에서 타겟 찾아오기
    public static int[] findTarget(int[][] tempMap, int asheCol){
        int[] target = null;
        int minDist = D + 1;

        for(int i = N - 1; i >= 0; i--) {
            for(int j = 0; j < M; j++) {
                // 적 포착
                if(tempMap[i][j] == 1) {
                    int dist = Math.abs(N - i) + Math.abs(asheCol - j);
                    if(dist <= D) { // 사정거리에 닿는 경우
                        // 타겟된 적보다 가까운적이면 최단거리 갱신하고, 현재 최단거리와 거리가 같다면 가장 왼쪽에있는 적을 타겟
                        if (dist < minDist || (dist == minDist && j < target[1])) {
                            target = new int[]{i, j};
                            minDist = dist;
                        }
                    }
                }
            }
        }

        return target;
    }

    public static void snapShotAshe() {
        for(int i = 0; i < ashePosition.length; i++){
            System.out.println(ashePosition[i]);
        }
        System.out.println(" ");
    }

    public static void snapShot(int[][] map) {
        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }

    // 적 내려오는 메서드임
    static void updateMap(int[][] tempMap) {
        for(int i = N - 1; i > 0; i--) {
            tempMap[i] = tempMap[i - 1].clone();
        }
        // 맨 윗줄 비우기
        Arrays.fill(tempMap[0],0);
    }

    public static int[][] copymap(int[][] currentMap) {
        int[][] newMap = new int[N][M]; // n x m 크기의 새로운 맵 생성

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newMap[i][j] = currentMap[i][j]; // 각 값을 깊은 복사
            }
        }

        return newMap; // 깊은 복사된 새로운 맵 반환
    }

}

// 궁수배치 중복 순열로 뽑기
// 다 뽑으면 시뮬레이션

// 시뮬레이션 - 사거리 계산해서 적 죽이기
// 몇명을 죽였는지 갱신
