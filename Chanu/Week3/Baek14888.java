
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] result;
    static ArrayList<Integer> list;
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    static int n;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());


        arr = new int[n];
        result = new int[n-1];
        list = new ArrayList<>();
        visited = new boolean[n-1];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<4; i++) {
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                list.add(i);
            }

        }

        bfs(0);
        System.out.println(max);
        System.out.println(min);


    }
    static void bfs(int depth) {
        if (depth == n-1) {

            int sum = arr[0];

            for (int i=0; i<n-1; i++) {
                if (result[i] == 0) {
                    sum+=arr[i+1];
                }else if (result[i] == 1) {
                    sum-=arr[i+1];
                }else if (result[i] == 2) {
                    sum*=arr[i+1];
                }else if (result[i] == 3) {
                    sum/=arr[i+1];
                }

            }

            max = Math.max(max,sum);
            min = Math.min(min,sum);



            return;
        }

        for (int i=0; i <n-1; i++) {

            if (!visited[i]) {
                visited[i] = true;
                result[depth] = list.get(i);
                bfs(depth+1);
                visited[i] = false;
            }

        }
    }
}
