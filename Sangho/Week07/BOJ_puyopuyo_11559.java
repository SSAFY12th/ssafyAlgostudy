import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringTokenizer st;

    static char[][] map = new char[12][6];

    static boolean[][] visited;

    static List<int[]> list = new ArrayList<>();

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int r = 0; r < 12; r++){
            String str = br.readLine();
            for(int c = 0; c < 6; c++){
                map[r][c] = str.charAt(c);
            }
        }

        while (true) {

            boolean flag = true;
            visited = new boolean[12][6];

            for(int r = 0; r < 12; r++){
                for(int c = 0; c < 6; c++){
                    // 방문 한적이 없는 뿌요를 만난다면
                    if(!visited[r][c] && map[r][c] != '.'){
                        // 뿌요 끼리 연결 되어있는지 확인 해보자고
                        checkConnect(r,c);
                    }
                    if(list.size() >= 4){
                        flag = false;
                        for(int[] a : list){
                            map[a[0]][a[1]] = '.';
                        }
                    }
                    list.clear();
                }
            }
            updateMap(map);

            if(flag){
                break;
            }
            else{
                answer++;
            }

        }

        System.out.println(answer);


    }

    // 뿌요 연결 여부를 체크하고 4개 이상이라면 떠트려
    // 탐색 좌표, 몇 번 이어졌는가
    public static void checkConnect(int r, int c){

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{r,c});
        list.add(new int[]{r,c});
        visited[r][c] = true;

        while (!q.isEmpty()){
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < 12 && ny < 6){
                    if(map[x][y] == map[nx][ny] && !visited[nx][ny]){
                        visited[nx][ny] = true;
                        q.add(new int[]{nx,ny});
                        list.add(new int[]{nx,ny});
                    }
                }
            }
        }

    }



    // 중력이 작용하다 뿌요가 내려와 !
    public static void updateMap(char[][] tempMap) {
        for (int c = 0; c < 6; c++) { // 열(column) 기준으로 탐색
            for (int r = 11; r >= 0; r--) { // 아래에서 위로 탐색
                if (tempMap[r][c] == '.') { // 빈 공간이면
                    int nr = r - 1; // 바로 위에 있는 뿌요를 찾음
                    while (nr >= 0 && tempMap[nr][c] == '.') { // 위로 올라가면서 뿌요를 찾음
                        nr--;
                    }
                    if (nr >= 0) { // 뿌요를 발견하면
                        tempMap[r][c] = tempMap[nr][c]; // 빈 칸으로 뿌요를 떨어뜨림
                        tempMap[nr][c] = '.'; // 떨어진 자리 빈칸으로 변경
                    }
                }
            }
        }
    }



    // 1. 뿌요 중력 반영하기
    // > 바닥을 만나거나, 다른 뿌요(같은 색의 뿌요를 만나도 멈추는건가봐용) 를 만날때 까지 아래로 향하셈
    // 2. 뿌요 연결 여부 체크하기
    // > BFS로 탐색해서 4개 이상가면 터뜨리자, 터뜨릴때마다 1연쇄
    // 3. 여러 그룹이 터지더라도 1 연쇄랍니다.
}
