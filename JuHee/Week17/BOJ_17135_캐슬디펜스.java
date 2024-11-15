import java.io.*;
import java.util.*;

public class Main {
    static class Monster implements Comparable<Monster> {
        int x, y, d;

        public Monster(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Monster o) {
            if (this.d == o.d) {
                return this.y - o.y;
            } else
                return this.d - o.d;
        }
    }

    static int N, M, D;
    static int[][] map;
    static List<int[]> list;
    static int[] archer;
    static int res;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        archer = new int[3];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1)
                    list.add(new int[]{i, j});
            }
        }

        locateArcher(0, 0);
        System.out.println(res);
    }

    private static void locateArcher(int idx, int start) {
        if (idx == 3) {
            List<int[]> data = copy(list);
            attackMonster(data);
            return;
        }
        for (int i = start; i < M; i++) {
            archer[idx] = i;
            locateArcher(idx + 1, i + 1);
        }
    }

    private static List<int[]> copy(List<int[]> list) {
        List<int[]> tmp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int[] cur = list.get(i);
            tmp.add(new int[]{cur[0], cur[1]});
        }
        return tmp;
    }

    private static void attackMonster(List<int[]> list) {
        int count = 0;

        while (true) {
            if (list.isEmpty())
                break;
            List<int[]> targets = new ArrayList<>();

            for (int k = 0; k < 3; k++) {
                PriorityQueue<Monster> pq = new PriorityQueue<>();

                for (int i = 0; i < list.size(); i++) {
                    int[] cur = list.get(i);
                    int d = Math.abs(cur[0] - N) + Math.abs(cur[1] - archer[k]);
                    if (d <= D)
                        pq.add(new Monster(cur[0], cur[1], d));
                }

                if (!pq.isEmpty()) {
                    Monster target = pq.poll();
                    boolean flag = false;
                    for (int i = 0; i < targets.size(); i++) {
                        int[] cur2 = targets.get(i);
                        if (target.x == cur2[0] && target.y == cur2[1])
                            flag = true;
                    }
                    if (!flag) {
                        targets.add(new int[]{target.x, target.y});
                    }
                }
            }

            for (int i = 0; i < targets.size(); i++) {
                for (int j = list.size() - 1; j >= 0; j--) {
                    if (targets.get(i)[0] == list.get(j)[0] && targets.get(i)[1] == list.get(j)[1]) {
                        list.remove(j);
                        count++;
                        break;
                    }
                }
            }

            for (int i = list.size() - 1; i >= 0; i--) {
                list.get(i)[0] += 1;
                if (list.get(i)[0] == N)
                    list.remove(i);
            }
        }
        res = Math.max(res, count);
    }
}
