import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringTokenizer st;

    static int N;

    // 지역의 정점 연결 정보 담음
    static ArrayList<Integer>[] graph = new ArrayList[11];
    
    // BFS로 지역 돌며 몇개로 지역이 구분되는지 판단함
    static Queue<Integer> q;

    // 각 지역의 인구수를 담는 배열
    static int[] population;

    // 팀을 나누기 위해 DFS 순회를 할텐데, 그때 팀 구분하는 배열
    static boolean[] visited;

    // 최소값임
    static int answer = Integer.MAX_VALUE;

    // 팀이 두개로 나누어지는 여부 판단
    static boolean answer_check = false;

    // 연결성 검증을 위한 BFS 탐색
    public static void BFS(){
        int count = 0;

        boolean[] bfsVisited = new boolean[N];

        q = new LinkedList<>();

        // 모든 정점 순회
        for(int i = 0; i < N; i++){

            // 아직 bfs에 걸리지 않았다면
            if(!bfsVisited[i]){
                // 큐에 추가하셈
                q.add(i);

                // 큐가 빌때 까지 돎
                while (!q.isEmpty()){
                    // 큐에서 하나 뽑음
                    int temp = q.poll();

                    // 해당 정점 방문 처리
                    bfsVisited[temp] = true;

                    // 그 정점에 연결 돼있는 정점 순회
                    for(int v : graph[temp]){
                        // 그 정점이 red 팀인데
                        if(visited[temp] && !bfsVisited[v]){
                            // 연결돼있는게 red팀이면 큐에 추가
                            if(visited[v]){
                                q.add(v);
                            }
                        }
                        // 그 정점이 blue 팀인데
                        if(!visited[temp] && !bfsVisited[v]){
                            // 연결돼있는게 blue팀이면 큐에 추가
                            if(!visited[v]){
                                q.add(v);
                            }
                        }
                    }
                }
                count++;
            }
        }

        // 팀이 두개로 나뉘어지면 점수 계산
        if(count == 2){
            answer_check = true;

            int redTeam = 0;
            int blueTeam = 0;
            int diff;

            for(int i = 0; i < N; i++){
                if(visited[i]){
                    redTeam += population[i];
                } else {
                    blueTeam += population[i];
                }
            }

            diff = Math.abs(redTeam - blueTeam);

            if(diff < answer) {
                answer = diff;
            }
        }
    }

    // 팀 조합 완전 탐색을 위한 DFS 탐색
    public static void DFS(int depth, int start, int limit){
        // limit => 팀의 개수
        if(depth == limit){
            BFS();
            return;
        }

        for(int i = start; i < N; i++){
            if(!visited[i]){
                visited[i] = true;
                DFS(depth + 1, i + 1, limit);
                visited[i] = false;
            }
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        population = new int[N];

        st = new StringTokenizer(bf.readLine());

        // 각 지역의 인구수를 입력 받음
        for(int i = 0; i < N; i++){
            population[i] = Integer.parseInt(st.nextToken());
        }

        // 각 그래프의 원소에 어레이 리스트 초기화
        for(int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        // 그래프 정보 입력 받음
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(bf.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            for(int j = 0; j < vertex; j++){
                int temp = Integer.parseInt(st.nextToken()) - 1;
                graph[i].add(temp);
            }
        }

        // 치킨 배달임
        // 1. 조합으로 N-x : x 팀 만들어보셈 (x > 0)
        // 1팀의 수 1 ~ n/2 넘어가면 같은 계산 또함
        for(int i = 1; i <= N / 2; i++) {
            visited = new boolean[N];
            // i vs N - i개의 팀 만드는거임
            DFS(0,0,i);
        }

        // 1. 조합으로 팀 나눔 
        // 2. BFS로 연결성 검증 
        // 3. 검증 됐으면 인원수 빼봐 최소값 비교해 갱신하셈 끝 ㅅㄱ
        
        if(answer_check){
            // 최소값 출력
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }
}
