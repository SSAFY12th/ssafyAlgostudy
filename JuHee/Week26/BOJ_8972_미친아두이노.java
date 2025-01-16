import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

  static class Node {

    private int r, c;
    private boolean live;

    public Node(int r, int c) {
      this.r = r;
      this.c = c;
      this.live = true;
    }
  }

  static int R, C;
  static Node jong;
  static ArrayList<Node> mad = new ArrayList<>();
  static int[] dr = { 1, 1, 1, 0, 0, 0, -1, -1, -1 };
  static int[] dc = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = br.readLine();
    R = Integer.parseInt(input.split(" ")[0]);
    C = Integer.parseInt(input.split(" ")[1]);
    mad.add(new Node(0, 0));
    for (int r = 0; r < R; r++) {
      input = br.readLine();
      for (int c = 0; c < C; c++) {
        if (input.charAt(c) == 'I') {
          jong = new Node(r, c);
        } else if (input.charAt(c) == 'R') {
          mad.add(new Node(r, c));
        }
      }
    }
    input = br.readLine();
    for (int t = 0; t < input.length(); t++) {
      // 종수 아두이노의 이동
      int dir = Integer.parseInt(input.substring(t, t + 1)) - 1;
      jong.r += dr[dir];
      jong.c += dc[dir];
      // 이동 후의 미친 아두이노들의 위치를 임시로 저장하는 map, map[R][C] 에 아두이노의 번호를 저장
      int[][] tmp_map = new int[R][C];
      for (int i = 1; i < mad.size(); i++) {
        Node now = mad.get(i);
        // 죽은 아두이노일 경우 continue
        if (now.live == false) continue;
        int nd = nextDir(now.r, now.c);
        int nr = now.r + dr[nd];
        int nc = now.c + dc[nd];
        if (nr == jong.r && nc == jong.c) {
          // 이 아두이노의 다음 위치가 종수 아두이노와 일치할 경우, 해당 숫자를 프린트하고 함수 종료
          StringBuilder sb = new StringBuilder();
          sb.append("kraj ").append(t + 1);
          System.out.println(sb.toString());
          return;
        }
        if (tmp_map[nr][nc] == 0) {
          // 다음 위치에 아두이노가 없을 경우, 해당 칸에 아두이노 번호를 저장하고 아두이노 위치 이동
          tmp_map[nr][nc] = i;
          mad.get(i).r += dr[nd];
          mad.get(i).c += dc[nd];
        } else {
          // 다음 위치에 아두이노가 있을 경우, 해당 번호의 아두이노와 현재 탐색중인 아두이노 폭발 처리
          mad.get(i).live = false;
          mad.get(tmp_map[nr][nc]).live = false;
        }
      }
    }
    br.close();
    // 이동이 완료된 후의 위치 상태 프린트
    char[][] map = new char[R][C];
    map[jong.r][jong.c] = 'I';
    for (int i = 1; i < mad.size(); i++) {
      Node now = mad.get(i);
      if (now.live == false) continue;
      map[now.r][now.c] = 'R';
    }
    StringBuilder sb = new StringBuilder();
    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        if (map[r][c] == 'I') {
          sb.append('I');
        } else if (map[r][c] == 'R') {
          sb.append('R');
        } else {
          sb.append('.');
        }
      }
      sb.append('\n');
    }
    System.out.print(sb.toString());
  }

  // 현재 위치에서 종수 아두이노까지의 거리가 가장 작은 방향을 리턴하는 함수
  static int nextDir(int r, int c) {
    int dir = 0;
    int min_d = Integer.MAX_VALUE;
    for (int d = 0; d < 9; d++) {
      int tmp_d = Math.abs(r + dr[d] - jong.r) + Math.abs(c + dc[d] - jong.c);
      if (tmp_d < min_d) {
        min_d = tmp_d;
        dir = d;
      }
    }
    return dir;
  }
}
