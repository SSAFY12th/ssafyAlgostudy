	
import java.io.*;
import java.util.*;

public class Baek14501 {

    static int n;
    static int[][] arr = new int[15][2];
    static int max = Integer.MIN_VALUE;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        for (int i=0; i<n; i++) {

            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());

        }

        bfs(0,0);
        System.out.println(max);



    }
    static void bfs(int depth, int sum) {

        if (depth >= n) {
            max = Math.max(sum,max);
            return;
        }


        if (arr[depth][0] <= n - depth) {
            bfs(depth + arr[depth][0], sum + arr[depth][1]);

        }else {
            bfs(depth + arr[depth][0] ,sum);
        }


        bfs(depth + 1 ,sum);


    }
}
