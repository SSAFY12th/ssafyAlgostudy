import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static int N;
    static int[] arr;
    static int[] op;
    static int add, sub, mul, div;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        op = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }
        add = op[0];
        sub = op[1];
        mul = op[2];
        div = op[3];

        dfs(1, arr[0], add, sub, mul, div);
        System.out.println(max);
        System.out.println(min);
    }
    public static void dfs(int depth, int total, int add, int sub, int mul, int div) {
        if (depth == arr.length) {
            max = Math.max(max, total);
            min = Math.min(min, total);
            return;
        }

        if (add > 0) {
            dfs(depth + 1, total + arr[depth], add - 1, sub, mul, div);
        }
        if (sub > 0) {
            dfs(depth + 1, total - arr[depth], add, sub - 1, mul, div);
        }
        if (mul > 0) {
            dfs(depth + 1, total * arr[depth], add, sub, mul - 1, div);
        }
        if (div > 0) {
            dfs(depth + 1, (int) total / arr[depth], add, sub, mul, div - 1);
        }
    }
}