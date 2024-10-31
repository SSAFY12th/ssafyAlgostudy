
import java.io.*;
import java.io.*;
import java.io.InputStreamReader;

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
