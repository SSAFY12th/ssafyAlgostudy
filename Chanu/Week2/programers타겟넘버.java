
import java.util.*;

class Solution {
    static int[] numbers;
    static int target;
    static int count = 0;
    static int n;
    
    public int solution(int[] numbers, int target) {
        
        this.numbers = numbers;
        this.target = target;
        n = numbers.length;
        
        dfs(0,0);
        
    
        return count;
    }
    
    static void dfs(int depth, int sum) {
        
        if (depth == n) {
            if (sum == target) {
                count++;
            }
            
            return;
        }
        
        
        dfs(depth+1, sum + numbers[depth]  );
        dfs(depth+1, sum - numbers[depth] );
    }
}
