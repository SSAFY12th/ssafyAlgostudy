import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
      int left = 100000, right = 0;
      for (int i = 0; i < diffs.length; i++) {
  		right = Math.max(right, diffs[i]);
  		left = Math.min(left, diffs[i]);
  	  }
      int answer = right;
  	  while (left <= right) {
    		long time = times[0];
    		int midlevel = (left + right) / 2;
    		for (int i = 1; i < diffs.length; i++) {
    			if (diffs[i] > midlevel) time += (diffs[i] - midlevel) * (times[i - 1] + times[i]) + times[i];
    			else time += times[i];
    		}
    		if (time <= limit) {
    			if (time >= times[0]) {
    				answer = Math.min(answer, midlevel);
    				right = midlevel - 1;
    			}
    		} else left = midlevel + 1;
  	  }
      return answer;
  }
}
