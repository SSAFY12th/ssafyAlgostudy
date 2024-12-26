/*
1. LRU알고리즘은 참조된 것이 있다면 가장 오랫동안 참조되지 않은 순으로 순서를 변경해야 한다.
2. 캐시사이즈가 가득 차있다면 가장 오랫동안 참조되지 않은 걸 제거 후 저장한다.
*/
import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int ans = 0;

        if (cacheSize == 0) {
            return cities.length * 5;
        }

        List<String> cache = new LinkedList<>();
        for (String city : cities) {
            city = city.toLowerCase();
            // 히트!
            if (cache.contains(city)) {
                ans += 1;
                int idx = cache.indexOf(city);
                cache.remove(idx);
                cache.add(city);
            }
            // 미스!
            else {
                ans += 5;
                if (cache.size() == cacheSize) {
                    cache.remove(0);
                    cache.add(city);
                } else {
                    cache.add(city);
                }
            }
        }

        return ans;
    }
}
