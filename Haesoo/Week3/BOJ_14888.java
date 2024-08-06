import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M = -1000000001;
    static int m = 1000000001;
    static int[] op = new int[4];
    static int[] num;
    public static void dfs (int cnt, int o_cnt) {
        if (o_cnt == N) {
            if (M < cnt) M = cnt;
            if (m > cnt) m = cnt;
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (op[i] == 0) continue;
            op[i]--;
            if (i == 0) dfs(cnt + num[o_cnt], o_cnt + 1);
            else if (i == 1) dfs(cnt - num[o_cnt], o_cnt + 1);
            else if (i == 2) dfs(cnt * num[o_cnt], o_cnt + 1);
            else dfs(cnt / num[o_cnt], o_cnt + 1);
            op[i]++;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        num = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }
        dfs(num[0], 1);
        System.out.println(M + " " + m);
    }
}

/* 초기에 720ms 걸린 코드
import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M = -1000000001;
    static int m = 1000000001;
    static List<Integer> op = new ArrayList<>();
    static int[] num;
    public static void dfs (int cnt, int o_cnt) {
        if (o_cnt == N) {
            if (M < cnt) M = cnt;
            if (m > cnt) m = cnt;
            return;
        }
        for (int i = 0; i < op.size(); i++) {
            if (op.get(i) != -1) {
                int temp = op.get(i);
                op.set(i, -1);
                if (temp == 0) dfs(cnt + num[o_cnt], o_cnt + 1);
                else if (temp == 1) dfs(cnt - num[o_cnt], o_cnt + 1);
                else if (temp == 2) dfs(cnt * num[o_cnt], o_cnt + 1);
                else dfs(cnt / num[o_cnt], o_cnt + 1);
                op.set(i, temp);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        num = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            int temp = Integer.parseInt(st.nextToken());
            for (int j = 0; j < temp; j++) {
                op.add(i);
            }

        }
        dfs(num[0], 1);
        System.out.println(M + " " + m);
    }
}

*/
