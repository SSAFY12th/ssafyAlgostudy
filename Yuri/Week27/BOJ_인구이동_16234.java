package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, L, R;
    static int[][] map;
    static int[][] split;
    static ArrayList<Cal> arr = new ArrayList<>();

    static int maxPeopleMove;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Cal {
        int totalPeople, totalBlock, result;
        public Cal(int totalPeople, int totalBlock, int result) {
            this.totalBlock = totalBlock;
            this.totalPeople = totalPeople;
            this.result = result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[n][n];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;

        while(true) {
            day++;
            int num = 1;
            maxPeopleMove = 1;
            split = new int[n][n];
            arr.clear();
            arr.add(new Cal(0, 0, 0));

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (split[i][j] == 0) {
                        bfs(i, j, num++);
                    }
                }
            }

            if(maxPeopleMove == 1) break;

            // 여기서 인구이동.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (split[i][j] == -1) continue;
                    map[i][j] = arr.get(split[i][j]).result;
                }
            }
        }

        System.out.println(day-1);
    }


    public static void bfs(int r, int c, int num) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        split[r][c] = num;
        int cnt = 1;
        int sum = map[r][c];

        while(!q.isEmpty()) {
            int[] now = q.poll();

            int newR, newC;

            for (int i = 0; i < 4; i++) {
                newR = now[0] + dr[i];
                newC = now[1] + dc[i];

                if(newR >= 0 && newC >= 0 && newR < n && newC < n && split[newR][newC] == 0) {
                    int cal = Math.abs(map[now[0]][now[1]] - map[newR][newC]);

                    if(cal >= L && cal <= R) {
                        split[newR][newC] = num;
                        q.add(new int[]{newR, newC});
                        sum += map[newR][newC];
                        cnt++;
                    }
                }
            }
        }

        arr.add(new Cal(sum, cnt, sum/cnt));

        if(cnt == 1) {
            split[r][c] = -1;
        }

        maxPeopleMove = Math.max(maxPeopleMove, cnt);
    }
}
