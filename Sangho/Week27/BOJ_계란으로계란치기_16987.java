import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;
    static int N;
    static Egg[] eggList;
    static int maxBroken = 0;

    // 계란 클래스
    public static class Egg {
        int durability;
        int weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        eggList = new Egg[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            eggList[i] = new Egg(durability, weight);
        }

        backtrack(0);

        System.out.println(maxBroken);
    }

    static void backtrack(int index) {
        // 모든 계란 탐색하면 계산
        if (index == N) {
            // 깨진 계란 수 계산 변수
            int brokenCount = 0;
            for (int i = 0; i < N; i++) {
                // 내구도 다 쓴 계란이면
                if (eggList[i].durability <= 0) {
                    brokenCount++;
                }
            }
            // 최대 깨진 계란 수 갱신
            maxBroken = Math.max(maxBroken, brokenCount);
            return;
        }

        // 현재 계란이 깨졌다면 다음 계란으로
        if (eggList[index].durability <= 0) {
            backtrack(index + 1);
            return;
        }

        // 부딪히고 난 후 깨졌는지 확인하는 변수
        boolean isBump = false;

        // 다른 계란들과 부딪히기 시도
        for (int i = 0; i < N; i++) {
            // 자기 자신 계란 제외, 이미 깨진 계란 제외
            if (i == index || eggList[i].durability <= 0) continue;

            // 기존 계란 내구도 보존
            int durability1 = eggList[index].durability;
            int durability2 = eggList[i].durability;

            // 부딪힌 후 계란 내구도 저장
            eggList[index].durability -= eggList[i].weight;
            eggList[i].durability -= eggList[index].weight;

            // 다른 계란과 부딪히는지 검증
            // 이미 뒤에 계란들이 다 깨졌으면 더 부딪힐게 없을수도 있잖아
            // true
            isBump = true;

            // 다음 계란 시도
            backtrack(index + 1);

            // 계란 내구도 복원 시키기
            eggList[index].durability = durability1;
            eggList[i].durability = durability2;
        }

        // 다른 계란과 부딪히지 못했더라도 다음계란으로 넘어가긴해야되잖아.
        if (!isBump) {
            backtrack(index + 1);
        }
    }
}
