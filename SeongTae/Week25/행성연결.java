import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static List<Edge> EdgeList;
    static int[] parent;
    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;
        
        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e){
            return this.cost - e.cost;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        EdgeList = new ArrayList<Edge>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int value = Integer.parseInt(st.nextToken());
                if(i < j){
                    Edge e = new Edge(i+1,j+1,value);
                    EdgeList.add(e);
                }
            }
        }

        Collections.sort(EdgeList);
        make();

        long cost = 0;
        int edges = 0;
        for(Edge e : EdgeList){
            if(union(e.from, e.to)){
                cost += (long)e.cost;
                if(++edges == N-1) break;
            }
        }

        System.out.println(cost);
    }

    public static void make(){
        parent = new int[N+1];
        Arrays.fill(parent, -1);
    }

    public static int find(int a){
        if(parent[a] < 0) return a;
        return parent[a] = find(parent[a]);
    }

    public static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;

        parent[aRoot] += parent[bRoot];
        parent[bRoot] = aRoot;
        return true;
    }
}
