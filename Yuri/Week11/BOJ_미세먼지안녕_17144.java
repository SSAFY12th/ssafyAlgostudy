import java.io.*;
import java.util.*;

public class Main {

    static int R;
    static int C;
    static int T;
    static int[][] map;
    static ArrayList<Point> vacuum = new ArrayList<>();
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int result;

    static class Point {
        int r;
        int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1)
                    vacuum.add(new Point(i, j));
            }
        }

        for(int i = 0; i < T; i++) {
            spread();
            cycle();
        }

        result = 0;

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] > 0)
                    result += map[i][j];
            }
        }

        System.out.println(result);

    }

    public static void spread() {
        int[][] spreadMap = new int[R][C];  // 확산 맵을 따로 만들어서 작업.

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] > 4) { // 확산이 이루어짐.
                    int amount = map[i][j]/5;
                    int cnt = 0;
                    int newR, newC;
                    for(int k = 0; k < 4; k++) {
                        newR = i+dr[k];
                        newC = j+dc[k];
                        if(newR >=0 && newC >=0 && newR <R && newC <C && map[newR][newC]!=-1) {
                            spreadMap[newR][newC] += amount;
                            cnt++;
                        }
                    }
                    spreadMap[i][j] += map[i][j] - amount*cnt;
                }
                else
                    spreadMap[i][j] += map[i][j];
            }
        }
        map = spreadMap;
    }

    public static void cycle() {
        int topR = 0;
        int bottomR = 0;
        int beforeTop = 0, beforeBottom = 0, tmpTop = 0, tmpBottom = 0;

        // 위쪽 순환
        topR = vacuum.get(0).r;

        // 아래쪽 순환
        bottomR = vacuum.get(1).r;

        // 오른쪽으로 이동 (두 순환 모두 처음에는 오른쪽으로 이동)
        for(int i = 1; i < C; i++) {
            // 현재 값을 다음에 넘기기 위해 저장.
            tmpTop = map[topR][i];
            tmpBottom = map[bottomR][i];
            // 이전 저장된 값을 가져와 현재 칸에 넣기.
            map[topR][i] = beforeTop;
            map[bottomR][i] = beforeBottom;

            beforeTop = tmpTop;
            beforeBottom = tmpBottom;
        }

        // 위쪽 순환 위로 이동.
        int topC = C-1;
        for(int i = topR-1; i >= 0; i--) {
            tmpTop = map[i][topC];
            map[i][topC] = beforeTop;
            beforeTop = tmpTop;
        }

        // 아래쪽 순환 아래로 이동.
        int bottomC = C-1;
        for(int i = bottomR+1; i < R; i++) {
            tmpBottom = map[i][bottomC];
            map[i][bottomC] = beforeBottom;
            beforeBottom = tmpBottom;
        }

        // 두 순환 모두 왼쪽으로 이동.
        topR = 0;
        bottomR = R-1;
        for(int i = C-2; i >=0; i--) {
            // 현재 값을 다음에 넘기기 위해 저장.
            tmpTop = map[topR][i];
            tmpBottom = map[bottomR][i];
            // 이전 저장된 값을 가져와 현재 칸에 넣기.
            map[topR][i] = beforeTop;
            map[bottomR][i] = beforeBottom;

            beforeTop = tmpTop;
            beforeBottom = tmpBottom;
        }

        // 위쪽 순환 아래로 이동
        topC = 0;
        for(int i = topR+1; i < vacuum.get(0).r; i++) {
            tmpTop = map[i][topC];
            map[i][topC] = beforeTop;
            beforeTop = tmpTop;
        }

        // 아래쪽 순환 위로 이동
        bottomC = 0;
        for(int i = bottomR-1; i > vacuum.get(1).r; i--) {
            tmpBottom = map[i][bottomC];
            map[i][bottomC] = beforeBottom;
            beforeBottom = tmpBottom;
        }

    }
}


// 처음 풀이
// import java.util.*;
// import java.io.*;

// public class Main {

//     static int R, C, T;
//     static int[][] map;
//     static Position[] vacuum = new Position[2];
//     static int result = 0;
//     static int[] dr = {-1, 0, 1, 0};
//     static int[] dc = {0, 1, 0, -1};

//     static class Position {
//         int r, c, value;
//         public Position(int r, int c, int value) {
//             this.r = r;
//             this.c = c;
//             this.value = value;
//         }
//     }

//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());

//         R = Integer.parseInt(st.nextToken());
//         C = Integer.parseInt(st.nextToken());
//         T = Integer.parseInt(st.nextToken());
//         map = new int[R][C];
//         int index = 0;
//         List<Position> list = new ArrayList<>();

//         for (int i = 0; i < R; i++) {
//             st = new StringTokenizer(br.readLine());
//             for (int j = 0; j < C; j++) {
//                 map[i][j] = Integer.parseInt(st.nextToken());
//                 if(map[i][j] == 0) continue;
//                 if(map[i][j] == -1)
//                     vacuum[index++] = new Position(i, j, -1);
//                 else if(map[i][j] > 0) {
//                     int value = map[i][j] / 5;
//                     if(value > 0) list.add(new Position(i, j, value));
//                 }
//             }
//         }

//         // 큐를 쓸까했지만.. 청소하는 순간 먼지의 위치가 바뀌기 때문에 굳이 큐를 써줄 이유가 없음.
        
//         for (int t = 0; t < T; t++) {
//             // 리스트 전부 참조
//             // 일단 퍼뜨리기.
//             // 퍼진 칸 만큼 현재 칸에서 빼기
//             // 리스트 참조 완료하면
//             for (Position p : list) {
//                 int newR, newC;
//                 int cnt = 0;
//                 for (int i = 0; i < 4; i++) {
//                     newR = p.r + dr[i];
//                     newC = p.c + dc[i];
//                     if(newR >= 0 && newC >= 0 && newR < R && newC < C && map[newR][newC] != -1) {
//                         cnt++;
//                         map[newR][newC] += p.value;
//                     }
//                 }
//                 map[p.r][p.c] -= p.value*cnt;
//             }

//             // 순환하기.
//             // 위쪽 공기청정기
//             int r = vacuum[0].r;
//             int c = vacuum[0].c+1;
//             int dir = 1;
//             int last = 0;

//             while(r!=vacuum[0].r || c!=vacuum[0].c) {
//                 while(r >= 0 && c >= 0 && r < R && c < C && map[r][c] != -1) {
//                     int tmp = map[r][c];
//                     map[r][c] = last;
//                     last = tmp;

//                     r += dr[dir];
//                     c += dc[dir];
//                 }
//                 dir = (dir+3)%4;
//                 if(r < 0) {
//                     r = 0;
//                     c--;
//                 }
//                 if(c >= C) {
//                     c = C-1;
//                     r--;
//                 }
//                 else if(c < 0) {
//                     c = 0;
//                     r++;
//                 }
//             }

//             // 아래쪽 공기청정기
//             r = vacuum[1].r;
//             c = vacuum[1].c+1;
//             dir = 1;
//             last = 0;
//             while(r!=vacuum[1].r || c!=vacuum[1].c) {
//                 while(r >= 0 && c >= 0 && r < R && c < C && map[r][c] != -1) {
//                     int tmp = map[r][c];
//                     map[r][c] = last;
//                     last = tmp;

//                     r += dr[dir];
//                     c += dc[dir];
//                 }
//                 dir = (dir+1)%4;
//                 if(r >= R) {
//                     r = R-1;
//                     c--;
//                 }
//                 if(c >= C) {
//                     c = C-1;
//                     r++;
//                 }
//                 else if(c < 0) {
//                     c = 0;
//                     r--;
//                 }
//             }

//             // 순환 끝나면 list에 다시 추가.
//             list.clear();
//             for (int i = 0; i < R; i++) {
//                 for (int j = 0; j < C; j++) {
//                     if(map[i][j] > 0) {
//                         int value = map[i][j] / 5;
//                         if(value > 0) list.add(new Position(i, j, value));
//                     }
//                 }
//             }

//         }

//         for (int i = 0; i < R; i++) {
//             for (int j = 0; j < C; j++) {
//                 int dust = map[i][j];
//                 if(dust > 0) result += dust;
//             }
//         }

//         System.out.println(result);

//     }
// }

