
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Solution {
 
    static int[] arr;
    static ArrayList<Integer>[] graph;
    static int v,e;
    static Queue<Integer> queue = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        for (int tc =1; tc<=10; tc++) {
 
            StringTokenizer st = new StringTokenizer(br.readLine());
 
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            queue.clear();
 
            arr = new int[v+1];
            graph = new ArrayList[v+1];
 
            for (int i=1; i<=v ;i++) {
                graph[i] = new ArrayList<>();
            }
 
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<e*2; i+=2) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[b]++;
                graph[a].add(b);
            }
 
            for (int i=1; i<=v; i++) {
                if (arr[i] == 0) {
                    queue.offer(i);
                }
            }
 
            bfs();
 
            System.out.println("#"+tc+" "+sb.toString());
 
 
 
        }
 
 
    }
 
    static void bfs () {
 
        sb.setLength(0);
 
        while (!queue.isEmpty()) {
 
            Integer poll = queue.poll();
 
            sb.append(poll).append(" ");
 
            for (int i=0; i < graph[poll].size(); i++) {
                Integer node = graph[poll].get(i);
                arr[node]--;
 
                if (arr[node] == 0 ) {
                    queue.offer(node);
                }
 
 
            }
 
 
 
 
        }
    }
}
