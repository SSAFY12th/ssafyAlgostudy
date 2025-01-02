import java.io.*;
import java.util.*;

public class Main {
    static int H, W;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0}; // 상,우,하,좌
    static int[] dy = {0, 1, 0, -1};
    static StringBuilder path = new StringBuilder();
    static int startX, startY, startDir;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        
        map = new char[H][W];
        visited = new boolean[H][W];
        
        // 맵 입력받기
        int totalCount = 0;
        for(int i = 0; i < H; i++) {
            String line = br.readLine();
            for(int j = 0; j < W; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == '#') totalCount++;
            }
        }
        
        // 모든 '#' 위치에서 시작해보기
        outerLoop:
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                if(map[i][j] == '#') {
                    for(int d = 0; d < 4; d++) {
                        // 해당 방향으로 2칸 연속으로 '#'이 있는지 확인
                        int nx1 = i + dx[d];
                        int ny1 = j + dy[d];
                        int nx2 = i + dx[d] * 2;
                        int ny2 = j + dy[d] * 2;
                        
                        if(isValid(nx1, ny1) && isValid(nx2, ny2) && 
                           map[nx1][ny1] == '#' && map[nx2][ny2] == '#') {
                            visited = new boolean[H][W];
                            startX = i;
                            startY = j;
                            startDir = d;
                            
                            StringBuilder tempPath = new StringBuilder();
                            path = tempPath;
                            int visitCount = checkPath(i, j, d);
                            
                            if(visitCount == totalCount) {
                                break outerLoop;
                            }
                        }
                    }
                }
            }
        }
        
        System.out.println((startX + 1) + " " + (startY + 1));
        System.out.println(getDirectionChar(startDir));
        System.out.println(path.toString());
    }
    
    // 경로를 따라가면서 방문 가능한 '#' 개수 세기
    static int checkPath(int x, int y, int dir) {
        int count = 1;
        visited[x][y] = true;
        
        while(true) {
            int nx1 = x + dx[dir];
            int ny1 = y + dy[dir];
            int nx2 = x + dx[dir] * 2;
            int ny2 = y + dy[dir] * 2;
            
            if(canMove(nx1, ny1, nx2, ny2)) {
                path.append('A');
                visited[nx1][ny1] = true;
                visited[nx2][ny2] = true;
                x = nx2;
                y = ny2;
                count += 2;
                continue;
            }
            
            int leftDir = (dir + 3) % 4;
            nx1 = x + dx[leftDir];
            ny1 = y + dy[leftDir];
            nx2 = x + dx[leftDir] * 2;
            ny2 = y + dy[leftDir] * 2;
            
            if(canMove(nx1, ny1, nx2, ny2)) {
                path.append('L');
                dir = leftDir;
                continue;
            }
            
            int rightDir = (dir + 1) % 4;
            nx1 = x + dx[rightDir];
            ny1 = y + dy[rightDir];
            nx2 = x + dx[rightDir] * 2;
            ny2 = y + dy[rightDir] * 2;
            
            if(canMove(nx1, ny1, nx2, ny2)) {
                path.append('R');
                dir = rightDir;
                continue;
            }
            
            break;
        }
        
        return count;
    }
    
    static boolean isValid(int x, int y) {
        return x >= 0 && x < H && y >= 0 && y < W;
    }
    
    static boolean canMove(int x1, int y1, int x2, int y2) {
        return isValid(x1, y1) && isValid(x2, y2) && 
               map[x1][y1] == '#' && map[x2][y2] == '#' && 
               !visited[x2][y2];
    }
    
    static char getDirectionChar(int dir) {
        switch(dir) {
            case 0: return '^';
            case 1: return '>';
            case 2: return 'v';
            case 3: return '<';
            default: return ' ';
        }
    }
}
