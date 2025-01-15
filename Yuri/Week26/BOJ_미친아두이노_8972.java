import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static Point jongsu;
    static ArrayList<Point> aduino = new ArrayList<>();
    static ArrayList<Point> removePoint = new ArrayList<>();

    static int[] dr = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dc = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true; // 동일 객체 참조
            if (o == null || getClass() != o.getClass()) return false; // 타입 검사
            Point p = (Point) o;
            return r == p.r && c == p.c; // r과 c 비교
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c); // r과 c 기반 해시값 생성
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 종수의 로봇은 따로 저장하고, 나머지 아두이노는 리스트에 위치 저장
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = s.charAt(j);
                if(c == 'I')
                    jongsu = new Point(i, j);
                else if(c == 'R') {
                    aduino.add(new Point(i, j));
                }
            }
        }

        String dir = br.readLine();

        for (int i = 0; i < dir.length(); i++) {
            if(!move(dir.charAt(i) - '0')) {  // 이동했을 때 종수가 졌다면
                System.out.println("kraj "+(i+1));
                return;
            }
        }

        // 출력을 위해 map을 전부 .으로 세팅
        char[][] map = new char[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(map[i], '.');
        }

        map[jongsu.r][jongsu.c] = 'I';  // 종수 위치 표시

        for (Point p : aduino)          // 남아있는 아두이노 위치 표시
            map[p.r][p.c] = 'R';

        // 스트링빌더에 map에 있는 값 저장 후 출력력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++)
                sb.append(map[i][j]);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean move(int dir) {
        // 종수 이동.
        jongsu.r += dr[dir];
        jongsu.c += dc[dir];

        if(aduino.contains(jongsu))  // 이미 아두이노가 있는 위치로 이동했다면
            return false;   // 게임오버


        // 아두이노들 이동.
        for (int k = 0; k < aduino.size(); k++) {
            Point p = aduino.get(k);

            int d = 0;
            int min = 100000000;

            // 아두이노가 이동하는 최적의 방향을 찾는다.
            for (int i = 1; i <= 9; i++) {
                if(i == 5) continue;
                int newR = p.r + dr[i];
                int newC = p.c + dc[i];

                int cal = Math.abs(jongsu.r-newR) + Math.abs(jongsu.c-newC);
                if(cal < min) {
                    min = cal;
                    dir = i;
                }
            }

            p.r += dr[dir];
            p.c += dc[dir];

            // 아두이노가 종수 위치로 이동했다면
            if(jongsu.equals(p)) 
                return false;    // 게임오버

            for (int i = 0; i < k; i++) {
                if(aduino.get(i).equals(p)) {
                    // 아두이노끼리 겹치는게 있다면 제거
                    // 이미 이동했던 아두이노 중에서만 찾는다.
                    removePoint.add(p);
                    removePoint.add(aduino.get(i));
                    break;
                }
            }

        }

        for (Point p : removePoint)
            aduino.remove(p);

        removePoint.clear();

        return true;
    }

}
