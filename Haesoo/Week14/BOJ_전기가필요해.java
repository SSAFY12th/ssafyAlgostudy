import java.util.*;
import java.io.*;
public class BOJ10423 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] electrics = new int[K];
        boolean[] cities = new boolean[N];
        PriorityQueue<pair> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            electrics[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        List <pair>[] cables = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            cables[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int aCity = Integer.parseInt(st.nextToken()) - 1;
            int bCity = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            cables[aCity].add(new pair(bCity, cost));
            cables[bCity].add(new pair(aCity, cost));
        }
        for (int i = 0; i < K; i++) {
            int curCity = electrics[i];
            cities[curCity] = true;
            for (int j = 0; j < cables[curCity].size(); j++) {
                pq.offer(cables[curCity].get(j));
            }
        }
        int totalCost = 0;
        while (!pq.isEmpty()) {
            int curCity = pq.peek().nextCity;
            int curCost = pq.peek().cost;
            pq.poll();
            if (!cities[curCity]) {
                cities[curCity] = true;
                totalCost += curCost;
                for (int i = 0; i < cables[curCity].size(); i++) {
                    if (!cities[cables[curCity].get(i).nextCity]) pq.offer(cables[curCity].get(i));
                }
            }
        }
        System.out.println(totalCost);
    }
    public static class pair implements Comparable<pair> {
        int nextCity;
        int cost;
        pair (int a, int b) {
            this.nextCity = a;
            this.cost = b;
        }

        @Override
        public int compareTo(pair o) {
            return this.cost - o.cost;
        }
    }
}
