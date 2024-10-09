package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Rupee {
        int r;
        int c;
        int cost;

        public Rupee(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    static int N;
    static int[][] map;
    static boolean[][] visit;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int idx = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            ans = move(0, 0, map[0][0]);
            sb.append("Problem").append(" ").append(idx + ":").append(" ").append(ans).append("\n");
            idx++;
        }
        System.out.println(sb);
    }
    //다익스트라
    private static int move(int r, int c, int cost) {
        PriorityQueue<Rupee> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        visit = new boolean[N][N];
        pq.offer(new Rupee(r, c, cost));
        visit[r][c] = true;
        int[][] moves = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(moves[i], Integer.MAX_VALUE);
        }
        moves[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            Rupee rupee = pq.poll();
            r = rupee.r;
            c = rupee.c;
            cost = rupee.cost;

            if (r == N - 1 && c == N - 1) break;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (0 > nr || nr >= N || 0 > nc || nc >= N || visit[nr][nc]) continue;

                if (cost + map[nr][nc] < moves[nr][nc]) {
                    moves[nr][nc] = cost + map[nr][nc];
                    pq.offer(new Rupee(nr, nc, cost + map[nr][nc]));
                }
            }
        }
        return moves[N - 1][N - 1];
    }
}
