import java.io.*;
import java.util.*;

public class SWEA1952 {
    static int[] costs;
    static int[] months;
    static int minCost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            costs = new int[4];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                costs[i] = Integer.parseInt(st.nextToken());
            }
            months = new int[12];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) {
                months[i] = Integer.parseInt(st.nextToken());
            }
            minCost = costs[3];
            dfs(0, 0);
            System.out.println("#" + t + " " + minCost);
        }
    }
    public static void dfs(int curcost, int depth) {
        if (depth >= 12) {
            minCost = Math.min(curcost, minCost);
            return;
        }
        if (months[depth] != 0) {
                dfs(curcost + months[depth] * costs[0], depth + 1);
                dfs(curcost + costs[1], depth + 1);
                dfs(curcost + costs[2], depth + 3);
            }
        else dfs(curcost, depth + 1);
    }
}

/* 처음 코드 .. 147ms
import java.io.*;
import java.util.*;

public class Solution {
    static int[] costs;
    static int[] months;
    static boolean[] isPaid;
    static int minCost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            costs = new int[4];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                costs[i] = Integer.parseInt(st.nextToken());
            }
            months = new int[12];
            isPaid = new boolean[30];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) {
                months[i] = Integer.parseInt(st.nextToken());
                if (months[i] == 0) isPaid[i] = true;
            }
            minCost = costs[3];
            dfs(0, 0);
            System.out.println("#" + t + " " + minCost);
        }
    }
    public static void dfs(int curcost, int depth) {
        if (depth >= 12) {
            minCost = Math.min(curcost, minCost);
            return;
        }
        if (months[depth] != 0) {
            if (!isPaid[depth]) {
                isPaid[depth] = true;
                for (int i = 0; i < 3; i++) {
                    if (i == 0) dfs(curcost + months[depth] * costs[i], depth + 1);
                    else if (i == 1) dfs(curcost + costs[i], depth + 1);
                    else {
                        isPaid[depth + 1] = true;
                        isPaid[depth + 2] = true;
                        dfs(curcost + costs[i], depth + 3);
                        isPaid[depth + 1] = false;
                        isPaid[depth + 2] = false;
                    }
                }
                isPaid[depth] = false;
            }
        }
        else dfs(curcost, depth + 1);
    }

}

*/
