import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static int N;
    static List<List<Integer>> adjList = new ArrayList<>();
    static ArrayList<Integer> result;
    static int[] inDegree;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            inDegree = new int[N + 1];

            for (int i = 0; i <= N; i++) {
                adjList.add(new ArrayList<>());
            }

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int adjCnt = Integer.parseInt(st.nextToken());
                if (adjCnt > 0) {
                    for (int j = 0; j < adjCnt; j++) {
                        int b = Integer.parseInt(st.nextToken());
                        adjList.get(b).add(i);
                        inDegree[i] += 1;
                    }
                }
            }
            //System.out.println(Arrays.toString(inDegree));
            //System.out.println(adjList);
            ans = -1;
            int tmp = 0;
            result = new ArrayList<>();
            topologySort();

            for (int i = 1; i <= N; i++) {
                System.out.println(bfs(i));
            }
        }
    }

    private static void topologySort() {
        Queue<Integer> q = new LinkedList<>();

        // 처음 시작할 떄는 진입차수가 0 인 노드를 큐에 삽입
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            result.add(cur);
            for (int i = 0; i < adjList.get(cur).size(); i++) {
                inDegree[adjList.get(cur).get(i)] -= 1;
                if (inDegree[adjList.get(cur).get(i)] == 0) {
                    q.offer(adjList.get(cur).get(i));
                }
            }
        }
    }

    private static int bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[N + 1];
        q.offer(v);
        visit[v] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < adjList.get(cur).size(); i++) {
                int w = adjList.get(cur).get(i);
                if (!visit[w]) {
                    q.offer(w);
                    cnt++;
                    visit[w] = true;
                }
            }
        }
        return cnt;
    }
}