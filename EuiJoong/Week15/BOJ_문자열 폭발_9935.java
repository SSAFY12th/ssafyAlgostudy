import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String crushStr = br.readLine();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
        
            sb.append(str.charAt(i));
            // 폭발 문자열 검사
            if (sb.length() >= crushStr.length()) {
                String subString = sb.substring(sb.length() - crushStr.length());

                if (subString.equals(crushStr)) {
//                    System.out.println(subString);
                    sb.delete(sb.length() - crushStr.length(), sb.length());
                }
            }
        }

        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb);
        }

    }
}
