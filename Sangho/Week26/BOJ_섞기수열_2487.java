import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // n개의 카드
        int[] perm = new int[n + 1]; // 순열 저장 배열 (1-based index 사용)
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            // 섞기 수열 입력 받기
            perm[i] = Integer.parseInt(st.nextToken());
        }

        // 사이클 길이를 계산하는 함수
        boolean[] visited = new boolean[n + 1];
        int lcm = 1;

        // 1번 부터 n번 순회
        for (int i = 1; i <= n; i++) {
            // 이미 방문 했다면 이전에 탐색한 사이클에 있음
            if (!visited[i]) {
                int cycleLength = 0;
                int current = i;

                // 사이클이 있는지 탐색
                while (!visited[current]) {
                    visited[current] = true;
                    current = perm[current];
                    cycleLength++;
                }

                // 최대공배수 갱신
                lcm = lcm(lcm, cycleLength);
            }
        }

        // 최소 공배수 출력
        System.out.println(lcm);
    }

    // 최대공약수 계산
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // 최소공배수 계산
    private static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}


// 수열을 반복했을때 카드의 위치가 처음 상태로 돌아오는 최소위치 구하기
// 각 카드 별 사이클을 구하기
// 그 사이클들의 최소 공배수를 구하면 그게 돌아오는 주기
//
