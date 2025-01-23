import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] eggs;
    static int maxBreak = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken()); // 내구도
            eggs[i][1] = Integer.parseInt(st.nextToken()); // 무게
        }

        solve(0, 0);
        
        System.out.println(maxBreak);
    }

    static void solve(int current, int brokenCount) {
        if (current == N) {
            maxBreak = Math.max(maxBreak, brokenCount);
            return;
        }

        // 현재 계란이 이미 깨졌으면 다음 계란으로
        if (eggs[current][0] <= 0) {
            solve(current + 1, brokenCount);
            return;
        }

        boolean noHit = true;
        for (int target = 0; target < N; target++) {
            if (target == current || eggs[target][0] <= 0) continue;
            noHit = false;
            // 계란 충돌
            eggs[current][0] -= eggs[target][1];
            eggs[target][0] -= eggs[current][1];
            // 깨진 계란 카운트
            int currentBroken = 0;
            if (eggs[current][0] <= 0) currentBroken++;
            if (eggs[target][0] <= 0) currentBroken++;
            // 다음 계란으로 재귀
            solve(current + 1, brokenCount + currentBroken);
            // 원상복구
            eggs[current][0] += eggs[target][1];
            eggs[target][0] += eggs[current][1];
        }
        // 다른 계란을 칠 수 없으면 다음 계란으로
        if (noHit) {
            solve(current + 1, brokenCount);
        }
    }
}
