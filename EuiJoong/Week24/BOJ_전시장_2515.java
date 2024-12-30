import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Picture implements Comparable<Picture> {
        int height;
        int cost;

        public Picture(int height, int cost) {
            this.height = height;
            this.cost = cost;
        }

        @Override
        public int compareTo(Picture o) {
            return this.height - o.height;
        }
    }

    static int N, S;
    static Picture[] pictures;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        pictures = new Picture[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            pictures[i] = new Picture(H, C);
        }

        Arrays.sort(pictures);

        int[] dp = new int[N];
        // 현재 그림까지 고려했을 때 얻을 수 있는 최대가격을 저장하기 위한 dp배열
        dp[0] = pictures[0].cost;

        for (int i = 1; i < N; i++) {
            dp[i] = dp[i - 1];
            // 이분탐색으로 높이 조건을 만족하는 가장 마지막 그림인덱스(가장 오른쪽인덱스) 반환
            int idx = bs(pictures[i].height - S);
            if (idx != -1) {
                dp[i] = Math.max(dp[i], dp[idx] + pictures[i].cost);
            } else {
                dp[i] = Math.max(dp[i], pictures[i].cost);
            }
        }
        System.out.println(dp[N - 1]);
    }

    private static int bs(int target) {
        int left = 0, right = pictures.length - 1, res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (pictures[mid].height <= target) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }
}
