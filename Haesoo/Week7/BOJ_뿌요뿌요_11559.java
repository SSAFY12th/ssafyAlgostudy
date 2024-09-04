import java.io.*;
import java.util.*;

public class BOJ11559 {
    public static char[][] field = new char[12][6];
    public static boolean[][] visited = new boolean[12][6];
    public static List <pair> coords;
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};
    public static int chainCnt = 0, colorcnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                //if (s.charAt(j) != '.')
                field[i][j] = s.charAt(j);
            }
        }
        while (true) {
            visited = new boolean[12][6];
            int retcnt = 0;
            for (int i = 11; i >= 0; i--) {
                for (int j = 5; j >= 0; j--) {
                    colorcnt = 1;
                    coords = new ArrayList<>();
                    if (field[i][j] != '.' && !visited[i][j]) count(i, j);
                    if (colorcnt >= 4) bomb();
                    retcnt = Math.max(retcnt, colorcnt);
                }
            }
            if (retcnt < 4) break;
            down();
            chainCnt++;
        }
        System.out.println(chainCnt);
    }
    public static boolean Range (int ny, int nx) {
        return ny >= 0 && nx >= 0 && ny < 12 && nx < 6;
    }


    public static void count(int y, int x) {
        visited[y][x] = true;
        pair c = new pair(y, x);
        coords.add(c);
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (Range(ny, nx)) {
                if (!visited[ny][nx] && field[ny][nx] == field[y][x]) {
                    colorcnt++;
                    count(ny, nx);
                }
            }
        }
    }

    public static void bomb() {
        for (int i = 0; i < coords.size(); i++) {
            int y = coords.get(i).y;
            int x = coords.get(i).x;
            field[y][x] = '.';
        }
    }
    public static void down() {
        for (int i = 0; i < 6; i++) {
            Stack <Character> s = new Stack<>();
            for (int j = 0; j < 12; j++) {
                if (field[j][i] != '.') {
                    s.push(field[j][i]);
                    field[j][i] = '.';
                }
            }

            for (int j = 11; j >= 0; j--) {
                if (!s.empty()) {
                    char curchar = s.peek();
                    s.pop();
                    field[j][i] = curchar;
                }
            }
        }
    }
    public static class pair {
        int y;
        int x;
        pair (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
