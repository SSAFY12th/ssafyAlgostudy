import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int max = 0;
    static ArrayList<Egg> eggs = new ArrayList<>();
    static boolean[] breakEggs;

    static class Egg {
        int solid, weight;
        public Egg(int solid, int weight) {
            this.solid = solid;
            this.weight = weight;
        }
        public Egg clone() {
            return new Egg(this.solid, this.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        breakEggs = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int solid = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            eggs.add(new Egg(solid, weight));
        }

        dfs(0, Arrays.copyOf(breakEggs, breakEggs.length));

        System.out.println(max);

    }

    public static void dfs(int idx, boolean[] breakEgg) {

        int cnt = 0;
        for (int i = 0; i < n; i++)
            if(breakEgg[i]) cnt++;
        max = Math.max(cnt, max);

        if(idx >= n) return;
        if(breakEgg[idx]) {
            dfs(idx+1, Arrays.copyOf(breakEgg, breakEgg.length));
            return;
        }

        Egg now = eggs.get(idx).clone();

        for (int i = 0; i < n; i++) {
            if(breakEgg[i]) continue;
            if(i == idx) continue;

            Egg next = eggs.get(i).clone();

            // 그 계란을 깼다.
            eggs.get(idx).solid -= next.weight;
            eggs.get(i).solid -= now.weight;
            if(eggs.get(idx).solid <= 0) breakEgg[idx] = true;
            if(eggs.get(i).solid <= 0) breakEgg[i] = true;

            dfs(idx+1, breakEgg);

            breakEgg[idx] = false;
            breakEgg[i] = false;
            eggs.get(idx).solid = now.solid;
            eggs.get(i).solid = next.solid;

        }
    }
}
