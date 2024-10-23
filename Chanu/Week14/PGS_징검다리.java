import java.util.*;
  

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        int low = 1;
        int high = distance;
        
        while (low <= high) {
            int mid = (low+high)/2;
            
            int cnt = getCount(distance, mid, rocks);
            
            if (cnt > n) {
                high = mid -1;
            }else if (cnt <= n) {
                answer = mid;
                low = mid + 1;
            }
        
            
        }
        
        
        return answer;
    }
    
    static int getCount(int distance, int mid, int[] rocks) {
        
        int b = 0;
        int end = distance;
        int count = 0;
        
        for (int i=0; i < rocks.length; i++) {
            
            if (rocks[i] - b < mid) {
                count++;
                continue;
            }
            
            b = rocks[i];
        }
        
        if (end - b < mid) {
            count++;
        }
             
    
        return count;    
        
    }
}
