import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    
    public static void perm(int [] arr, int[] out, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            for (int i = 0; i < r; i++) {
                System.out.print(out[i] + " ");
            }
            System.out.println();
            return;
        }
        int temp = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && arr[i] != temp) {
                //swap(arr, i, depth);
                visited[i] = true;
                out[depth] = arr[i];
                temp = out[depth];
                perm(arr, out, visited, depth + 1, n, r);
                //swap(arr, i, depth);
                visited[i] = false;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), R = sc.nextInt();
        int[] data = new int[N];
        int[] output = new int[N];
        boolean[] v = new boolean[N];
        for (int i = 0; i < N; i++) data[i] = sc.nextInt();
        Arrays.sort(data);
        perm(data, output, v,0, N, R);


    }
}
