import java.util.*;
import java.io.*;

public class 컵라면 {
	static int N, answer;
	static class Problem implements Comparable<Problem>{
		public Problem(int deadLine, int cup) {
			this.deadLine = deadLine;
			this.cup = cup;
		}
		int deadLine;
		int cup;
		@Override
		public int compareTo(Problem o) {
			return this.deadLine - o.deadLine;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		answer = 0;
		Problem[] problems = new Problem[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			problems[i] = new Problem(deadline, cnt);
		}
		
		Arrays.sort(problems);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(Problem p : problems) {
			pq.offer(p.cup);
			
			if(pq.size() > p.deadLine) {
				pq.poll();
			}
		}
		
		while(!pq.isEmpty()) {
			answer += pq.poll();
		}
		
		System.out.println(answer);
		
	}
}
