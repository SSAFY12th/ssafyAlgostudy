import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];;
        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }

        //Arrays.sort(arr); 정렬이 안되어있다면 배열을 정렬 해줘야 함

        do {
            for (int i = 0; i < N; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }while (nextPermutation(arr));
    }

    private static boolean nextPermutation(int[] arr) {
        int n = arr.length;
        //1. 교환할 i - 1 찾기
        int i = n - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) {
            --i;
        }

        if (i <= 0) {
            return false;
        }

        //교환할 j 찾기. j는 꼭대기 이전 값중 가장 큰 값
        int j = n - 1;
        while (arr[i - 1] >= arr[j]) {
            --j;
        }

        //두 위치 교환
        swap(arr, i - 1, j);

        //꼭대기 위치부터 맨 뒤까지 오름차순 정렬
        int k = n - 1;
        while (i < k) {
            swap(arr, i++, k--);
        }
        return true;

    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}