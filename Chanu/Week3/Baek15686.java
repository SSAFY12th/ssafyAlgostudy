import java.io.*;
import java.util.*;


public class Main {
    static ArrayList<int[]> chicken = new ArrayList<>();
    static ArrayList<int[]> house = new ArrayList<>();
    static int[][] result;
    static boolean[] visited = new boolean[13];
    static int n,m;
    static int answer= Integer.MAX_VALUE;



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        result = new int[m][2];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {

                int num = Integer.parseInt(st.nextToken());
                if (num == 2) {
                    chicken.add(new int[] {i,j});
                }else if (num == 1) {
                    house.add(new int[] {i,j});
                }
            }
        }

        bfs(0,0);
        System.out.println(answer);


    }
    static void bfs(int depth, int start) {
        if (depth == m) {

            int cityDistanceChicken = 0;
            int distanceChicken;

            for (int i=0; i<house.size(); i++) {

                distanceChicken = Integer.MAX_VALUE;

                for (int j = 0; j<result.length; j++) {

                    distanceChicken = Math.min(distanceChicken,
                            Math.abs(house.get(i)[0] -result[j][0]) +
                                    Math.abs(house.get(i)[1]-result[j][1]));

                }

                cityDistanceChicken+=distanceChicken;

            }

            answer = Math.min(answer,cityDistanceChicken);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {

            if (!visited[i]) {
                visited[i] = true;
                result[depth] = chicken.get(i);
                bfs(depth+1,i+1);
                visited[i] = false;

            }
        }
    }
}


