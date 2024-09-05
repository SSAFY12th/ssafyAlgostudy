import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "[" +
                    r +
                    ", " + c +
                    "]";
        }
    }
    private static int ans;
    private static char[][] map, copyMap;
    private static boolean[][] visit1, visit2;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        copyMap = new char[12][6];
        ans = 0;

        for (int i = 0; i < 12; i++) {
            String str = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = str.charAt(j);
                copyMap[i][j] = str.charAt(j);
            }
        }

        ans = 0;
        int chk = 0;
        while (true) {
            visit1 = new boolean[12][6];
            chk = solution();
            if (chk == 0) break;
            ans++;
        }
        System.out.println(ans);




    }
    private static int solution() {
        int cnt = 0;
        int res = 0;
        ArrayList<Point> start = new ArrayList<>();

        for (int i = 11; i >= 0; i--) {
                for (int j = 5; j >= 0; j--) {
                    if (map[i][j] != '.' && !visit1[i][j]) {
                        cnt = check(i, j);
                        if (cnt >= 4) {
                            res++;
                            start.add(new Point(i, j));
                            //crush(i, j);
                            //down();
                            //printMap();
                        }
                    }
                }
            }

        for (int i = 0; i < start.size(); i++) {
            int r = start.get(i).r;
            int c = start.get(i).c;
            crush(r, c);
        }
        down();
        //printMap();

        return res;
    }


    private static void down() {
        Stack<Character> s = new Stack<>();
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 12; i++) {
                if (map[i][j] != '.') {
                    s.push(map[i][j]);
                    map[i][j] = '.';
                }
            }

            for (int i = 11; i >= 0; i--) {
                if (!s.isEmpty()) {
                    map[i][j] = s.pop();
                }
            }
        }

    }

    private static int check(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c));
        visit1[r][c] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            Point point = q.poll();
            r = point.r;
            c = point.c;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (0 <= nr && nr < 12 && 0 <= nc && nc < 6 && !visit1[nr][nc] && map[r][c] == map[nr][nc]) {
                    q.offer(new Point(nr, nc));
                    visit1[nr][nc] = true;
                    cnt++;
                }
            }
        }
        //System.out.println(cnt);
        return cnt;
    }

    private static void crush(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c));
        char check = map[r][c];
        map[r][c] = '.';

        while (!q.isEmpty()) {
            Point point = q.poll();
            r = point.r;
            c = point.c;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (0 <= nr && nr < 12 && 0 <= nc && nc < 6 && check == map[nr][nc]) {
                    q.offer(new Point(nr, nc));
                    map[nr][nc] = '.';
                }
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
