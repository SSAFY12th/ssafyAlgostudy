import java.util.*;
import java.io.*;

public class Main {
    public static int N, result = 0;
    public static List <Integer>[] probs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        probs = new List[N];
        for (int i = 0; i < N; i++) {
            probs[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            probs[a - 1].add(b);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = N - 1; i >= 0; i--) {
            for (Integer p : probs[i]) {
                pq.add(p);
            }
            if (!pq.isEmpty()) {
                result += pq.peek();
                pq.poll();
            }
        }
        System.out.println(result);
    }
}
