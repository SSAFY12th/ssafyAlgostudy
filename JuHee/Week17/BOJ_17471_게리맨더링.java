import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] population;
    static ArrayList<Integer>[] graph;
    static boolean[] selected;
    static int minDifference = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        graph = new ArrayList[N + 1];
        selected = new boolean[N + 1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int adjCount = Integer.parseInt(st.nextToken());
            for (int j = 0; j < adjCount; j++) {
                int neighbor = Integer.parseInt(st.nextToken());
                graph[i].add(neighbor);
            }
        }
        
        divide(1);
        
        System.out.println(minDifference == Integer.MAX_VALUE ? -1 : minDifference);
    }
    
    static void divide(int idx) {
        if (idx == N + 1) {
            int area1 = -1, area2 = -1;
            for (int i = 1; i <= N; i++) {
                if (selected[i]) area1 = i;
                else area2 = i;
            }
            
            if (area1 != -1 && area2 != -1 && isConnected(area1, true) && isConnected(area2, false)) {
                int diff = calculateDifference();
                minDifference = Math.min(minDifference, diff);
            }
            return;
        }
        
        selected[idx] = true;
        divide(idx + 1);
        
        selected[idx] = false;
        divide(idx + 1);
    }
    
    static boolean isConnected(int start, boolean flag) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : graph[current]) {
                if (!visited[neighbor] && selected[neighbor] == flag) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    count++;
                }
            }
        }
        
        for (int i = 1; i <= N; i++) {
            if (selected[i] == flag && !visited[i]) return false;
        }

        return true;
    }
    
    static int calculateDifference() {
        int populationA = 0, populationB = 0;
        for (int i = 1; i <= N; i++) {
            if (selected[i]) populationA += population[i];
            else populationB += population[i];
        }
        return Math.abs(populationA - populationB);
    }
}
