class Solution {
    public long solution(int n, int[] times) {
        long nn = n, left = times[0], right = times[times.length - 1] * nn, answer = right;
        while (left <= right) {
		      long midtime = (left + right) / 2;
		      long completeCnt = 0;
		      for (int i = 0; i < times.length; i++)
			      completeCnt += midtime / times[i];
		      if (completeCnt >= n) {
			      answer = Math.min(answer, midtime);
			      right = midtime - 1;
		      } else left = midtime + 1;
	      }
        return answer;
    }
}
