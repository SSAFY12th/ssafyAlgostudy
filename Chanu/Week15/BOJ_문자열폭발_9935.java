
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bombWord = br.readLine();
        int bombLen = bombWord.length();

        for(int i=0; i<str.length(); i++ ){
            stack.push(str.charAt(i));
            if(stack.size() >= bombLen) {

                boolean check = true;
                for(int j=0; j<bombLen; j++) {
                    if(stack.get(stack.size() + j -bombLen) != bombWord.charAt(j)) {
                        check = false;
                        break;
                    }
                }

                if(check) {
                    for(int j=0; j<bombLen; j++) {
                        stack.pop();
                    }
                }
            }
        }

        if(stack.isEmpty()) {
            System.out.println("FRULA");
        }
        else {
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            System.out.println(sb.reverse().toString());
        }
    }
}
