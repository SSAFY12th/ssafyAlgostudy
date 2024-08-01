class Solution {
    static int ansCnt = 0;
    public int solution(int[] numbers, int target) {

        int[] arr = new int[numbers.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = numbers[i];
        }
        dfs(1, arr[0], arr.length, target, arr);
        dfs(1, -arr[0], arr.length, target, arr);
        int answer = ansCnt;
        return answer;
    }
    public static void dfs(int depth, int total, int endPoint, int target, int[] arr) {
        if (depth == endPoint) {
            if (total == target) {
                ansCnt += 1;
            }
            return;
        }
        dfs(depth + 1, total + arr[depth], endPoint, target, arr);
        dfs(depth + 1, total - arr[depth], endPoint, target, arr);
    }
}