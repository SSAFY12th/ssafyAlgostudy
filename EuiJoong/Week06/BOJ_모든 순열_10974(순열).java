import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static int N;
    private static int[] num;
    private static boolean[] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        visit = new boolean[N];
        num = new int[N];
        permutation(0);

    }

    private static void permutation(int depth) {
        if (depth == N) {
            for (int i = 0; i < N; i++) {
                System.out.print(num[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                num[depth] = i + 1;
                permutation(depth + 1);
                visit[i] = false;
            }
        }
    }
}