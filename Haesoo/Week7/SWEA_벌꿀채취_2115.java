import java.io.*;
import java.util.*;

public class SWEA2115 {
    public static int C, M, N;
    public static int maxsum = 0, dfscnt;
    public static int realans = 0, maxearning = 0, earning;
    public static int honeys;
    public static boolean[] visit;
    public static int[][] maps;

    public static void dfs(int limit, List<Integer> honeys, int depth, int sum) { // 부분집합으로 각 일꾼이 M개에 대해 얻을 수 있는 최댓값 계산
        if (limit > C) return;
        if (depth == honeys.size()) {
            maxsum = Math.max(maxsum, sum);
            return;
        }
        dfs(limit + honeys.get(depth), honeys, depth + 1, sum + (int) Math.pow(honeys.get(depth), 2));
        dfs(limit, honeys, depth + 1, sum);
    }

    public static void CombiRow(int depth, int idx) { // 행을 2개 골라서 계산 (두 일꾼이 다른 행에 있을 경우)
        if (depth == 2) {
            maxearning = 0; // 각 일꾼이 벌어온거
            for (int i = 0; i < N; i++) {
                if (visit[i]) CombiHoney(i);
            }
            realans = Math.max(maxearning, realans);
            return;
        }
        for (int i = idx; i < N; i++) {
            visit[i] = true;
            CombiRow(depth + 1, i + 1);
            visit[i] = false;
        }
    }

    public static void CombiHoney(int i) { // 각 행에 대해서, 연속된 M개씩 뽑아서 일꾼 투입
        earning = 0;
        for (int j = 0; j < N - M + 1; j++) {
            maxsum = 0;// 각 행에 대해서 가능한 연속 선택 경우의 수
            List<Integer> honey = new ArrayList<>();
            for (int k = j; k < j + M; k++) {
                honey.add(maps[i][k]);
            }
            dfs(maxsum, honey, 0, 0);
            earning = Math.max(earning, maxsum);
        }
        maxearning += earning;
    }

    public static void SameRow(int i) { // 이거는 각 행에 대해 일꾼 두 명이 같이 있을 경우
        for (int j = 0; j < N - M + 1; j++) {
            maxearning = 0;
            maxsum = 0; // 각 일꾼이 벌어온 거
            if (N - j >= 2 * M) {
                List<Integer> honey = new ArrayList<>();
                int k;
                for (k = j; k < j + M; k++) {
                    honey.add(maps[i][k]);
                }
                dfs(0, honey, 0, 0);
                maxearning += maxsum;
                maxsum = 0; // 각 일꾼이 벌어온 거
                honey.clear();
                for (int l = k; l < k + M; l++) {
                    honey.add(maps[i][l]);
                }
                dfs(0, honey, 0, 0);
                maxearning += maxsum; // 두 일꾼이 벌어온 거
            }
        }
        realans = Math.max(maxearning, realans); // 이게 찐 최댓값
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            maps = new int[N][N];
            realans = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
                if (N >= 2 * M) SameRow(i);
            }
            visit = new boolean[N];
            Arrays.fill(visit, 0, 2, true);
            CombiRow(0, 0);
            System.out.println("#" + t + " " + realans);
        }
    }
}

/* 동일한 행에서는 두 명 못하는 경우인데 왜 맞았지. (128ms)
import java.io.*;
import java.util.*;
 
public class Solution {
    public static int C, M, N;
    public static int maxsum = 0;
    public static int realans = 0, maxearning = 0, earning;
 
    public static int honeys;
    public static boolean[] visit;
    public static int[][] maps;
    public static void dfs (int limit, List<Integer> honeys, int depth, int sum) {
        if (limit > C) return;
        if (depth == honeys.size()) {
            maxsum = Math.max(maxsum, sum);
            return;
        }
        dfs(limit + honeys.get(depth), honeys, depth + 1, sum + (int)Math.pow(honeys.get(depth), 2));
        dfs(limit, honeys, depth + 1, sum);
    }
 
    public static void CombiRow (int depth, int idx) {
        if (depth == 2) {
            maxearning = 0;
            for (int i = 0; i < N; i++) {
                if (visit[i]) CombiHoney(i);
 
            }
            realans = Math.max(maxearning, realans);
            return;
        }
        for (int i = idx; i < N; i++) {
            visit[i] = true;
            CombiRow(depth + 1, i + 1);
            visit[i] = false;
 
        }
    }
    public static void CombiHoney(int i) {
        earning = 0;// 전체 행 중 2개 뽑는 경우의 수
 
        for (int j = 0; j < N - M + 1; j++) {
            maxsum = 0;// 각 행에 대해서 가능한 연속 선택 경우의 수
            List<Integer> honey = new ArrayList<>();
            for (int k = j; k < j + M; k++) {
                honey.add(maps[i][k]);
            }
            dfs(0, honey, 0, 0);
            earning = Math.max(earning, maxsum);
        }
        maxearning += earning;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            maps = new int[N][N];
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            visit = new boolean[N];
            Arrays.fill(visit, 0, 2, true);
            realans = 0;
            CombiRow(0, 0);
            System.out.println("#" + t + " " + realans);
        }
    }
}
*/
