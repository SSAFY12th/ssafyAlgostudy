import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] home = new int[N];
        for (int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(home);

        int left = 1;
        int right = home[N - 1] - home[0];

        while (left <= right) {
            int mid = (left + right) / 2;

            int count = 1;
            int previousDistance = home[0];

            for (int i = 1; i < home.length; i++) {
                int distance = home[i] - previousDistance;

                if (distance >= mid) {
                    previousDistance = home[i];
                    count++;
                }
            }

            if (count >= C) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(right);
    }
}
