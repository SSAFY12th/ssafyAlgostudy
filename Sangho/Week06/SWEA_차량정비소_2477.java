import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());  // 접수 창구 개수
            int M = Integer.parseInt(st.nextToken());  // 정비 창구 개수
            int K = Integer.parseInt(st.nextToken());  // 방문한 고객의 수
            int A = Integer.parseInt(st.nextToken()) - 1;  // 목표 접수 창구
            int B = Integer.parseInt(st.nextToken()) - 1;  // 목표 정비 창구

            int[] receptionTimes = new int[N];  // 각 접수 창구의 처리 시간
            int[] repairTimes = new int[M];     // 각 정비 창구의 처리 시간
            int[] customerArrivals = new int[K];  // 각 고객의 도착 시간

            // 접수 창구 처리 시간 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                receptionTimes[i] = Integer.parseInt(st.nextToken());
            }

            // 정비 창구 처리 시간 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                repairTimes[i] = Integer.parseInt(st.nextToken());
            }

            // 고객 도착 시간 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                customerArrivals[i] = Integer.parseInt(st.nextToken());
            }

            int[] receptionFinish = new int[K];  // 각 고객이 어떤 접수 창구를 사용했는지 기록
            int[] repairFinish = new int[K];     // 각 고객이 어떤 정비 창구를 사용했는지 기록
            Arrays.fill(receptionFinish, -1);
            Arrays.fill(repairFinish, -1);

            Queue<Integer> receptionQueue = new LinkedList<>();  // 접수 대기 큐
            Queue<Integer> repairQueue = new LinkedList<>();     // 정비 대기 큐

            int[][] receptionWindows = new int[N][2];  // [끝나는 시간, 고객 번호]
            int[][] repairWindows = new int[M][2];     // [끝나는 시간, 고객 번호]
            for (int i = 0; i < N; i++) receptionWindows[i][1] = -1;
            for (int i = 0; i < M; i++) repairWindows[i][1] = -1;

            int time = 0;  // 현재 시간
            int count = 0;  // 처리된 고객 수

            // 고객만큼 돌지 않았거나, 창구들이 비어 있지 않거나, 창구가 꽉차있지 않거나
            while (count < K || !receptionQueue.isEmpty() || !repairQueue.isEmpty() ||
                    anyBusy(receptionWindows) || anyBusy(repairWindows)) {

                // 현재 시간에 도착한 고객을 접수 대기 큐에 추가
                while (count < K && customerArrivals[count] == time) {
                    receptionQueue.offer(count);
                    count++;
                }

                // 접수 창구에서 작업을 끝낸 고객을 정비 대기 큐로 이동
                for (int i = 0; i < N; i++) {
                    if (receptionWindows[i][1] != -1 && receptionWindows[i][0] == time) {
                        repairQueue.offer(receptionWindows[i][1]);
                        receptionWindows[i][1] = -1;  // 창구를 비워줌
                    }
                }

                // 접수 창구가 비어있다면 대기 중인 고객을 접수 창구로 이동
                for (int i = 0; i < N; i++) {
                    if (receptionWindows[i][1] == -1 && !receptionQueue.isEmpty()) {
                        int customer = receptionQueue.poll();
                        receptionWindows[i][0] = time + receptionTimes[i];
                        receptionWindows[i][1] = customer;
                        receptionFinish[customer] = i;  // 고객이 어느 창구를 사용했는지 기록
                    }
                }

                // 정비 창구에서 작업을 끝낸 고객 처리
                for (int i = 0; i < M; i++) {
                    if (repairWindows[i][1] != -1 && repairWindows[i][0] == time) {
                        repairWindows[i][1] = -1;  // 창구를 비워줌
                    }
                }

                // 정비 창구가 비어있다면 대기 중인 고객을 정비 창구로 이동
                for (int i = 0; i < M; i++) {
                    if (repairWindows[i][1] == -1 && !repairQueue.isEmpty()) {
                        int customer = repairQueue.poll();
                        repairWindows[i][0] = time + repairTimes[i];
                        repairWindows[i][1] = customer;
                        repairFinish[customer] = i;  // 고객이 어느 창구를 사용했는지 기록
                    }
                }

                time++;  // 시간 증가
            }

            // 결과 계산
            int result = 0;
            for (int i = 0; i < K; i++) {
                if (receptionFinish[i] == A && repairFinish[i] == B) {
                    result += (i + 1);
                }
            }

            if (result == 0) result = -1;

            System.out.println("#" + tc + " " + result);
        }
    }

    // 창구 바쁜지 체크하는 메서드
    private static boolean anyBusy(int[][] windows) {
        for (int[] window : windows) {
            if (window[1] != -1) return true;
        }
        return false;
    }
}
