
import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        
        Arrays.sort(files, new Comparator<String>(){
            
            @Override
            public int compare(String o1, String o2) {
                
                // 숫자가 아닌 부분까지 잘라서 대문자든 소문자로 치환 여기에서 결과가 나오면 끝 
                
                int o1Idx = findHeadIdx(o1);
                int o2Idx = findHeadIdx(o2);
                
                String o1Head = o1.substring(0,o1Idx).toLowerCase();
                String o2Head = o2.substring(0,o2Idx).toLowerCase();
                
                int firstR = o1Head.compareTo(o2Head);
                
                if (firstR != 0) {
                    return firstR;
                }
                // 숫자 부분만 찾아서 integer로 치환해서 비교 ... 이것까지 같으면 그냥 순서 유지 
                
                int o1NumIdx = findNumIdx(o1,o1Idx);
                int o2NumIdx = findNumIdx(o2,o2Idx);
                
                int o1Num = Integer.parseInt(o1.substring(o1Idx,o1NumIdx));
                int o2Num = Integer.parseInt(o2.substring(o2Idx,o2NumIdx));
                
                
                return o1Num - o2Num;
            }
            
        });
        
        return files;
    }
    
    static int findHeadIdx(String str) {
        
        for (int i=0; i<str.length(); i++) {
            if (0 <= str.charAt(i) - '0' && str.charAt(i) - '0' <= 9) {
                return i;
            }   
        }
        return -1;
    }
    
     static int findNumIdx(String str, int index) {
        
        for (int i=index; i<str.length(); i++) {
            if (0 > str.charAt(i) - '0' || str.charAt(i) - '0' > 9) {
                return i;
            }   
        }
         
        return str.length();
    }
    
    
}



