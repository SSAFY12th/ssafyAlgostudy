import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static boolean[] visited;
    static int n,m;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        visited = new boolean[n];
        result = new int[m];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        back(0);

        System.out.println(sb.toString());



    }

    static void back(int depth) {

        if (depth == m) {

            for (int i=0; i<m; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append('\n');

            return;
        }

        int before = 0;

        for (int i=0; i<n; i++) {


            if (!visited[i]) {
                

                if (before != arr[i]) {
                    visited[i] = true;
                    before = arr[i];
                    result[depth] = arr[i];
                    back(depth+1);
                    visited[i] = false;

                }



            }
        }


    }
}
