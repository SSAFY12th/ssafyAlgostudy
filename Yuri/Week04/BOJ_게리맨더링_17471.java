import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<Integer>[] area = new ArrayList[11];   // 선거구의 인구 및 연결된 정보를 저장하는 2차원 배열(처음부터 최대 n+1로 선언)
    static int n;   // 선거구의 개수
    static StringTokenizer st;
    static List<Integer> selected = new ArrayList<>();  // 선택한 선거구 저장
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;   // 선거구 연결 확인 시 방문 여부 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        area[0] = new ArrayList<>();    // area의 0번째에는 각 구역 별 인구 수 저장
        for(int i = 0; i < n; i++)
            area[0].add(Integer.parseInt(st.nextToken()));

        int num;
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            area[i] = new ArrayList<>();

            // i번째 (1부터 n까지)에 연결된 구역 저장
            for(int j = 1; j <= num; j++)
                area[i].add(Integer.parseInt(st.nextToken()));
        }

        // 종료조건을 여기서 정해줌.
        for(int i = 1; i <= n/2; i++){
            dfs(0, i, 1);
        }

        // 만약 min값이 전혀 변하지 않았다면 -1로 변경.
        if(min == Integer.MAX_VALUE)
            min = -1;

        System.out.println(min);
    }

    public static void dfs(int cnt, int fin, int position) {
        if(cnt == fin) {
            // cnt가 fin와 같아지면 그냥 무조건 여기 들어온다.
            // 만약 두 구역의 인구 차가 최솟값이라면 변경.
            // 두 구역이 유효한지 검사가 필요함. (각 구역끼리는 전부 이어져있어야 한다.)
            if(bfs()) { // 두 구역이 유효하다면
                int choice = 0;
                int another = 0;
                for(int i = 1; i <= n; i++) {
                    if(selected.contains(i))
                        choice += area[0].get(i-1);
                    else
                        another += area[0].get(i-1);
                }
                min = Math.min(min, Math.abs(choice-another));
            }
            return;
        }

        // 조합이랑 비슷함.
        for(int i = position; i <= n; i++) {
            selected.add(i);
            dfs(cnt+1, fin, i+1);
            selected.remove((Integer) i);
        }

    }

    public static boolean bfs() {
        Queue<Integer> choice = new LinkedList<>();
        Queue<Integer> another = new LinkedList<>();
        int count = 1;

        choice.add(selected.get(0));    // 선택한 구역 중 하나를 넣어줌.
        for(int i = 1; i <= n; i++) {   // 선택하지 않은 구역 중 하나를 넣어줌.
            if(!selected.contains(i)) {
                another.add(i);
                break;
            }
        }

        visited = new boolean[n+1];
        while(!choice.isEmpty()) {  // 선택한 구역이 모두 이어져 있는지 확인
            int n = choice.poll();
            visited[n] = true;
            // 구역끼리 이어져있으며 방문을 안했다면 큐에 추가.
            for(int i = 0; i < area[n].size(); i++) {
                if(selected.contains(area[n].get(i)) && !visited[area[n].get(i)]) {
                    count++;    // 방문 횟수를 ++
                    choice.add(area[n].get(i));
                    visited[area[n].get(i)] = true;
                }
            }
        }

        // 방문 횟수와 선택한 구역의 수가 같지 않으면 구역이 이어져 있지 않은 것으로 return false
        if(count != selected.size()) {
            return false;
        }
        else {  // 횟수가 같다면 선택하지 않은 구역의 이어짐도 확인
            visited = new boolean[n+1];
            count = 1;
            while(!another.isEmpty()) {
                int n = another.poll();
                visited[n] = true;
                for(int i = 0; i < area[n].size(); i++) {
                    if(!selected.contains(area[n].get(i)) && !visited[area[n].get(i)]) {
                        count++;
                        another.add(area[n].get(i));
                        visited[area[n].get(i)] = true;
                    }
                }
            }

            if(count != n - selected.size())
                return false;
        }
        return true;
    }
}
