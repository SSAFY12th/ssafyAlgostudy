import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int min = 1000000000;
    static Point[] chicken = new Point[13];
    static Point[] choice = new Point[13];  // m개만큼 선택한 치킨집을 넣어 둘 배열
    static Point[] house = new Point[100];
    static int chickenIndex = 0;
    static int houseIndex = 0;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int token;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                token = Integer.parseInt(st.nextToken());
                if(token == 1)
                    house[houseIndex++] = new Point(i, j);
                else if(token == 2)
                    chicken[chickenIndex++] = new Point(i, j);
            }
        }

        dfs(0, 0);

        System.out.println(min);

    }

    static void dfs(int cnt, int position) {
        if(cnt == m) {
            // 계산 후 최솟값 구하기.
            int sum = 0;
            int houseMin;
            for(int i = 0; i < houseIndex; i++) {
                houseMin = 1000000000;
                for(int j = 0; j < m; j++) {
                    houseMin = Math.min(houseMin, cal(i, j));
                }
                sum+=houseMin;
            }
            min = Math.min(min, sum);
            return;
        }

        for(int i = position; i <chickenIndex; i++) {
            choice[cnt] = chicken[i];
            dfs(cnt+1, i+1);
        }
    }

    static int cal(int housePosition, int chickenPosition) {
        return Math.abs(house[housePosition].r-choice[chickenPosition].r)
                + Math.abs(house[housePosition].c-choice[chickenPosition].c);
    }
}

class Point {
    int r, c;
    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
