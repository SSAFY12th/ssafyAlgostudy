import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] graph, reverseGraph;
    static int n, m, home, company;
    static boolean[] FromHome, ToHome, FromCompany, ToCompany;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        FromHome = new boolean[n + 1];
        ToHome = new boolean[n + 1];
        FromCompany = new boolean[n + 1];
        ToCompany = new boolean[n + 1];
        
        graph = new ArrayList[n + 1];
        reverseGraph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i< m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            reverseGraph[to].add(from);
        }
        
        st = new StringTokenizer(br.readLine());
        home = Integer.parseInt(st.nextToken());
        company = Integer.parseInt(st.nextToken());

        System.out.println(Go());
    }

    public static int Go(){
        FromHome[company] = true;
        DFS(home, graph, FromHome);

        FromCompany[home] = true;
        DFS(company, graph, FromCompany);
        
        DFS(home, reverseGraph, ToHome);
        DFS(company, reverseGraph, ToCompany);

        int sum = 0;
        for(int i = 1; i<= n; i++){
            if(FromHome[i] && ToHome[i] && FromCompany[i] && ToCompany[i]) sum++;
        }
        return sum - 2;
    }

    public static void DFS(int current, ArrayList<Integer>[] graph, boolean[] visited){
        if(visited[current]) return;

        visited[current] = true;
        for(int next : graph[current]){
            if(!visited[next]){
                DFS(next, graph, visited);
            }
        }
    }
}
