import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;
    static int N, L, R;
    static int[][] board;
    static int answer;
    static Queue<int[]> q;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static List<int[]> points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        answer = 0;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            visited = new boolean[N][N];
            boolean isExit = true;
            
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!visited[r][c]) {
                        points = new ArrayList<>();
                        int population = BFS(r, c);

                        if (points.size() > 1) {
                            isExit = false; 
                            int newPopulation = population / points.size();
                            
                            for (int[] point : points) {
                                board[point[0]][point[1]] = newPopulation;
                            }
                        }
                    }
                }
            }

            answer++;
            if (isExit) {
                break; 
            }
        }

        System.out.println(answer - 1);
    }
    
    public static int BFS(int x, int y) {
        int population = board[x][y];
        q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        points.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int cx = temp[0];
            int cy = temp[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;

                int diff = Math.abs(board[cx][cy] - board[nx][ny]);
                if (L <= diff && diff <= R) {
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    population += board[nx][ny];
                    points.add(new int[]{nx, ny});
                }
            }
        }

        return population;
    }
}
