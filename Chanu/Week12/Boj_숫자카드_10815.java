

package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek10815By {

    static int n;
    static int m;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<m; i++) {
            int key = Integer.parseInt(st.nextToken());

            int result = binarySearch(0, arr.length-1, key);

            if (result == -1) {
                sb.append(0).append(" ");
                continue;
            }
            sb.append(1).append(" ");

        }

        System.out.println(sb.toString());


    }

    static int binarySearch(int low, int high, int key) {

        while (low <= high) {

            int mid = (low + high)/2;

            if (arr[mid] == key) {
                return mid;
            }else if (arr[mid] < key) {
                low = mid + 1;
            }else if (arr[mid] > key) {
                high = mid - 1;
            }
        }

        return -1;

    }
}
