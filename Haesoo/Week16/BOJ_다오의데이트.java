import java.util.*;
import java.io.*;

public class 다오 {
    public static int H, W, N;
    public static int[] dy = {-1, 0, 1, 0};
    public static int[] dx = {0, 1, 0, -1};
    public static char[][] m = new char[21][21];
    public static char[] command = {'W', 'D', 'S', 'A'};
    public static List <Integer> ans = new ArrayList<>();
    public static int len = 10000000;
    public static pair start;
    public static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= H; i++) {
            //st = new StringTokenizer(br.readLine());
            String s = br.readLine();
            for (int j = 1; j <= W; j++) {
                m[i][j] = s.charAt(j - 1);// st.nextToken().charAt(0);
                if (m[i][j] == 'D') {
                    start = new pair(i, j);
                    m[i][j] = '.';
                }
            }
        }
        N = Integer.parseInt(br.readLine());
        pair[] v = new pair[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char c1 = st.nextToken().charAt(0);
            char c2 = st.nextToken().charAt(0);
            v[i] = new pair(convert(c1), convert(c2));
        }
        List <Integer> path = new ArrayList<>();

        dfs(v, path, start, 0);
        if (flag) return;
        if (len == 10000000) {
            System.out.print("N0");
            return;
        }

    }
    public static int convert (char c) {
        if (c == 'W') return 0;
        else if (c == 'D') return 1;
        else if (c == 'S') return 2;
        else return 3;
    }
    public static boolean range (int ny, int nx) {
        return ny >= 0 && nx >= 0 && ny <= H && nx <= W;
    }
    public static void dfs (pair[] v, List <Integer> path, pair now, int depth) {
        if (m[now.first][now.second] == 'Z') {
            if (path.size() < len) {
                len = path.size();
                ans = path;
                flag = true;
                System.out.println("YES");
                for (int i = 0; i < ans.size(); i++) {
                    System.out.print(command[ans.get(i)]);
                }
            }
            return;
        }
        if (depth == N) return;
        int ny = now.first + dy[v[depth].first];
        int nx = now.second + dx[v[depth].first];
        if (range(ny, nx) && m[ny][nx] != '@') {
            path.add(v[depth].first);
            dfs(v, path, new pair (ny, nx), depth + 1);
            path.remove(path.size() - 1);
        }

        ny = now.first + dy[v[depth].second];
        nx = now.second + dx[v[depth].second];
        if (range(ny, nx) && m[ny][nx] != '@') {
            path.add(v[depth].second);
            dfs(v, path, new pair (ny, nx), depth + 1);
            path.remove(path.size() - 1);
        }

    }
    public static class pair {
        int first;
        int second;
        pair (int f, int s) {
            this.first = f;
            this.second = s;
        }
    }
}
