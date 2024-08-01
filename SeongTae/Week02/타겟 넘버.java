class Solution {
    public static int answer = 0;
    public static int[] arr;
    public static void DFS(int start,int result,int target){
        
        if(start == arr.length){
            if(result == target){
                answer++;    
            }
            return;
        }
        start++;
        DFS(start,result+arr[start-1],target);
        DFS(start,result-arr[start-1],target);
        }
    
    public int solution(int[] numbers, int target) {
        arr = new int[numbers.length];
        for(int i=0;i<numbers.length;i++){
            arr[i] = numbers[i];
        }
        
        DFS(0,0,target);
        return answer;
    }        
}
