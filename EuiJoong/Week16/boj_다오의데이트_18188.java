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
    }

    static int H, W, N;
    static List<Character> ansMoveCommand;
    static List<char[]> maridList;
    static char[][] map;
    static Map<Character, int[]> direction;
    static boolean check;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        direction = new HashMap<>();
        direction.put('W', new int[] {-1, 0});
        direction.put('A', new int[] {0, -1});
        direction.put('S', new int[] {1, 0});
        direction.put('D', new int[] {0, 1});

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        int start_r = 0, start_c = 0, end_r = 0, end_c = 0;

        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'D') {
                    start_r = i;
                    start_c = j;
                } else if (map[i][j] == 'Z') {
                    end_r = i;
                    end_c = j;
                }
            }
        }

        maridList = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char dir1 = st.nextToken().charAt(0);
            char dir2 = st.nextToken().charAt(0);
            maridList.add(new char[] {dir1, dir2});
        }
        
        ansMoveCommand = new ArrayList<>();
        check = false;
        goDiz(start_r, start_c, 0, end_r, end_c);

        if (!check) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            System.out.println(sb);
        }

    }

    private static void goDiz(int r, int c, int depth, int end_r, int end_c) {

        if (check) {
            return;
        }

        if (r == end_r && c == end_c) {
            check = true;

            sb = new StringBuilder();

            for (Character character : ansMoveCommand) {
                sb.append(character);
            }

            return;
        }

        if (depth == N) {
            return;
        }

        for (int i = 0; i < 2; i++) {
            char[] marid = maridList.get(depth);

            int[] dir = direction.get(marid[i]);

            int nr = r + dir[0];
            int nc = c + dir[1];

            if (0 > nr || nr >= H || 0 > nc || nc >= W) continue;
            if (map[nr][nc] == '@') continue;
            
            ansMoveCommand.add(marid[i]);
            goDiz(nr, nc, depth + 1, end_r, end_c);
            ansMoveCommand.remove(ansMoveCommand.size() - 1);
            
        }
    }
}
