class Solution {
    public long solution(int n, int[] times) {
        long left = 1;
        long right = 1000000000000000000L;  // 그냥 가능한 시간 중 가장 큰 값을 넣음
        long mid = 0;
        while(left <= right) {
            mid = (left+right)/2;
            long cnt = 0;
            for(int t : times) 
                cnt += mid/t;
            
            if(cnt >= n)
                right = mid-1;
            else 
                left = mid+1;
        }
        return left;
    }
}
