import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int map[][];
    static boolean visited[][];
    static int[] nearX = {-1, 0, 1, 0};
    static int[] nearY = {0, 1, 0, -1};

    private static class Node {
        int x, y;
        public Node(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //선언
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1][M + 1];
        map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String s = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = s.charAt(j-1) - '0';
            }
        }
        bfs();
        System.out.println(map[N][M]);
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(1, 1));
        visited[1][1] = true;
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            for (int i = 0; i < 4; i++) {
                int curNearY = curNode.y + nearY[i];
                int curNearX = curNode.x+ nearX[i];
                if (curNearX > 0 && curNearY > 0 && curNearY <= N && curNearX <= M && map[curNearY][curNearX] != 0 && visited[curNearY][curNearX] == false) {
                    queue.offer(new Node(curNearY, curNearX));
                    map[curNearY][curNearX] = map[curNode.y][curNode.x]+1;
                    visited[curNearY][curNearX] = true;
                }
            }
        }
    }
}
