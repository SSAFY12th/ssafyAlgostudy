import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        //times에서 최댓값을 가져와 최악의 경우를 만들어야 함.
        Arrays.sort(times);
        long left = 1;
        long right = (long) times[times.length - 1] * n;
        
        while (left < right) {
            long mid = (left + right) / 2;
        
            long human = 0;
            for (int i = 0; i < times.length; i++) {
                human += (mid / times[i]);
            }
            
            if (human >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
