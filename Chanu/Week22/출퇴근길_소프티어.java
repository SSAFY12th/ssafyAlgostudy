	


import java.io.*;
import java.util.*;

public class 출퇴근길 {
    static int n, m, s, t;
    static List<Integer>[] arr;
    static List<Integer>[] arrR;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n + 1];
        arrR = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            arr[i] = new ArrayList<>();
            arrR[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x].add(y);
            arrR[y].add(x);
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[] fromS = new int[n+1];
        fromS[t] = 1;
        dfs(s, arr, fromS);

        int[] fromT = new int[n+1];
        fromT[s] = 1;
        dfs(t, arr, fromT);

        int[] toS = new int[n+1];
        dfs(s, arrR, toS);

        int[] toT = new int[n+1];
        dfs(t, arrR, toT);

        int cnt = 0;
        for(int i = 1; i<=n;i++) {
            if(fromS[i]==1 && fromT[i]==1 && toS[i]==1 && toT[i]==1)
                cnt++;
        }

        System.out.println(cnt-2);
    }

    static void dfs(int now, List<Integer>[] adj, int[] visit) {
        if(visit[now]==1) return;
        visit[now] = 1;
        for(int x : adj[now])
            dfs(x, adj, visit);
    }
}
