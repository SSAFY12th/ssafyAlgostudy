import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N - 1;
        int closestSum = Integer.MAX_VALUE; // 0에 가장 가까운 합
        int resultLeft = 0, resultRight = 0; // 결과 저장

        while (left < right) {
            int sum = arr[left] + arr[right];

            // 0에 더 가까운 경우 갱신
            if (Math.abs(sum) < closestSum) {
                closestSum = Math.abs(sum);
                resultLeft = arr[left];
                resultRight = arr[right];
            }

            // 합이 0보다 크면 오른쪽 포인터를 왼쪽으로 이동
            if (sum > 0) {
                right--;
            }
            // 합이 0보다 작으면 왼쪽 포인터를 오른쪽으로 이동
            else {
                left++;
            }
        }

        System.out.println(resultLeft + " " + resultRight);
    }
}
