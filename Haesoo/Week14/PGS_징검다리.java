import java.util.*;
class Solution {
    public long solution(int distance, int[] rocks, int n) {
        long answer = 0;
        Arrays.sort(rocks);
        long left = 0, right = distance;
        while (left <= right) {
            long mid = (left + right) / 2;
            int cnt = 1;
            int prevIdx = 0;
            for (int i = 0; i < rocks.length; i++) { 
                if (rocks[i] - prevIdx >= mid) {
                    cnt++;
                    prevIdx = rocks[i];
                }
            }
            if (distance - prevIdx >= mid) cnt++;
            if (rocks.length + 2 - cnt <= n) {
                left = mid + 1;
                answer = Math.max(answer, mid);
            } 
            else right = mid - 1;
        }
        return answer;
    }
}
