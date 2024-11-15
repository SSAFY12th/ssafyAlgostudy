import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int N;
    static ArrayList<Integer> numbers = new ArrayList<>();
    static ArrayList<Character> operators = new ArrayList<>();
    static int maxResult = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String expression = br.readLine();
        
        for (int i = 0; i < N; i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                numbers.add(c - '0');
            } else {
                operators.add(c);
            }
        }
        
        dfs(numbers.get(0), 0);
        
        System.out.println(maxResult);
    }
    
    static void dfs(int result, int idx) {
        if (idx == operators.size()) {
            maxResult = Math.max(maxResult, result);
            return;
        }
        
        int calc1 = calculate(result, numbers.get(idx + 1), operators.get(idx));
        dfs(calc1, idx + 1);
        
        if (idx + 1 < operators.size()) {
            int nextCalc = calculate(numbers.get(idx + 1), numbers.get(idx + 2), operators.get(idx + 1));
            int calc2 = calculate(result, nextCalc, operators.get(idx));
            dfs(calc2, idx + 2);
        }
    }
    
    static int calculate(int a, int b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return 0;
    }
}
