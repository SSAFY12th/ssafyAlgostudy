import java.util.*;
import java.io.*;

public class Main{
    static List<List<Integer>> all_area = new ArrayList<>();
    static List<Integer> area1 = new ArrayList<>();
    static List<Integer> area2 = new ArrayList<>();
    static Queue<Integer> queue = new LinkedList<>();
    static int[] people;
    static boolean[] visited;
    static boolean[] queue_visited;
    static int N;
    static int min = Integer.MAX_VALUE;

    public static int DFS(int depth , int start, int end){ // 조합 구하기
        if(depth == end){ // 각 영역에 포함될 원소의 개수 : end
			area2.clear();
            for(int i=0;i<visited.length;i++){ // 선택되지 않은 그룹은 area2에 포함
                if(!visited[i]){
                    area2.add(i+1);
                }
            }
			int bfs_area1 = BFS(area1); // 각 구역의 인구수 합 계산
			int bfs_area2 = BFS(area2);
            if(bfs_area1 != 0 && bfs_area2 != 0){
                return Math.abs(bfs_area1 - bfs_area2); // 둘 중에 0인 곳(연결X)이 하나도 없으면 
            }											// 두 구역의 인구수 차이값 반환
            else{
                return Integer.MAX_VALUE; // 아니면 MAX_VALUE 반환
            }
        }
        for(int i=start;i<N;i++){
            if(!visited[i]){
				visited[i] = true;
				area1.add(i+1);		
				int result = DFS(depth + 1,i + 1, end); // 중복X 인 조합을 위한 DFS 
				min = Math.min(result, min); // DFS결과로 최소값 갱신
				visited[i] = false;
				area1.remove(area1.size()-1); 
            }
        }
        return min;
    }
    public static int BFS(List<Integer> area){
        int result = 0;
        queue_visited = new boolean[N];
        queue.offer(area.get(0)); // 현재 영역의 첫 번째 원소를 큐에 삽입
		queue_visited[area.get(0)-1] = true; // queue_visited는 0부터 시작이므로 index-1
		int count = 1; // 처음 원소를 offer 했으므로 1부터 시작
            
        while(!queue.isEmpty()) {
            int index = queue.poll() - 1;
			result += people[index]; // 뽑아낸 영역의 인구 수를 result에 누적
			for (int next : all_area.get(index)) { // 각 구역의 연결성 확인
                if (!queue_visited[next-1] && area.contains(next)) {
                    queue.offer(next);
                    queue_visited[next-1] = true;
					count++;
                }
            }
        }
		if(count == area.size()){ // 영역의 크기와 연결된 영역의 개수가 같으면 result 반환
			return result;
		}
		else {
			return 0;
		}
  }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        people = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            people[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++) {
            List<Integer> list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int area_count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < area_count; j++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            all_area.add(list);
        }


        for(int i=0;i<N/2;i++){
            min = Math.min(min,DFS(0,0,i+1));
            Arrays.fill(visited,false); // DFS한번 하고나면 무조건 visited 초기화
            area1.clear(); // area1 초기화
            area2.clear(); // area2 초기화
        }
		if(min == Integer.MAX_VALUE){ // 최종 갱신된 min이 MAX_VALUE인 경우 : 두 구역으로 나눌 수 없는 경우
			min = -1; // -1로 min값 갱신
		}
		System.out.println(min);
    }
}
