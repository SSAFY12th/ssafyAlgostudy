import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] people;
    static StringTokenizer st;
    static ArrayList<ArrayList<Integer>> adjList;
    static int ans = Integer.MAX_VALUE;
    static boolean divChk;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        people = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int adjCnt = Integer.parseInt(st.nextToken());
            ArrayList<Integer> adj = new ArrayList<>();
            for (int j = 0; j < adjCnt; j++) {
                adj.add(Integer.parseInt(st.nextToken()));
            }
            adjList.add(adj);
        }

        //구역 나누기
        for (int i = 1; i <= N/2; i++) {
            boolean[] visited = new boolean[N + 1];
            combine(1, i, new ArrayList<>(), visited);
        }

        if (divChk) {
            bw.write(ans + "");
        }
        else {
            bw.write(-1 + "");
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static void combine(int start, int end, ArrayList<Integer> groupA, boolean[] visited) {

        if (groupA.size() == end) {
            ArrayList<Integer> groupB = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    groupB.add(i);
                }
            }
            if (isLinked(groupA) && isLinked(groupB)) {
                int sum_A = 0, sum_B = 0;
                for (int i = 0; i < groupA.size(); i++) {
                    sum_A += people[groupA.get(i)];
                }
                for (int i = 0; i < groupB.size(); i++) {
                    sum_B += people[groupB.get(i)];
                }
                ans = Math.min(ans, Math.abs(sum_A - sum_B));
                divChk = true;
            }
            return;
        }

        for (int i = start; i <= N; i++) {
            visited[i] = true;
            groupA.add(i);
            combine(i + 1, end, groupA, visited);
            groupA.remove(groupA.size() - 1);
            visited[i] = false;
        }
    }
    public static boolean isLinked(ArrayList<Integer> group) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(group.get(0));
        visited[group.get(0)] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int link : adjList.get(cur)) {
                if (!visited[link] && group.contains(link)) {
                    cnt++;
                    q.add(link);
                    visited[link] = true;
                }
            }
        }
        return cnt == group.size();
    }
}