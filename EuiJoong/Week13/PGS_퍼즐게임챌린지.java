class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        int max = 0;
        int min = 100001;
        for (int i = 0; i < diffs.length; i++) {
            max = Math.max(max, diffs[i]);
            min = Math.min(min, diffs[i]);
        }
        
        while (min <= max) {
            int level = (max + min) / 2;
            long mid = calTime(diffs, times, level);
            
            if (mid > limit) {
                min = level + 1;
            } else {
                max = level - 1;
            }
        }
        
        return min;
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
