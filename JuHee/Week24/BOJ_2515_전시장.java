import java.io.*;
import java.util.*;

public class Main {
    static int[][] pictures;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        pictures = new int[N + 1][2];
        pictures[0] = new int[]{Integer.MIN_VALUE, 0};
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pictures[i] = new int[]{h, c};
        }

        Arrays.sort(pictures, (o1, o2) -> {
            if(o1[0] == o2[0]) return Integer.compare(o2[1], o1[1]);
            return Integer.compare(o1[0], o2[0]);
        });
        

        for (int i = 1; i <= N; i++) {
            int[] picture = pictures[i];
        }
    }

    private static int upperBound(int target) {
        int l = 1;
        int r = N;

        while (l <= r) {
            int mid = (l + r) / 2;

            if (pictures[mid][0] <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
