
class Solution {
    
    static int min = Integer.MAX_VALUE;
    
    public int solution(int[] diffs, int[] times, long limit) {
        int low = 1;
        int high = 100000;
        int level = 0;
        
        while (low <= high) {
            level = (low+high)/2;
            
            long time = 0;
            boolean check = true;
            for (int i=0; i<diffs.length; i++) {
                
                if (level >= diffs[i]) {
                    time += times[i];
                }else {
                    
                    if(i == 0){
                        time += (times[i] * (diffs[i]-level))  + times[i];
                    }else {
                        time += ((times[i-1]+times[i])*(diffs[i]-level) + times[i]);
                    }   
                     
                }
                
                if (limit < time) {
                    check = false;
                    break;
                }
                
            }
            
            if (!check) {
                low = level + 1;
                
            }else {
                high = level - 1;
            }
            
            
        }
        
        
        return low;
    }
    
    
}
