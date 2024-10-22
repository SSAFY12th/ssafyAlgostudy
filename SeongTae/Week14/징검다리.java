import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        
        List<Integer> diff = new ArrayList<>();
        int prev = 0;
        for(int rock : rocks){
            diff.add(rock - prev);
            prev = rock;
        }
        
        diff.add(distance - prev);
        
        int left = Collections.min(diff);
        int right = distance;
        
        while(left <= right){
            int mid = (left + right)/2;
            if(calMinDistance(mid, rocks, n, distance)){
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }
    
    static boolean calMinDistance(int mid, int[] rocks, int n, int distance){
        int removeCnt = 0;
        int prev = 0;
        for(int rock : rocks){
            if(rock - prev < mid){
                removeCnt++;
                if(removeCnt > n) return false;
            } else {
                prev = rock;
            }
        }
        
        if(distance - prev < mid) removeCnt++;
        
        return removeCnt <= n;
    }
}
