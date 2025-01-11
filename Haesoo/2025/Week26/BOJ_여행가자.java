import java.util.*;
import java.io.*;

public class Main {
    public static int N, M;
    public static int[] parents = new int[201];
    public static int[] plan = new int[1000];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
        int num = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                num = Integer.parseInt(st.nextToken());
                if (j >= i) {
                    if (num == 1) union(i, j);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }
        int start = find(parents[plan[0]]);
        for (int i = 1; i < M; i++) {
            if (start != find(parents[plan[i]])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
    public static int find(int a) {
        if (parents[a] == a) return a;
        else return parents[a] = find(parents[a]);
    }
    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        else parents[b] = a;
        return true;
    }
}
