
import java.io.*;
import java.util.*;

public class Baek1976 {

    static int n;
    static int m;
    static int[] parents;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        init();

        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=n; j++) {

                int result = Integer.parseInt(st.nextToken());

                if (result == 1) {
                    union(i,j);
                }

            }
        }

        st = new StringTokenizer(br.readLine());

        int parent = -1;
        String answer = "YES";
        for (int i=0; i<m; i++) {

            int node = Integer.parseInt(st.nextToken());

            if (i == 0) {
                parent = find(node);
                continue;
            }

            if (find(node) != parent) {
                answer = "NO";
                break;
            }

        }
        System.out.println(answer);

    }

    static void init() {
        parents = new int[n+1];

        for (int i=1; i<=n; i++) {
            parents[i] = i;
        }
    }

    static int find(int node) {
        if (parents[node] == node) {
            return node;
        }

        return parents[node] = find(parents[node]);
    }

    static boolean union(int a, int b) {
        int node1 = find(a);
        int node2 = find(b);

        if (node1 == node2) {
            return false;
        }

        parents[node2] = node1;
        return true;
    }
}
