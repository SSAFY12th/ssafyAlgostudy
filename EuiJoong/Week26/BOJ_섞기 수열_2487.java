import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 모든 싸이클의 길이의 최소 공배수를 구하는 문제
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 싸이클을 찾아내기 위한 방문 체크 배열
        boolean[] visited = new boolean[N + 1];
        long lcm = 1;

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                int cycleLength = 0;
                int current = i;

                // 싸이클의 길이 계산
                while (!visited[current]) {
                    visited[current] = true;
                    current = numbers[current];
                    cycleLength++;
                }

                lcm = lcm(lcm, cycleLength);
            }
        }

        System.out.println(lcm);
    }

    /**
     * 유클리드 호제법은 2개의 자연수의 최대 공약수를 구하는 알고리즘 중 하나
     * 과정
     * 1. 2개의 자연수 a, b에 대해서 a를 b로 나눈 나머지를 r이라 하면 (단, a > b), a와 b의 최대 공약수는 b와 r의 최대 공약수와 같다.
     * 2. 이 과정을 반복하여 나머지가 0이 ㅗ디었을 때 나누는 수가 a와 b의 최대공약수이다.
     */
    // 유클리드 호제법 (최대 공약수 계산)
    private static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    /**
     * 두 수 a와 b의 최소공배수는 a와 b의 곱을 a와 b의 최대공약수로 나눈 것과 같다.
     */
    // 최소 공배수 계산
    private static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
}
