import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static String s;
    static int[] tempint;
    static int N, result;
    static void swap (int a, int b) {
        int temp = tempint[a];
        tempint[a] = tempint[b];
        tempint[b] = temp;
    }

    static void dfs (int idx, int n) {
        if (n == N) {
            int tempresult = 0;
            for (int i = 0; i < tempint.length; i++) tempresult = 10 * tempresult + tempint[i];
            result = Math.max(result, tempresult);
            return;
        }
        for (int i = idx; i < tempint.length - 1; i++) {
            for (int j = i + 1; j < tempint.length; j++) {
                if (tempint[i]<= tempint[j]) {
                    swap(i, j);
                    dfs (idx, n + 1);
                    swap(i, j);
                }
                if (i == s.length() - 2 && j == s.length() - 1) {
                    swap(i, j);
                    dfs (idx, n + 1);
                    swap(i, j);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; ++test_case) {
            st = new StringTokenizer(br.readLine());
            s = st.nextToken();
            tempint = new int[s.length()];
            for (int i = 0; i < s.length(); i++) tempint[i] = s.charAt(i) - '0';
            N = Integer.parseInt(st.nextToken());
            if (s.length() < N) N = s.length();
            result = 0;
            dfs(0, 0);
            System.out.println("#" + test_case + " " + result);
        }
    }
}
