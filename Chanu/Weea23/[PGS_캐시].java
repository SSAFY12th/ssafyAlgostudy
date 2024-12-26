
import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        
        int result = 0; 
        
        List<String> queue = new LinkedList<String>();
        
        for(int i=0; i<cities.length; i++){
            
            String word = cities[i].toUpperCase();
            
            if(cacheSize == 0){
                return cities.length*5;
            }
            
            if(queue.size() < cacheSize){
                
                if(queue.contains(word)){
                    queue.remove(word);
                    queue.add(word);
                    result+=1;
                }else{
                    queue.add(word);
                    result+=5;
                }
            }else{
                
                if(queue.contains(word)){
                    queue.remove(word);
                    queue.add(word);
                    result+=1;
                }else{
                    queue.remove(0);
                    queue.add(word);
                    result+=5;
                }
                
            }
          
        }
      
        return result;
    }
}
