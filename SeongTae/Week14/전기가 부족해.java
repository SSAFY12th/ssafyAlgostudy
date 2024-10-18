import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static int[] parents;
    static Edge[] edgeList;
    static class  Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return this.weight - o.weight;
       }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); 
        K = Integer.parseInt(st.nextToken());
        // 0번 노드와 발전소가 있는 도시 K개를 연결하기 위해 M+K로 선언
        edgeList = new Edge[M+K]; 

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int from = 0;
            int to = Integer.parseInt(st.nextToken());
            int weight = 0;
            edgeList[i] = new Edge(from, to, weight);
        }

        for (int i = K; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(from, to, weight); 
        }

        Arrays.sort(edgeList);
        make();
        int cost = 0;
        int cnt = 0;
        for (Edge edge : edgeList) {
            if(union(edge.from, edge.to)){
                cost +=edge.weight;
              // 처음 K개는 0번 노드와 연결 & N-1개의 정점과 연결되면 멈춤
                if(++cnt == N+K-1){ 
                    break;
                }
            }
        }
        System.out.println(cost);
    }

    static void make(){
        parents = new int[N+1];
        Arrays.fill(parents, -1);
    }

    static int findSet(int a){
        if(parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]); 
    }
    
    static boolean union(int a, int b){
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if(aRoot == bRoot) return false;
        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }
}
