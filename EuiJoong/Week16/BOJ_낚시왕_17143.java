import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Shark {
        int r;
        int c;
        int s;
        int d;
        int z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public String toString() {
            return z + "";
        }
    }

    static int R, C, M, ans;
    static List<Shark>[][] map, tmpMap;
    static int[][] dir = {
            {},
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1}
    };
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new ArrayList[R][C];
        tmpMap = new ArrayList[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = new ArrayList<>();
                tmpMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            map[r][c].add(new Shark(r, c, s, d, z));
        }
        //낚시 시작
        ans = 0;
        int fishKingPosition = -1;
        
        while (fishKingPosition < C - 1) {

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    tmpMap[i][j] = new ArrayList<>();
                }
            }
            
            fishKingPosition++;
            //가장 가까운 상어 낚기
            fishingShark(fishKingPosition);
            //상어 이동
            moveShark();
            //상어 합치기
            sharkMeal();
            copyMap();
        }
        System.out.println(ans);
    }

    private static void fishingShark(int fishKingPosition) {
        for (int r = 0; r < R; r++) {
            if (!map[r][fishKingPosition].isEmpty()) {
                ans += map[r][fishKingPosition].get(0).z;
                map[r][fishKingPosition].clear();
                return;
            }
        }
    }

    private static void moveShark() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!map[i][j].isEmpty()) {
                    Shark shark = map[i][j].get(0);

                    int r = shark.r;
                    int c = shark.c;
                    int speed = shark.s;
                    int direction = shark.d;
                    int size = shark.z;

                    int moveDistance = 0;
                    if (direction <= 2) {
                        moveDistance = speed % (2 * (R - 1));
                    } else {
                        moveDistance = speed % (2 * (C - 1));
                    }

                    for (int k = 0; k < moveDistance; k++) {
                        if (direction == 1) {
                            if (r == 0) {
                                direction = 2;
                                r++;
                            } else {
                                r--;
                            }
                        } else if (direction == 2) {
                            if (r == R - 1) {
                                direction = 1;
                                r--;
                            } else {
                                r++;
                            }
                        } else if (direction == 3) {
                            if (c == C - 1) {
                                direction = 4;
                                c--;
                            } else {
                                c++;
                            }
                        } else if (direction == 4) {
                            if (c == 0) {
                                direction = 3;
                                c++;
                            } else {
                                c--;
                            }
                        }
                    }
                    tmpMap[r][c].add(new Shark(r, c, speed, direction, size));
                }
            }
        }
    }

    private static void sharkMeal() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (tmpMap[i][j].size() >= 2) {
                    Shark sharkKing = null;
                    int maxSize = 0;
                    for (Shark shark : tmpMap[i][j]) {
                        if (shark.z > maxSize) {
                            maxSize = shark.z;
                            sharkKing = shark;
                        }
                    }
                    tmpMap[i][j].clear();
                    tmpMap[i][j].add(sharkKing);
                }
            }
        }
    }

    private static void copyMap() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = tmpMap[i][j];
            }
        }
    }
}
