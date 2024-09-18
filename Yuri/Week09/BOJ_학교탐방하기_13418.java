import java.util.*;
import java.io.*;

public class Main {

    // 크루스칼을 이용한 풀이

    static int N, M, worst, best;
    static List<Edge> edges = new ArrayList<>();
    static int[] parents;

    static class Edge implements Comparable<Edge> {
        int from, to, climb;
        public Edge(int from, int to, int climb) {
            this.from = from;
            this.to = to;
            this.climb = climb;
        }

        @Override
        public int compareTo(Edge o) {
            if(climb != o.climb)    // 오르막길부터 정렬되도록 한다. (0이 오르막길)
                return this.climb - o.climb;
            else if(from != o.from)
                return this.from - o.from;
            else
                return this.to - o.to;
        }

    }

    static public void make() {
        parents = new int[N+1];
        for (int i = 1; i <= N; i++)
            parents[i] = i;
    }

    static public int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static public boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int climb = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, climb));
        }

        make();
        int climb = 0;
        int down = 0;

        Collections.sort(edges);

        for (Edge e : edges) {
            if(union(e.from, e.to)) {
                if(e.climb == 0)
                    climb++;
                else
                    down++;
            }
            if(climb+down == N) break;
        }

        worst = climb*climb;


        make();
        climb = 0;
        down = 0;

        for (int i = M; i >= 0 ; i--) {
            Edge e = edges.get(i);
            if(union(e.from, e.to)) {
                if(e.climb == 0)
                    climb++;
                else
                    down++;
            }
            if(climb+down == N) break;
        }

        best = climb*climb;

        System.out.println(worst-best);
    }
}

/*
입구가 따로 있음. 숫자 0부터 시작.
오르막길을 많이 포함하면 피곤해진다.
오르막길을 k번 오를 때 피로도는 k^2.
피로도의 계산은 최초 조사된 길을 기준으로만 함. (내리막 후 다시 올라올 때의 경우는 고려 x)
-> 그냥 선택된 오르막 전체 개수를 뜻하는듯?
(아 내리막으로 이동했다가 다시 이전으로 돌아오는 경우(내리막이니 돌아올 때는 오르막이 됨)를 고려하지 않는다는 뜻이다.)
 */
