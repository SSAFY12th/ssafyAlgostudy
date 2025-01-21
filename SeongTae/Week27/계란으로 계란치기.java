import java.util.*;
import java.io.*;

public class 계란 {
	static int N, max;
	static Egg[] eggs, temp;

	static class Egg {
		int durability;
		int weight;

		public Egg(int durability, int weight) {
			this.durability = durability;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		eggs = new Egg[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int dur = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			eggs[i] = new Egg(dur, weight);
		}

		max = Integer.MIN_VALUE;

		DFS(0, 0);

		System.out.println(max);
	}

	static void DFS(int start, int cnt) {
	    // 종료 조건
	    if (start == N) {
	        max = Math.max(max, cnt);
	        return;
	    }
	    
	    // 현재 계란이 깨진 경우
	    if (eggs[start].durability <= 0) {
	        DFS(start + 1, cnt);
	        return;
	    }
	    
	    // 현재 계란을 제외하고 나머지가 모두 깨진 경우
	    if(checkAnotherEgg(start, eggs)) {
	    	max = Math.max(max, N - 1);
	    	return;
	    }
	    // 다른 계란치기
	    for (int i = 0; i < N; i++) {
	        if (start != i && eggs[i].durability > 0) {
	            int broken = hit(eggs[start], eggs[i]);
	            DFS(start + 1, cnt + broken);
	            eggs[start].durability += eggs[i].weight;
	            eggs[i].durability += eggs[start].weight;
	        }
	    }
	}

	static int hit(Egg e, Egg e2) {
		e.durability -= e2.weight;
		e2.durability -= e.weight;
		if (e.durability <= 0 && e2.durability <= 0)
			return 2;
		else if (e.durability > 0 && e2.durability > 0)
			return 0;
		else
			return 1;
	}

	static boolean checkAnotherEgg(int index, Egg[] eggs) {
		for (int i = 0; i < index; i++) {
			if (eggs[i].durability > 0)
				return false;
		}

		for (int i = index + 1; i < N; i++) {
			if (eggs[i].durability > 0)
				return false;
		}
		return true;
	}
}
