import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
  
        int answer = 0;
  
        // 대소문자 구분을 하지 않기 위해 전부 소문자로 변경
        for (int i = 0; i < cities.length; i++)
            cities[i] = cities[i].toLowerCase();
  
        ArrayList<String> cache = new ArrayList<>();
  
        for (String s : cities) {
            if(!cache.contains(s)) {    // 만약 캐시에 해당 도시가 없다면
                answer += 5;            // miss로 실행시간 +5
                cache.add(s);           // 캐시에 추가
                if(cache.size() > cacheSize)        // 캐시 사이즈보다 값이 많아지면
                    cache.remove(0);          // 캐시에서 삭제
            } else {                    // 캐시에 해당 도시가 있다면
                answer += 1;            // hit으로 실행시간 +1
                cache.remove(s);        // 기존 캐시에 있던 값을 제일 앞으로 가져옴
                cache.add(s);
            }
        }
  
        return answer;
    }
}
