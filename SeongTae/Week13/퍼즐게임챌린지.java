public class 퍼즐게임챌린지 {
	class Solution {
	    public int solution(int[] diffs, int[] times, long limit) {
	        int answer = 0;
	        int left = 1;
	        int right = 100000;
	        while(left < right){
	            int mid = (left + right) / 2;
	            if(cal(mid,diffs,times) <= limit){
	               right = mid; 
	            } else if(cal(mid,diffs,times) > limit){
	                left = mid + 1;
	            }
	        }
	        answer = right;
	        return answer;
	    }
	    
	    public long cal(int level,int[] diffs, int[] times){
	        long sum = 0;
	        for(int i = 0;i < diffs.length ;i++){
	            if(diffs[i] <= level){
	                sum += times[i];
	            } else {
	                sum += (diffs[i] - level) * (times[i] + times[i-1]) + times[i];
	            }
	        }
	        return sum;
	    }
	}
}
