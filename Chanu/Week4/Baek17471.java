
import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr = new int[11][11];
    static boolean[] visited = new boolean[11];
    static int[] people = new int[11];
    static ArrayList<Integer> list = new ArrayList<>();
    static int n;
    static int min = Integer.MAX_VALUE;

    static int[] arr1 = new int[11];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            for (int j=0; j<num; j++) {

                int k = Integer.parseInt(st.nextToken());
                arr[i][k] = 1;
                arr[k][i] = 1;

            }
        }

        for (int i=1; i<=n/2; i++) {

            visited = new boolean[11];
            back(0,1,i);

        }

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        }else {
            System.out.println(min);
        }


    }

    static void back(int depth,int start, int cnt){

        if (depth == cnt) {

            List<Integer> otherList = new ArrayList<>();

            for (int i=1; i<=n; i++) {

                if(!list.contains(i)) {
                    otherList.add(i);
                }
            }

            int[] arr1 = new int[11];
            int[] arr2 = new int[11];

            arr1[list.get(0)] = 1;
            arr2[otherList.get(0)] = 1;

            int[] one = bfs(list.get(0),arr1,list);
            int[] two = bfs(otherList.get(0),arr2,otherList);

            for (Integer number : list) {
                if (one[number] == 0) {
                    return;
                }
            }

            for (Integer number : otherList) {
                if (two[number] == 0) {
                    return;
                }
            }


            int a1 = 0;
            int a2 = 0;

            for (Integer number : list) {
                a1 += people[number];
            }

            for (Integer number : otherList) {
                a2+=people[number];
            }


            min = Math.min(Math.abs(a1-a2),min);


            return;

        }

        for (int i=start; i<=n; i++) {

            if (!visited[i]) {
                visited[i] = true;
                list.add(i);
                back(depth+1,i+1,cnt);
                list.remove(list.size()-1);
                visited[i] = false;
            }
        }


    }
    static int[] bfs(int node,int[] visit, List<Integer> r) {


        for (int i=1; i <=n; i++) {

            if (node == i){
                continue;
            }

            if (arr[node][i] == 1){

                if (visit[i] == 0 && r.contains(i)) {
                    visit[i] = 1;
                    bfs(i,visit,r);
                }

            }

        }

        return visit;



    }
}
