import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int H, W, N;
    static char[][] map;

    static boolean isMeet = false;

    static List<char[]> commandList;

    // W A S D 상 좌 하 우
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int ox, oy;
    static int zx, zy;

    static StringBuilder resultPath;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[H][W];

        for (int r = 0; r < H; r++) {
            String str = br.readLine();
            for (int c = 0; c < W; c++) {
                map[r][c] = str.charAt(c);
                if (map[r][c] == 'D') {
                    ox = r;
                    oy = c;
                } else if (map[r][c] == 'Z') {
                    zx = r;
                    zy = c;
                }
            }
        }

        N = Integer.parseInt(br.readLine());
        commandList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String first = st.nextToken();
            String second = st.nextToken();
            commandList.add(new char[]{first.charAt(0), second.charAt(0)});
        }

        resultPath = new StringBuilder();

        if (BFS(ox, oy)) {
            System.out.println("YES");
            System.out.println(resultPath.toString());
        } else {
            System.out.println("NO");
        }
    }

    public static boolean BFS(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        Queue<StringBuilder> pathQueue = new LinkedList<>();

        q.add(new int[]{x, y, 0});
        pathQueue.add(new StringBuilder());

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            StringBuilder path = pathQueue.poll();
            int cx = cur[0], cy = cur[1], count = cur[2];

            if (count >= N) continue;

            char[] currentCommand = commandList.get(count);

            for (char command : currentCommand) {
                int nx = cx;
                int ny = cy;

                if (command == 'W') {
                    nx += dx[0];
                    ny += dy[0];
                } else if (command == 'A') {
                    nx += dx[1];
                    ny += dy[1];
                } else if (command == 'S') {
                    nx += dx[2];
                    ny += dy[2];
                } else if (command == 'D') {
                    nx += dx[3];
                    ny += dy[3];
                }

                if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                if (map[nx][ny] == '@') continue;

                StringBuilder newPath = new StringBuilder(path).append(command);

                if (map[nx][ny] == 'Z') {
                    resultPath.append(newPath);
                    isMeet = true;
                    return true;
                }

                q.add(new int[]{nx, ny, count + 1});
                pathQueue.add(newPath);
            }
        }
        return false;
    }
}
