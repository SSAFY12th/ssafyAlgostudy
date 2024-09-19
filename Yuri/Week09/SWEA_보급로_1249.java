import java.util.*;
import java.io.*;

public class Solution {
    // 시간 빼고 계산해야됨 (최단 경로 문제)

    static int[][] map; // 비용을 저장하는 맵
    static int[][] distance; // 각 칸까지의 최소 비용을 저장
    static boolean[][] visited; // 방문 여부를 저장
    static int N; // 맵의 크기
    static int[] dr = {-1, 1, 0, 0}; // 상하 이동을 위한 행 변화량
    static int[] dc = {0, 0, -1, 1}; // 좌우 이동을 위한 열 변화량

    // 우선순위 큐에서 사용할 포인트 클래스
    static class Point implements Comparable<Point> {
        int r, c, value; // 행, 열, 현재까지의 총 비용
        public Point(int r, int c, int value) {
            this.r = r; // 행
            this.c = c; // 열
            this.value = value; // 총 비용
        }

        // 비용 기준으로 비교 (오름차순)
        @Override
        public int compareTo(Point o) {
            return this.value - o.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine()); // 맵의 크기 입력
            map = new int[N][N]; // 맵 초기화
            distance = new int[N][N]; // 거리 배열 초기화
            visited = new boolean[N][N]; // 방문 배열 초기화

            // 맵에 비용 입력
            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = s.charAt(j) - '0'; // 각 칸의 비용을 정수로 변환
                }
            }

            // BFS 수행
            bfs(new Point(0, 0, 0));

            // 결과 저장 (거리 배열에서 도착지 값이 최종 결과)
            sb.append("#").append(t).append(" ").append(distance[N-1][N-1]).append("\n");
        }

        // 결과 출력
        System.out.println(sb.toString());
    }

    public static void bfs(Point first) {
        PriorityQueue<Point> pq = new PriorityQueue<>(); // 우선순위 큐 초기화
        pq.add(first); // 시작 포인트 추가
        visited[0][0] = true; // 시작 포인트 방문 처리

        while (!pq.isEmpty()) {
            Point now = pq.poll(); // 현재 포인트 가져오기

            // 도착점에 도달하면 종료
            if (now.r == N - 1 && now.c == N - 1) {
                return;
            }

            int moveR, moveC;
            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                moveR = now.r + dr[i]; // 새로운 행
                moveC = now.c + dc[i]; // 새로운 열

                // 경계 체크 및 방문 여부 확인
                if (moveR >= 0 && moveC >= 0 && moveR < N && moveC < N && !visited[moveR][moveC]) {
                    visited[moveR][moveC] = true; // 방문 처리
                    distance[moveR][moveC] = now.value + map[moveR][moveC]; // 새로운 비용 계산
                    pq.add(new Point(moveR, moveC, distance[moveR][moveC])); // 큐에 추가
                }
            }
        }
    }
}
