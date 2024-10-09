import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] arr2 = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        /*
        1. arr1 정렬한다.
        2. arr2 에서 0부터 arr2.length - 1까지 순회하며 타겟을 정하고 이분탐색으로 찾아본다.
         */
        Arrays.sort(arr1);
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(binarySearch(arr1, arr2[i]) + " ");
        }
    }

    private static int binarySearch(int[] arr, int target) {
        //1. 시작점과 끝점을 정한다.
        int left = 0;
        int right = arr.length - 1;

        //2. 써치!
        while (left <= right) {
            //중간 값
            int mid = (left + right) / 2;

            if (arr[mid] == target) {
                return 1;
            }

            if (arr[mid] > target) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return 0;
    }
}