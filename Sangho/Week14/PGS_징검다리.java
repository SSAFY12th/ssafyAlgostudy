import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);

        int[] gaps = new int[rocks.length + 1];
        gaps[0] = rocks[0]; // 시작점과 첫 번째 바위 사이 거리
        for (int i = 1; i < rocks.length; i++) {
            gaps[i] = rocks[i] - rocks[i - 1]; // 각 바위 간의 거리
        }
        gaps[rocks.length] = distance - rocks[rocks.length - 1]; // 마지막 바위와 끝점 사이 거리

        // 이진 탐색으로 최소 거리를 찾음
        int left = 0;
        int right = distance;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canMaintainMinDistance(gaps, mid, n)) {
                answer = mid;  // 더 큰 최소 거리 시도
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    // 주어진 최소 거리(mid)를 유지하면서 바위를 제거할 수 있는지 확인
    private boolean canMaintainMinDistance(int[] gaps, int minDistance, int n) {
        int removedRocks = 0;
        int accumulatedGap = 0;

        for (int gap : gaps) {
            accumulatedGap += gap;
            if (accumulatedGap < minDistance) {
                removedRocks++;  // 바위를 제거해 최소 거리를 유지
                if (removedRocks > n) {
                    return false; // 허용된 바위 수보다 많이 제거하면 false
                }
            } else {
                accumulatedGap = 0; // 최소 거리가 충족되면 누적 초기화
            }
        }

        return true;  // 주어진 n 이하로 제거할 수 있음
    }
}
