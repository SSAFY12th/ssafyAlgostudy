import java.util.*;
import java.io.*;
public class 전시장 {
    public static int N, S;
    public static picture[] pictures;
    public static int[] DP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        pictures = new picture[N];
        DP = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pictures[i] = new picture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(pictures);

        for (int i = 0, j = 0; i < N; i++) {
            while (j < N && pictures[j].height <= pictures[i].height - S) j++;
            DP[i] = Math.max((i > 0 ? DP[i - 1] : 0), pictures[i].cost + (j > 0 ? DP[j - 1] : 0));
        }

        System.out.println(DP[N - 1]);

    }
    public static class picture implements Comparable<picture> {
        int height;
        int cost;
        picture (int height, int cost) {
            this.height = height;
            this.cost = cost;
        }

        @Override
        public int compareTo(picture o) {
            return this.height - o.height;
        }
    }
}
