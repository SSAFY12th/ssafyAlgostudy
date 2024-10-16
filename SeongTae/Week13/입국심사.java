import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;        
        Arrays.sort(times);

        answer = search(n,times);
        
        return answer;
    }
    public long search(int n,int[] times){
        long left = 0;
        long right = (long) times[times.length - 1] * n;
        long mid = 0;
        
        while(left < right){
            mid = (left + right)/2;
            if(compare(mid,times) >= n){
                right = mid;
            } else {
                left = mid + 1;
            } 
        }
        return right;
    }
    
    public long compare(long count, int[] times){
        long result = 0;
        for(int i = 0;i < times.length;i++){
            result += count/times[i];
        }
        return result;
    }
}
