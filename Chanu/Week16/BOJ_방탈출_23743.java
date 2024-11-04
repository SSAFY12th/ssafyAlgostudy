	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek23743 {

    static int[] parents;
    static int v;
    static int e;
    static Node[] list;

    static class Node implements Comparable<Node>{
        int from;
        int to;
        int time;

        public Node(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "from=" + from +
                    ", to=" + to +
                    ", time=" + time +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        list = new Node[e+v];

        for (int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            list[i] = new Node(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        int[] arr = new int[v];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<v; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int k = 0;
        for (int i=e; i<e+v; i++){
            list[i] = new Node(0,k+1,arr[k]);
            k++;
        }

        Arrays.sort(list);

        init();

        int result = 0;
        int check = 0;
        for (int i=0; i < list.length; i++) {

            Node node = list[i];

            if (union(node.from, node.to)) {
                result+= node.time;
                check++;

                if (check == v) {
                    break;
                }
            }

        }

        System.out.println(result);

    }

    static void init() {
        parents = new int[v+1];
        for (int i=0; i<=v; i++) {
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

        if (node2 == node1) {
            return false;
        }

        parents[node2] = node1;
        return true;
    }
}
