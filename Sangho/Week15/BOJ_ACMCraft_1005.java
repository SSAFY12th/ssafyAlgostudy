import java.util.*;
import java.io.*;

public class Main {
	
	static StringTokenizer st;
	
	static int T;
	static int N, K;
	
	static int[] times;
	
	static int start, destination;
	
	static int object;
	
	static List<List<Integer>> graph;
	
	// 진입 차수 계산
	static int[] inDegree;
	
	static Queue<Integer> q;
	
	static long[] totalTime; // 각 건물을 완공하는 데 걸리는 총 시간

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}
			
			times = new int[N + 1];
			
			// 진입차수임
			inDegree = new int[N + 1];
			
			totalTime = new long[N + 1]; // 총 시간을 저장할 배열 초기화
			
			// 건물 건설 시간 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				times[i] = Integer.parseInt(st.nextToken());
			}
			
			// 건물 연결 관계 입력하기
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				start = Integer.parseInt(st.nextToken());
				destination = Integer.parseInt(st.nextToken());
				
				graph.get(start).add(destination);
				inDegree[destination]++;
			}
			
			// 목표 건물 좌표 입력 받기
			object = Integer.parseInt(br.readLine());
			
			// 위상 정렬 실행
			TopologicalSort();
			
			// 결과 출력: 목표 건물을 완공하는 데 걸리는 총 시간
			System.out.println(totalTime[object]);
		}
	}

	// 위상 정렬 진행하기
	public static void TopologicalSort() {
		q = new LinkedList<>();
		
		// 진입 차수가 0인 건물을 큐에 추가
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
				totalTime[i] = times[i]; // 건물 자체의 건설 시간으로 초기화
			}
		}
		
		while (!q.isEmpty()) {
			int current = q.poll();
			
			for (int neighbor : graph.get(current)) {
				inDegree[neighbor]--;
				
				// 다음 정점에 닿는 시간 vs 현재 정점까지의 시간 + 다음 정점의 진행시간 중에 짧은것을 선택
				totalTime[neighbor] = Math.max(totalTime[neighbor], totalTime[current] + times[neighbor]);
				
				// 진입 차수가 0이 되면 큐에 추가하는것이야
				if (inDegree[neighbor] == 0) {
					q.add(neighbor);
				}
			}
		}
	}
}
