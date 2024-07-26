import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] map;         // 처음 맵을 저장할 배열
    static int[][] copyMap;     // 바이러스 확산 확인을 위해 복사본을 넣어 둘 배열
    static List<Point> space = new ArrayList<>();   // 빈 공간을 미리 저장해 둘 ArrayList
    static List<Point> virus = new ArrayList<>();   // 바이러스 위치를 넣어둘 ArrayList
    static int[] dr = {-1, 0, 1, 0};    // 상하좌우 확인을 편하게 하기 위한 배열
    static int[] dc = {0, 1, 0, -1};
    static int max = 0;         // 안전 영역 최대값을 구하기 위한 변수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        copyMap = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) space.add(new Point(i, j));      // 빈공간 기록
                else if(map[i][j] == 2) virus.add(new Point(i, j)); // 바이러스 위치 기록
            }
        }

        wallDFS(0, 0);  // 벽세우기

        System.out.println(max);
    }

    public static void wallDFS(int cnt, int position) {
        if(cnt == 3) {  // 벽을 3개 세우면
            virusBFS(); // 바이러스를 퍼트린다.
            // 끝나면? copyMap을 이용하여 안전 영역의 max값을 구해야한다.
            int sum = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(copyMap[i][j] == 0) sum++;
                }
            }

            max = Math.max(max, sum);

            return;
        }

        // 빈공간 리스트에서 하나씩 차례대로 벽을 세움. (순열과 비슷)
        for(int i = position; i < space.size(); i++) {
            map[space.get(i).r][space.get(i).c] = 1;
            wallDFS(cnt+1, i+1);
            map[space.get(i).r][space.get(i).c] = 0;
        }

    }

    public static void virusBFS() {
        for (int i = 0; i < map.length; i++)    // 기존 map 배열을 복사하여 copyMap에 넣기.
            copyMap[i] = Arrays.copyOf(map[i], map[i].length);
        Queue<Point> queue = new LinkedList<>(virus);

        while(!queue.isEmpty()) {
            Point now = queue.poll();
            copyMap[now.r][now.c] = 2;

            // 바이러스의 상하좌우가 맵을 벗어나지 않으면서 빈 공간이면 큐에 추가
            for(int i = 0; i < 4; i++) {
                if(now.r+dr[i] >= 0 && now.r+dr[i] < n && now.c+dc[i] >= 0 && now.c+dc[i] < m && copyMap[now.r+dr[i]][now.c+dc[i]] == 0)
                    queue.add(new Point(now.r+dr[i], now.c+dc[i]));
            }
        }
    }
}

// 위치를 한번에 저장하는 객체를 만들기 위해 클래스 구현
class Point {
    int r;
    int c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
