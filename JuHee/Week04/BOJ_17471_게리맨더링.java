import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static int N;
	static int[] people;
	static List<ArrayList<Integer>> graph;
	static boolean visited[];
	static boolean selected[];
	static int re = Integer.MAX_VALUE;
	
 	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		re = Integer.MAX_VALUE;
		people = new int[N];
		selected = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());		
		for(int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) { 
				int next = Integer.parseInt(st.nextToken());
				graph.get(i).add(next-1);
			}
		}
		
		divide(0);
		if(re == Integer.MAX_VALUE) re = -1;
		
		System.out.println(re);
	}
 	
 	private static void divide(int cnt) {
 		if(cnt == N) {
 			List<Integer> aList = new ArrayList<>();
 			List<Integer> bList = new ArrayList<>();

 			for (int i = 0; i < N; i++) {
				if(selected[i]) aList.add(i); 
				else bList.add(i);
			}
 			
 			if(aList.size() == 0 || bList.size() == 0) return; 
 			

 			if(check(aList) && check(bList)) {
 				getPeople();
 			}
 			return;
 		}
 		
 		selected[cnt] = true;
 		divide(cnt+1);
 		selected[cnt] = false;
 		divide(cnt+1);
 	}

 	private static boolean check(List<Integer> list) {
 		Queue<Integer> q = new LinkedList<>();
 		visited = new boolean[N];
 		visited[list.get(0)] = true;
 		q.offer(list.get(0));
 		
 		int cnt = 1;
 		while(!q.isEmpty()) {
 			int cur = q.poll();
 			for (int i = 0; i < graph.get(cur).size(); i++) {
 				int y = graph.get(cur).get(i);
 				if(list.contains(y) && !visited[y]) {
 					q.offer(y);
 					visited[y] = true;
 					cnt++;
 				}
			}
 		}
 		
 		if(cnt == list.size()) return true;
 		else return false;
 	}

 	private static void getPeople() {
 		int apeople = 0, bpeople = 0;
 		for(int i = 0; i < N; i++) {
 			if(selected[i]) apeople+=people[i];
 			else bpeople += people[i];
 		}
 		int sub = Math.abs(apeople-bpeople);
 		re = Math.min(re, sub);
 		
 	}
}
