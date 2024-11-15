
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static char[] arr;
    static int n;
    static int[] result;
    static int max = Integer.MIN_VALUE;
    static boolean[] visited;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new char[n];
        visited = new boolean[n/2];
        result = new int[n/2];

        arr = br.readLine().toCharArray();
        for (int i=0; i<n-1; i+=2) {
            int sum = arr[i]-'0';

            if (arr[i+1] == '*') {
                sum *= arr[i+2]-'0';
            } else if (arr[i+1] == '+') {
                sum += arr[i+2]-'0';
            } else if (arr[i+1] == '-') {
                sum -= arr[i+2]-'0';
            }
            list.add(sum);
        }

        back(0);
        System.out.println(max);
    }

    static void back(int depth) {
        if (depth == n/2) {

            for (int i=0; i<visited.length-1; i++) {
                if (visited[i] && visited[i+1]) {
                    return;
                }
            }

            boolean[] booleans = new boolean[n];

            for (int i=0; i<visited.length; i++) {

                if (visited[i]) {
                    int index = i*2;
                    booleans[index] = true;
                    booleans[index+1] = true;
                    booleans[index+2] = true;
                }
            }

            Queue<String> queue = new ArrayDeque<>();

            int cnt =0;
            while (cnt < n) {
                if (booleans[cnt]) {
                    queue.offer(list.get(cnt/2)+"");
                    cnt+=3;

                }else {
                    queue.offer(arr[cnt]+"");
                    cnt++;
                }
            }

            int sum = Integer.parseInt(queue.poll());
            while (!queue.isEmpty()) {

                String poll = queue.poll();

                if (poll.equals("*")) {
                    sum *= Integer.parseInt(queue.poll());
                }else if (poll.equals("+")) {
                    sum += Integer.parseInt(queue.poll());
                }else if (poll.equals("-")) {
                    sum -= Integer.parseInt(queue.poll());
                }
            }

            max = Math.max(sum,max);


            return;
        }

        visited[depth] =true;
        back(depth+1);
        visited[depth] = false;
        back(depth+1);

    }

}
