import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        p = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            p[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int isConnected = Integer.parseInt(st.nextToken());

                if (i != j && isConnected == 1) {
                    union(i, j);
                }
            }
        }
        
        Set<Integer> validSet = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int tmpInput = Integer.parseInt(st.nextToken());
            validSet.add(p[tmpInput]);
        }
        
        if (validSet.size() > 1) System.out.println("NO");
        else System.out.println("YES");

    }

    private static int find(int x) {
        if (x == p[x]) return x;
        return p[x] = find(p[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y) p[y] = x;
        else p[x] = y;
    }
}
