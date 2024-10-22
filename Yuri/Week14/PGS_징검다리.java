class Solution {
    public int solution(int distance, int[] rocks, int n) {
        
        Arrays.sort(rocks);

        int[] dist = new int[rocks.length+1];
        dist[0] = rocks[0];    // 0~첫번째 돌 사이의 거리
        dist[dist.length-1] = distance - rocks[rocks.length-1];  // 최종 길이~마지막 돌 사이 거리

        for (int i = 1; i < rocks.length; i++)
            dist[i] = rocks[i] - rocks[i-1];    // 돌 사이의 거리를 전부 구해 저장한다.

        int left = 1;
        int right = distance;

        while(left <= right) {

            int mid = (left+right)/2;
            int removeCnt = 0;
            int currdist = 0;

            for(int d : dist) {
                currdist += d;  // 돌을 제거할 때마다 돌 사이의 거리가 늘어나기 때문에 더해줘야함

                if(currdist < mid)
                    removeCnt++;
                else
                    currdist = 0;
            }

            if(removeCnt <= n)
                left = mid+1;
            else
                right = mid -1;
        }
        return right;   // n개 제거하는 경우 중 가장 큰 값 return
    }
}
