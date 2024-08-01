import java.util.*;

class Solution {
    static int answer = 0;
    public void dfs (int [] numbers, int target, int sum, int depth) {
        if (depth == numbers.length) {
            if (sum == target) answer++;
            return;
        }
        dfs (numbers, target, sum + numbers[depth], depth + 1);
        dfs (numbers, target, sum - numbers[depth], depth + 1);
    }
    public int solution(int[] numbers, int target) {
        dfs (numbers, target, 0, 0);
        return answer;
    }
}
