import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2, N);
        ans = 0;

        recursion(r, c, size);
        System.out.println(ans);
    }
    private static void recursion(int r, int c, int size) {

        if (size == 1) {
            return;
        }

        if (r < size / 2 && c < size / 2) {
            recursion(r, c, size / 2);
        }
        else if (r < size / 2 && c >= size / 2) {
            ans += size * size / 4;
            recursion(r, c - size / 2, size / 2);
        }
        else if (r >= size / 2 && c < size / 2) {
            ans += (size * size / 4) * 2;
            recursion(r - size / 2, c, size / 2);
        }
        else {
            ans += (size * size / 4) * 3;
            recursion(r - size / 2, c - size / 2, size / 2);
        }
    }
}
