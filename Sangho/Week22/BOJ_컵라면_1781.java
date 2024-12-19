import java.io.*;
import java.util.*;

public class Main {

    public static class Problem {
        int deadline;
        int reward;

        public Problem(int deadline, int reward) {
            this.deadline = deadline;
            this.reward = reward;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Problem[] problems = new Problem[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int reward = Integer.parseInt(st.nextToken());
            problems[i] = new Problem(deadline, reward);
        }

        // 마감일 내림차순 정렬 (같은 마감일 내 보상이 큰 순 정렬 !! )
        Arrays.sort(problems, (a, b) -> b.deadline == a.deadline ? b.reward - a.reward : b.deadline - a.deadline);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int maxDeadline = problems[0].deadline;
        int totalReward = 0;
        int index = 0;

        // 가장 큰 데드라인부터 1까지 역으로 ?
        for (int time = maxDeadline; time > 0; time--) {
            // 현재 시간에 풀 수 있는 모든 문제를 큐에 추가하기
            while (index < n && problems[index].deadline >= time) {
                pq.offer(problems[index].reward);
                index++;
            }

            // 큐에서 우선순위 제일 높은거 하나 get ~
            if (!pq.isEmpty()) {
                totalReward += pq.poll();
            }
        }

        System.out.println(totalReward);
    }
}


