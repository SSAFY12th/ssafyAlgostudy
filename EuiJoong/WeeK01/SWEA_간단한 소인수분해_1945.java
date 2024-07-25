import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[12];
            int K = 2;

            while (N != 1) {
                if (N % K == 0) {
                    arr[K] += 1;
                    N = N / K;
                }
                else {
                    K++;
                }
            }
            System.out.println("#" + tc + " " + arr[2] + " " + arr[3] + " " + arr[5] + " " + arr[7] + " " + arr[11]);
        }
    }
}