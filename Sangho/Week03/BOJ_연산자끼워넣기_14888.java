import java.util.Scanner;

public class Main {
    static int N;

    static int[] numbers;

    // +, - , * , /
    static int[] operators;

    static int maxValue = Integer.MIN_VALUE;
    static int minValue = Integer.MAX_VALUE;

    public static void DFS(int sum,int depth){

        // 깊이가 닿으면 리턴
        if (depth == N) {
            maxValue = Math.max(maxValue,sum);
            minValue = Math.min(minValue,sum);
            return;
        }

        for(int i = 0; i < 4; i++){
            if(operators[i] > 0){
                operators[i]--;

                switch (i) {
                    case 0: DFS(sum + numbers[depth],depth + 1); break;
                    case 1: DFS(sum - numbers[depth],depth + 1); break;
                    case 2: DFS(sum * numbers[depth],depth + 1); break;
                    case 3: DFS(sum / numbers[depth],depth + 1); break;
                }

                operators[i]++;
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        numbers = new int[N];

        operators = new int[4];

        for(int i = 0; i < N; i++){
            numbers[i] = sc.nextInt();
        }

        // 연산자 개수 입력 받기
        for(int i = 0; i < 4; i++){
            operators[i] = sc.nextInt();
        }

        DFS(numbers[0], 1);

        System.out.println(maxValue);
        System.out.println(minValue);


    }
}
