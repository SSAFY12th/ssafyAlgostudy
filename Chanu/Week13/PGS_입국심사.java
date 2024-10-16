
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long low = 1;
        long mid = 0;
        long high = 1000000000000000000L;
        
        while (low <= high) {
    
            mid = (low + high)/2;
            long count = 0;
            
           
            for (int i=0; i<times.length; i++) {
                count += mid/times[i];
                
                if (count >= Long.MAX_VALUE) {
                    break;
                }
            }
            
            if (n > count) {
                low = mid + 1;
                
                
            }else {
                high = mid - 1;
            }
            
        }
        
        return low;
    }
}
