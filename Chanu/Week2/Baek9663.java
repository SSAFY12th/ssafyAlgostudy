package backTracking;

import java.io.*;


public class Baek9663 {

    static int[] arr;
    static int n;
    static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        backTracking(0);
        System.out.println(count);

    }

    static void backTracking(int depth) {

        if (depth == n) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {

            arr[depth] = i;

            if (check(depth)) {
                backTracking(depth+1);
            }

            arr[depth] = 0;

        }

    }

    static boolean check(int x) {

        for (int i = 0; i < x; i++) {

            if (arr[i] == arr[x]) {
                return false;
            }

            if (Math.abs(arr[x] - arr[i]) == Math.abs(x - i)) {
                return false;
            }

        }

        return true;

    }
}
