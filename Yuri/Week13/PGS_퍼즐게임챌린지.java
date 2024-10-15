class Solution {

    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 0;
        for (int diff : diffs) 
            right = Math.max(right, diff);
        int mid = left;
        int time_prev = 0;
        while(left <= right) {
            mid = (right+left)/2;

            long time = 0;
            for (int i = 0; i < diffs.length; i++) {
                if(diffs[i] <= mid) { // 내 숙련도로 풀이 가능한 경우
                    time += times[i];
                } else {              // 내 숙련도로 풀이 불가능한 경우
                    time += (diffs[i] - mid) * (time_prev + times[i]);
                    time += times[i];
                }
                time_prev = times[i];
            }

            if(time <= limit) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return left;
    }
}
