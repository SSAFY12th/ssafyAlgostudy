import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        boolean[] ans = new boolean[T];
      
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            String[] s = new String[N];
            for (int i = 0; i < N; i++) {
                s[i] = br.readLine();
            }
            Arrays.sort(s);
            for (int i = 0; i < N - 1; i++) {
                if (s[i].length() == s[i + 1].length())
                    if (s[i].equals(s[i + 1])) {
                        ans[t] = true;
                    }
                if (s[i].startsWith(s[i + 1])) {
                    ans[t] = true;
                    break;
                }
                else if (s[i + 1].startsWith(s[i])) {
                    ans[t] = true;
                    break;
                }
            }
        }
      
        for (boolean flag : ans) {
            sb.append(flag ? "NO\n" : "YES\n");
        }
        System.out.print(sb);
    }
}
