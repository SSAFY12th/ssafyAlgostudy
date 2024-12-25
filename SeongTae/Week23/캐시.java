import java.util.*;
import java.io.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if(cacheSize == 0) return cities.length * 5;
        
        LinkedHashSet<String> cache = new LinkedHashSet<>();
        for(String city : cities){
            city = city.toLowerCase();
            if(cache.contains(city)){
                cache.remove(city);
                cache.add(city);
                answer += 1;
            } else {
                if(cache.size() >= cacheSize){
                    String oldestCity = cache.iterator().next();
                    cache.remove(oldestCity);
                }
                cache.add(city);
                answer += 5;
            }
        }
        return answer;
    }
}
