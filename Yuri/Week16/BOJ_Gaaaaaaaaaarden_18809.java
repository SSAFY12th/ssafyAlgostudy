import java.util.*;
import java.io.*;

public class Main {

    static int N, M, G, R, result;
    static int[][] map;
    static List<Point> positive = new ArrayList<>();
    static List<Point[]> combi = new ArrayList<>();
    static Set<List<List<Point>>> uniqueSplits = new HashSet<>();
    static Point[] combiTmp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Point[][] visited;

    static class Point {
        int r, c, color, time;
        public Point(int r, int c, int color, int time) {
            this.r = r;
            this.c = c;
            this.color = color;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        combiTmp = new Point[G+R];
        result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) positive.add(new Point(i, j, -1, 0));
            }
        }

        dfs(0, 0);

        splitCombinations(G);

        for (List<List<Point>> split : uniqueSplits) {
            List<Point> groupG = split.get(0);
            List<Point> groupR = split.get(1);
            Point[] now = new Point[G+R];
            int idx = 0;

            visited = new Point[N][M];

            for (Point p : groupG) {
                p.color = 3;
                now[idx++] = p;
                visited[p.r][p.c] = p;
            }

            for (Point p : groupR) {
                p.color = 4;
                now[idx++] = p;
                visited[p.r][p.c] = p;
            }

            bfs(now);

        }

        System.out.println(result);

    }

    public static void dfs(int cnt, int idx) {
        if(cnt == combiTmp.length) {
            combi.add(combiTmp.clone());
            return;
        }

        for (int i = idx; i < positive.size(); i++) {
            combiTmp[cnt] = positive.get(i);
            dfs(cnt+1, i+1);
        }
    }

    // 모든 조합에서 G와 R을 구분하기 위해 호출.
    public static void splitCombinations(int groupSizeG) {
        for (Point[] combination : combi) {
            splitCombination(combination, groupSizeG);
        }
    }

    // 조합을 G개와 R개 그룹으로 나누기
    private static void splitCombination(Point[] combination, int groupSizeG) {
        List<Point> group1 = new ArrayList<>();
        splitRecursively(combination, group1, groupSizeG, 0);
    }

    // 재귀적으로 두 그룹으로 나누기
    private static void splitRecursively(Point[] combination, List<Point> group1, int groupSizeG, int index) {
        if (group1.size() == groupSizeG) {  // G개 뽑으면
            List<Point> group2 = new ArrayList<>();
            for (Point point : combination) {
                if (!group1.contains(point)) {  // G에 포함되지 않는 것은
                    group2.add(point);          // R에 포함한다.
                }
            }

            // 더 작은 것을 앞에 둬서 set에 넣었을 때 중복이면 등록이 되지 않도록한다.
            List<List<Point>> split = new ArrayList<>();
            if (group1.get(0).r < group2.get(0).r) {
                split.add(new ArrayList<>(group1));
                split.add(new ArrayList<>(group2));
            } else {
                split.add(new ArrayList<>(group2));
                split.add(new ArrayList<>(group1));
            }
            uniqueSplits.add(split);
            return;
        }

        for (int i = index; i < combination.length; i++) {
            group1.add(combination[i]);
            splitRecursively(combination, group1, groupSizeG, i + 1);
            group1.remove(group1.size() - 1);
        }
    }



    public static void bfs(Point[] start) {
        int flower = 0;
        Queue<Point> q = new LinkedList<>();
        for (Point p : start) {
            q.add(p);
        }

        while(!q.isEmpty()) {

            Point p = q.poll();
            if(visited[p.r][p.c].color == -1) continue;

            int newR, newC;
            for (int i = 0; i < 4; i++) {
                newR = p.r+dr[i];
                newC = p.c+dc[i];

                if(newR >= 0 && newC >= 0 && newR < N && newC < M && map[newR][newC] >= 1) {    // 일단 맵에서 놓을 수 있음.
                    if(visited[newR][newC] == null) {
                        if(map[newR][newC] != 0) {
                            Point newP = new Point(newR, newC, p.color, p.time+1);
                            visited[newR][newC] = newP;
                            q.add(newP);
                        }
                    } else {
                        if(visited[newR][newC].color > 2 && visited[newR][newC].color != p.color && visited[newR][newC].time == p.time+1) {
                            // 이러면 꽃이 피고 더 이상 번식 x
                            visited[newR][newC].color = -1;
                            flower++;
                        }
                    }
                }
            }
        }

        result = Math.max(result, flower);

    }
}
