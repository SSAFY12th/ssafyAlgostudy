
import java.io.*;
import java.util.*;
 
public class Solution {
 
    static int[][] map;
    static int max;
    static int sum1,sum2;
    static int n,m,c;
    static int[][] result;
    static ArrayList<int[]> firstMan = new ArrayList<>();
    static ArrayList<int[]> secondMan = new ArrayList<>();
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
 
        for (int test_case =1; test_case <=T; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
 
            map = new int[n][n];
            result = new int[2][2];
            max = Integer.MIN_VALUE;
 
            for (int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            back(0,0);
            System.out.println("#"+test_case+" "+max);
        }
    }
 
    static void back (int depth, int start) {
        if (depth == 2) {
            firstMan.clear();
            secondMan.clear();
 
            int[] first = result[0];
            int[] second = result[1];
 
            for (int i=0; i < m; i++) {
                firstMan.add(new int[] {first[0],first[1] + i});
            }
            for (int i=0; i < m; i++) {
                secondMan.add(new int[] {second[0],second[1] + i});
            }
            for (int i=0; i<firstMan.size(); i++) {
                int[] node = firstMan.get(i);
                for (int j=0; j<secondMan.size(); j++) {
                    int[] nodeTwo = secondMan.get(j);
                    if (node[0] == nodeTwo[0] && node[1] == nodeTwo[1]) {
                        return;
                    }
                }
            }
            sum1 = Integer.MIN_VALUE;
            sum2 = Integer.MIN_VALUE;
 
            int[] one = new int[m];
            int[] two = new int[m];
 
            for (int i=0; i<firstMan.size(); i++) {
                int[] ints = firstMan.get(i);
                int[] ints1 = secondMan.get(i);
                one[i] = map[ints[0]][ints[1]];
                two[i] = map[ints1[0]][ints1[1]];
            }
 
            backTrack(0,one,new boolean[m],0);
            backTrack(0,two,new boolean[m],1);
            max = Math.max((sum1+sum2), max);
            return;
        }
 
        for (int i = start; i < n*(n-m+1); i++) {
            int x = i / (n-m+1);
            int y = i % (n-m+1);
             
            result[depth][0] = x;
            result[depth][1] = y;
 
            back(depth+1,i+1);
        }
 
    }
 
    static void backTrack(int depth, int[] arr, boolean[] visited, int num) {
        if (depth == m) {
            int t = 0;
            int check = 0;
 
            for (int i=0; i<m; i++) {
                if (visited[i]) {
                    t += (arr[i]*arr[i]);
                    check += arr[i];
                }
            }
 
            if (check > c) {
                return;
            }
 
            if (num == 0) {
                sum1 = Math.max(sum1,t);
            }else {
                sum2 = Math.max(sum2,t);
            }
 
            return;
        }
 
        visited[depth] = true;
        backTrack(depth+1, arr,visited,num);
        visited[depth] = false;
        backTrack(depth+1,arr,visited,num);
 
    }
}
