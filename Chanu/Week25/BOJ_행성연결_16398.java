
import java.io.*;
import java.util.*;

public class Baek16398 {

    static int[] parents;
    static int  v;
    static List<Node> list = new ArrayList<>();

    static class Node implements Comparable<Node>{

        int from;
        int to;
        int weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());

        for (int i=0; i<v; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<v; j++) {
                int w = Integer.parseInt(st.nextToken());
                if (w > 0) {
                    list.add(new Node(i,j,w));
                }
            }
        }

        Collections.sort(list);
        init();

        int ver = 0;
        long answer = 0L;

        for (Node node : list) {

            int to = node.to;
            int from = node.from;;
            int weight = node.weight;

            if (union(from,to)) {
                answer += weight;
                ver++;

                if (ver == v-1) {
                    break;
                }
            }

        }

        System.out.println(answer);

    }

    static void init() {
        parents = new int[v];
        for (int i=0; i<v; i++) {
            parents[i] = i;
        }
    }

    static int find(int node) {

        if (node == parents[node]) {
            return node;
        }
        return parents[node] = find(parents[node]);
    }

    static boolean union(int a, int b) {

        int node1 = find(a);
        int node2 = find(b);

        if (node1 == node2) {
            return false;
        }

        parents[node2] = node1;
        return true;
    }


}
