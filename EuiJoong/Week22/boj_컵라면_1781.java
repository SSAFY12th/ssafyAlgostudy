import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 입력 데이터를 저장할 리스트
        List<int[]> problems = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int cupRamen = Integer.parseInt(st.nextToken());
            problems.add(new int[]{deadline, cupRamen});
        }

        // 데드라인 기준으로 오름차순 정렬, 데드라인이 같다면 컵라면 보상이 높은 순으로 정렬
        problems.sort((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        // 우선순위 큐(최소 힙)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] problem : problems) {
            int deadline = problem[0];
            int cupRamen = problem[1];

            // 우선순위 큐에 현재 문제의 컵라면 보상 추가
            pq.offer(cupRamen);

            // 큐의 크기가 데드라인을 초과하면 최소값 제거
            if (pq.size() > deadline) {
                pq.poll();
            }
        }

        // 큐에 남아 있는 값의 합이 최대 컵라면 수
        int maxCupRamen = 0;
        while (!pq.isEmpty()) {
            maxCupRamen += pq.poll();
        }

        System.out.println(maxCupRamen);
    }
}
