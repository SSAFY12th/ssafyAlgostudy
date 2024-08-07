import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[][] map = new int[1000][1000];
    static Queue<Point> tomato = new LinkedList<>();  // 익은 토마토 위치를 큐에 처음부터 저장
    static int minDay = 0;
    static int yetTomato = 0;
    static StringTokenizer st;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j  = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) yetTomato++;
                if(map[i][j] == 1) tomato.add(new Point(i, j, 0));
            }
        }

        if(yetTomato == 0)  // 처음부터 모든 토마토가 익어있는 상태.
            System.out.println(0);
        else {
            bfs();

            if(yetTomato != 0)
                System.out.println(-1);
            else
                System.out.println(minDay);
        }
    }

    public static void bfs() {
        Point p;
        while(!tomato.isEmpty()) {
            p = tomato.poll();
            if(p.day == minDay) {
                minDay++;
            }
            for(int i = 0; i < 4; i++) {
                if(p.r+dr[i] >= 0 && p.r+dr[i] < n && p.c+dc[i] >= 0 && p.c+dc[i] < m) {
                    if(map[p.r+dr[i]][p.c+dc[i]] == 0) {
                        yetTomato--;
                        map[p.r+dr[i]][p.c+dc[i]] = 1;
                        tomato.add(new Point(p.r+dr[i], p.c+dc[i], minDay));
                    }
                }
            }
        }
        minDay--;
    }
}

class Point {
    int r;
    int c;
    int day;
    public Point(int r, int c, int day) {
        this.r = r;
        this.c = c;
        this.day = day;
    }
}
