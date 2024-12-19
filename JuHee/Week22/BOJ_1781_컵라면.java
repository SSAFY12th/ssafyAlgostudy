import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> problems = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int cupRamen = Integer.parseInt(st.nextToken());
            problems.add(new int[]{deadline, cupRamen});
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        while (!problems.isEmpty()) {
            int[] current = problems.poll();
            pq.add(current[1]);

            if (pq.size() > current[0]) {
                pq.poll();
            }
        }

        int result = 0;
        while (!pq.isEmpty()) {
            result += pq.poll();
        }

        System.out.println(result);
    }
}
