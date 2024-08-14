import java.io.*;
import java.util.*;
// C++ 에서는 voteRegion을 벡터로 해서 각 구역의 idx만을 저장했다.
// 자바에서 그렇게 하려면 ArrayList 써야하니까 그냥 크기 10으로 배열 만들어서, idx 맞춰서 인구 수 바로 넣어버리자.
// 그냥 ArrayList 쓰도록 하자..
public class BOJ17471 {
    public static ArrayList<Integer>[] relations;
    public static int[] nodes;
    public static int N, regionSize1, regionSize2;
    public static boolean[] visitNodes;
    public static int minsub = 10000000;
    public static boolean bfs (ArrayList <Integer> voteRegion, int size) { // 연결 정보 확인용

        Queue <Integer> q = new LinkedList<>();
        q.offer(voteRegion.get(0));
        int nodecnt = 1;
        visitNodes[voteRegion.get(0)] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < relations[cur].size(); i++) {
                boolean in = false;
                for (int j = 0; j < voteRegion.size(); j++) {
                    if (relations[cur].get(i) == voteRegion.get(j)) {
                        in = true;
                        break;
                    }
                }
                if (in && !visitNodes[relations[cur].get(i)]) {
                    q.offer(relations[cur].get(i));
                    visitNodes[relations[cur].get(i)] = true;
                    nodecnt++;
                }
            }
        }
        //System.out.println("선거구 크기와 연결 구역 크기 비교: " + nodecnt + " " + size);
        if (nodecnt == voteRegion.size()) return true;
        return false;
    }

    public static void combi (int idx, int n, boolean[] visit, int R) {
        if (n == R) {
            int calsub = 10000000;
            ArrayList <Integer> voteRegion1 = new ArrayList<>();
            ArrayList <Integer> voteRegion2 = new ArrayList<>();
            regionSize1 = 0;
            regionSize2 = 0;
            for (int i = 0; i < N; i++) {
                if (visit[i]) voteRegion1.add(i);
                else voteRegion2.add(i);

            } // 뽑았을 때 할 것 : 뽑힌 idx의 구역을 선거구 1로 할당, 나머지는 2로 할당.
            //System.out.println(voteRegion2);
            int sum1 = 0, sum2 = 0;
            visitNodes = new boolean[10];
            boolean isConnected1 = bfs(voteRegion1, regionSize1);
            boolean isConnected2 = bfs(voteRegion2, regionSize2); // 각 선거구마다 구역 연결 여부 체크
            //System.out.println("연결여부: " + isConnected1 + " " + isConnected2);
            if (isConnected1 && isConnected2) { // 각 선거구 둘 다 연결되어 있을 시 계산
                for (int i = 0; i < voteRegion1.size(); i++) {
                    sum1 += nodes[voteRegion1.get(i)];
                }
                for (int i = 0; i < voteRegion2.size(); i++) {
                    sum2 += nodes[voteRegion2.get(i)];
                }
                //System.out.println("sum1: " + sum1 + " sum2: " + sum2);
                calsub = Math.abs(sum1 - sum2);
                minsub = Math.min(minsub, calsub);
                //System.out.println(calsub);
            }
            return;
        }
        for (int i = idx; i < N; i++) {
            visit[i] = true;
            combi(i + 1, n + 1, visit, R);
            visit[i] = false;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        nodes = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nodes[i] = Integer.parseInt(st.nextToken());
        } // 각 구역의 인구 정보를 담는 nodes, idx는 0 ~ N - 1
        relations = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            relations[i] = new ArrayList<Integer>();
            for (int j = 0; j < tmp; j++) {
                relations[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        } // 0 ~ N - 1번 구역의 연결 정보 저장
        for (int i = 0; i < N / 2; i++) {
            boolean[] visit = new boolean[nodes.length];
            combi(0, 0, visit, i + 1);
        }
        if (minsub == 10000000) System.out.println("-1");
        else System.out.println(minsub);
    }
}
