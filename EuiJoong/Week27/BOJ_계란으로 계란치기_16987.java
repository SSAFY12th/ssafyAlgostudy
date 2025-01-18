import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Egg {
        int durability;
        int weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }

    static int N, ans;
    static List<Egg> eggList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        eggList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggList.add(new Egg(d, w));
        }

        ans = 0;
        hit(0);
        System.out.println(ans);
    }

    private static void hit(int idx) {
        // 모든 계란을 다 시도했을 경우
        if (idx == N) {
            int broken = 0;
            for (Egg egg : eggList) {
                if (egg.durability <= 0) broken++;
            }
            ans = Math.max(ans, broken);
            return;
        }

        // 현재 계란이 깨져있는 경우
        if (eggList.get(idx).durability <= 0) {
            hit(idx + 1);
            return;
        }

        boolean anyHit = false;
        for (int i = 0; i < N; i++) {
            if (i == idx || eggList.get(i).durability <= 0) continue; // 칠 계란이 자기 자신이거나 이미 깨져있다면 패스~

            anyHit = true; // 칠 수 있는 계란이 있음

            // 두 계란 충돌
            eggList.get(idx).durability -= eggList.get(i).weight;
            eggList.get(i).durability -= eggList.get(idx).weight;

            // 다음 계란으로
            hit(idx + 1);

            // 상태 복원
            eggList.get(idx).durability += eggList.get(i).weight;
            eggList.get(i).durability += eggList.get(idx).weight;
        }

        // 칠 수 있는 계란이 없으면 다음 계란으로
        if (!anyHit) {
            hit(idx + 1);
        }

    }
}
