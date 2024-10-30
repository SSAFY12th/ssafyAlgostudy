import java.io.*;
import java.util.*;

public class BOJ1238 {
    public static int curCity, nextCity, cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;
        List <info>[] cities = new List[N];
        List <info>[] reverse = new List[N];
        for (int i = 0; i < N; i++) {
            cities[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
             curCity = Integer.parseInt(st.nextToken()) - 1;
             nextCity = Integer.parseInt(st.nextToken()) - 1;
             cost = Integer.parseInt(st.nextToken());
            cities[curCity].add(new info(nextCity, cost));
            reverse[nextCity].add(new info(curCity, cost));
        }
        int[] answer = new int[N];
        int[] cost = new int[N];
        Arrays.fill(cost, 100000);
        PriorityQueue<info> pq = new PriorityQueue<>();
        curCity = X;
        int curCost = 0;
        cost[curCity] = curCost;
        pq.offer(new info(curCity, curCost));
        while (!pq.isEmpty()) {
            curCity = pq.peek().toCity;
            curCost = pq.peek().time;
            pq.poll();
            if (cost[curCity] < curCost) continue;
            for (int i = 0; i < cities[curCity].size(); i++) {
                    int nextCity = cities[curCity].get(i).toCity;
                    int nextCost = cities[curCity].get(i).time;
                    if (cost[nextCity] > nextCost + curCost) {
                        cost[nextCity] = nextCost + curCost;
                        pq.offer(new info(nextCity, cost[nextCity]));
                    }
            }
        }

        for (int i = 0; i < N; i++) {
            answer[i] += cost[i];
        }
        Arrays.fill(cost, 100000);
        curCity = X;
        curCost = 0;
        cost[curCity] = curCost;
        pq.offer(new info(curCity, curCost));
        while (!pq.isEmpty()) {
            curCity = pq.peek().toCity;
            curCost = pq.peek().time;
            pq.poll();
            if (cost[curCity] < curCost) continue;
            for (int i = 0; i < reverse[curCity].size(); i++) {
                    int nextCity = reverse[curCity].get(i).toCity;
                    int nextCost = reverse[curCity].get(i).time;
                    if (cost[nextCity] > nextCost + curCost) {
                        cost[nextCity] = nextCost + curCost;
                        pq.offer(new info(nextCity, cost[nextCity]));
                    }
            }
        }
        for (int i = 0; i < N; i++) {
            answer[i] += cost[i];
        }
        Arrays.sort(answer);
        System.out.println(answer[N - 1]);

    }
    public static class info implements Comparable<info> {
        int toCity;
        int time;
        info (int a, int b) {
            this.toCity = a;
            this.time = b;
        }
        @Override
        public int compareTo(info o) {
            return this.time - o.time;
        }
    }
}
