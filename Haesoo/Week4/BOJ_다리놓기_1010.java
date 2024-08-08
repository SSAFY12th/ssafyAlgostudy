import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            long ans = 1;
            int temp = 1;
            for (int i = M; i > M - N; i--) {
                ans *= i; // ans = ans * i 보다 쪼끔 빠르다
                ans /= temp;
                temp++;
            }
            System.out.println(ans);
        }
    }
}
