import java.io.BufferedReader;
import java.io.InputStreamReader;
class Solution {
    public static int exCount;
    public static int max;
    public static String[] numbers;
    public static void swap(String[] arr, int index1, int index2) {
        String temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    public static int calVal(String[] numbers){
        String s = "";
        for(int i=0;i<numbers.length;i++){
            s+=numbers[i];
        }
        int result = Integer.parseInt(s);
        return result;
    }
    public static void DFS(int depth, int start) {
        if (depth == exCount) {
            int result = calVal(numbers);
            max = Math.max(max, result);
            return;
        }

        for (int i = start; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {     
                    swap(numbers, i, j);
                    DFS(depth + 1,i);
                    swap(numbers, i, j); // 원래 상태로 되돌리기
            }
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            String s = br.readLine();
            String[] str = s.split(" ");
            numbers = str[0].split("");
            exCount = Integer.parseInt(str[1]);
            
            if(numbers.length < exCount){
                exCount = numbers.length;
            }
            
            max = Integer.MIN_VALUE; // 매 테스트 케이스마다 초기화
            DFS(0,0);
            System.out.println("#" + test_case + " "+ max);
        }
    }
}
