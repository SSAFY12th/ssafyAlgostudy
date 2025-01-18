import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N - 1;
        int abs = Integer.MAX_VALUE;
        int resultStart = 0;
        int resultEnd = 0;

        while (start < end) {
            int value = arr[start] + arr[end];
            if (abs > Math.abs(value)) {
                abs = Math.abs(value);
                resultStart = arr[start];
                resultEnd = arr[end];
            }

            if (value < 0) start++;
            else if (value == 0) break;
            else end--;
        }

        System.out.println(resultStart + " " + resultEnd);
    }
}
