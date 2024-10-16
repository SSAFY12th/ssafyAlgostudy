class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        int max = 1;
        int min = 100000;
        
        while (max <= min) {
            int level = (max + min) / 2;
            long mid = calTime(diffs, times, level);
            
            if (mid > limit) {
                max = level + 1;
            } else {
                min = level - 1;
            }
        }
        
        return max;
    }
    
    public long calTime(int[] diffs, int[] times, int level) {
        
        long res = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                res += times[i];
            } else if (diffs[i] > level) {
                res += (diffs[i] - level) * (times[i] + times[i - 1]) + times[i];
            }
        }
        
        return res;
    }
}
