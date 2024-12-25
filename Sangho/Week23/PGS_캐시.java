import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5; // 캐시 크기가 0일 경우 모든 요청이 캐시 미스
        }
        
        int totalTime = 0;

        // HashSet : 중복허용x , LinkedList : 순서유지
        // 중복은 안되는데, 순서는 유지하는 자료구조
        LinkedHashSet<String> cache = new LinkedHashSet<>(cacheSize);

        // 도시 배열 순회
        for (String city : cities) {
            city = city.toLowerCase(); // 대소문자 구분 없이 처리

            // 캐시 히트
            if (cache.contains(city)) {
                totalTime += 1;
                // 최근에 사용된 항목으로 갱신하기 위해 제거 후 추가
                cache.remove(city); 
                cache.add(city);
            // 캐시 미스              
            } else {
                totalTime += 5;
                // 캐시가 가득 찼으면
                if (cache.size() == cacheSize) {
                    //  가장 오래된 항목 제거
                    //  첫번쨰가 가장 오래됐음
                    Iterator<String> it = cache.iterator();
                    if (it.hasNext()) {
                        it.next();
                        it.remove();
                    }
                }
                cache.add(city); // 새 항목 추가
            }
        }
        return totalTime;
    }
}
