class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        answer = binarySearch(times, n);
    
        return answer;
    }
    
    public static long binarySearch(int[] times, int n) {
        long left = 0; 
        long right = (long) times[times.length - 1] * n; 
        long mid = 0;

        while (left <= right) {
            mid = (left + right) / 2;

            if (canProcess(mid, times, n)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left; 
    }
    
    public static boolean canProcess(long mid, int[] times, int n) {
        long total = 0;

        // 각 입국 심사대에서 주어진 시간 만큼 처리 가능한 인원 계산
        for (int time : times) {
            total += mid / time; 
        }

        // 처리 가능하면 true 반환
        return total >= n;
    }
}
