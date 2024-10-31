
import java.io.*;
import java.util.*;

public class Baek1238 {
    static int n;
    static int m;
    static ArrayList<Node>[] graph;
    static PriorityQueue<Node> queue = new PriorityQueue<>();
    static int end;
    static int max = Integer.MIN_VALUE;

    static class Node implements Comparable<Node>{
        int index;
        int wight;

        public Node(int index, int wight) {
            this.index = index;
            this.wight = wight;
        }

        @Override
        public int compareTo(Node o) {
            return this.wight - o.wight;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];

        for (int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b,c));
        }
        
        for (int i=1; i<=n; i++) {
            int come = dijkstra(i, end);
            int back = dijkstra(end, i);
            max = Math.max(max,(come+back));
        }
        System.out.println(max);

    }
    
    static int dijkstra(int start, int end ) {
        queue.clear();
        int[] d = new int[n+1];
        
        for (int i=1; i<=n; i++) {
            d[i] = Integer.MAX_VALUE;
        }

        d[start] = 0;
        queue.offer(new Node(start,0));
        
        while (!queue.isEmpty()) {

            Node poll = queue.poll();

            for (int i=0; i<graph[poll.index].size(); i++) {
                
                Node node = graph[poll.index].get(i);
                
                if (d[node.index] > poll.wight+ node.wight) {
                    d[node.index] = poll.wight+ node.wight;
                    queue.offer(new Node(node.index, d[node.index]));
                }
                
            }

        }
        
        return d[end];
    }
}
