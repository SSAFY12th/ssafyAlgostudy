

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bombWord = br.readLine();
        int bombLen = bombWord.length();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));

            if (sb.length() >= bombLen && sb.substring(sb.length() - bombLen).equals(bombWord)) {
                sb.setLength(sb.length() - bombLen);
            }
        }
        
        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb.toString());
        }
    }
}




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
