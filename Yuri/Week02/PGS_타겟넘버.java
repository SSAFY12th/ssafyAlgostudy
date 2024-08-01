class Solution {
    
    static int[] numbers;
    static int target;
    static int answer;
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        this.numbers = numbers;
        this.target = target;
        
        dfs(0, 0);
        
        return answer;
    }

    public static void dfs(int cnt, int num) {
        if(cnt == numbers.length) {
            if(num == target) 
                answer++;
            return;
        }
        
        dfs(cnt+1, num-numbers[cnt]);
        dfs(cnt+1, num+numbers[cnt]);
        
    }
}
