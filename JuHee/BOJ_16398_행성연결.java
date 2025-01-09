import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[] root;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n*n+1][3];
        root = new int[n+1];

        int idx = 1;
        for(int i=1;i<=n;i++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1;j<=n;j++)
            {
                int tmp = Integer.parseInt(st.nextToken());

                if(i < j) {
                    graph[idx][0] = i;
                    graph[idx][1] = j;
                    graph[idx][2] = tmp;
                    idx = idx + 1;
                }
            }
        }

        Arrays.sort(graph,1,idx,(x,y) -> x[2] - y[2] );
        
        for(int i=1;i<=n;i++)
        {
            root[i] = i;
        }

        long cost = 0;
        for(int i=1; i < idx;i++)
        {
            if(find(graph[i][0]) != find(graph[i][1]))
            {
                cost += graph[i][2];
                union(graph[i][0],graph[i][1]);
            }
        }
        System.out.println(cost);

        br.close();
    }


    static int find(int x)
    {
        if(x == root[x])
            return x;

        else{
            return root[x] = find(root[x]);
        }
    }


    static void union(int x,int y)
    {
        x = find(x);
        y = find(y);

        if(x < y)
        {
            root[y] = x;
        }

        else{
            root[x] = y;
        }
    }
}
