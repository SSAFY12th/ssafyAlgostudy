/*
mid(거리) 값에 대해 바위를 n개 이하로 제거하며 mid 이상 간격을 유지할 수 있는 mid의 최댓값을 구하는 문제
*/
import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        
        Arrays.sort(rocks);
        
        int left = 0;
        int right = distance;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            int previousDistance = 0;
            int removeCnt = 0;
            
            for (int i = 0; i < rocks.length; i++) {
                int currentDistance = rocks[i] - previousDistance;
                if (currentDistance < mid) {
                    removeCnt++;
                } else {
                    previousDistance = rocks[i];
                }
            }
            
            if (distance - previousDistance < mid) {
                removeCnt++;
            }
            
            if (removeCnt > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return right;
    }
}
