
import java.util.*;
import java.lang.Math;

class Solution {
    
    public int solution(String s) {
        
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int min = Integer.MAX_VALUE;
        int len = s.length();
        
        for (int i=1; i<=len/2; i++) {
            
            stack.clear();
            sb.setLength(0);
            int count = 1;
            
            for (int j=0; j < len; j+=i) {
                
                String str = "";
                
                // 범위를 넘어갔을 때
                if (j+i >= len) {
                    str = s.substring(j);
                    
                }else {
                    str = s.substring(j,j+i);
                }
                
                if (stack.isEmpty()) {
                    stack.add(str);
                    continue;
                }
                
                if (stack.peek().equals(str)) {
                    count++;
            
                }else {
                    
                    if (count > 1) {
                        stack.add(String.valueOf(count));
                    }
                    
                    count = 1;
                    stack.add(str);
                }
                
            }
            
            
            if (count != 1) {
                stack.add(String.valueOf(count));
            }
            
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
                 
            min = Math.min(sb.length(), min);
         
            
        }
        
        if (min == Integer.MAX_VALUE) {
            min = 1;
        }
        
        return min;
    }
    
    
}
