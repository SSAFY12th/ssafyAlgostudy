import java.util.Scanner;

public class Solution {
    static int T;
    static String[] num;
    static int chance;
    static int max;

    public static void DFS(int start, int depth){
        if(depth == chance){
            String result = "";
            for(String s : num){
                result += s;
            }
            max = Math.max(max,Integer.parseInt(result));
            return;
        }
        for (int i = start; i < num.length; i++){
            for(int j = i + 1; j < num.length; j++){
                String temp;
                temp = num[i];
                num[i] = num[j];
                num[j] = temp;

                DFS(i,depth + 1);

                temp = num[j];
                num[j] = num[i];
                num[i] = temp;

            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        // DFS 완전 탐색
        // 그러나 찬스가 넘버의 길이를 넘어가면
        // 그냥 넘버 길이만큼으로 제한. 그래야 시간초과 안남 ㅅㄱ

        for(int tc = 1; tc <= T; tc++){
            num = sc.next().split("");
            chance = sc.nextInt();

            max = Integer.MIN_VALUE;

            if(num.length < chance){
                chance = num.length;
            }

            DFS(0,0);

            System.out.println("#"+tc+" "+max);



        }

    }
}
