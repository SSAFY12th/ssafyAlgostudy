import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;

        answer = binarySearch(diffs, times, limit);

        return answer;
    }

    // 현재 난이도로 통과가 가능한지 여부 체크
    public static boolean checkSuccess(int[] diffs, int[] times, long limit, int level) {

        long totalTime = 0;

        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                totalTime += times[i];
            } else {
                // i가 1 이상일 때만 times[i-1]에 접근
                if (i > 0) {
                    totalTime += (times[i] + times[i - 1]) * (diffs[i] - level) + times[i];
                } else {
                    // i가 0일 경우에 대한 처리 (필요하다면)
                    totalTime += times[i] * (diffs[i] - level); // 이전 시간을 고려하지 않음
                }
            }
        }


        if (totalTime <= limit) {
            return true;
        } else {
            return false;
        }

    }

    public static int binarySearch(int[] diffs, int[] times, long limit) {

        // 최대값 : 난이도 중에 제일 어려운
        int top = Arrays.stream(diffs).max().orElseThrow();
        int down = 1;
        int middle = 0;

        // 아래가 위를 역전할때까지 반복
        while (down <= top) {

            middle = (top + down) / 2;

            // 성공하면
            if (checkSuccess(diffs, times, limit, middle)) {
                // 고점 낮추기
                top = middle - 1;
                // 실패하면
            } else {
                // 저점 높이기
                down = middle + 1;
            }

        }

        // 얻은 미들 값으로 성공하면
        if (checkSuccess(diffs, times, limit, middle)) {
            // 그대로 리턴
            return middle;
            // 실패했는데 하나 올려서 성공하면
        } else {
            // 하나 올린 값 리턴
            return middle + 1;
        }

    }
}
