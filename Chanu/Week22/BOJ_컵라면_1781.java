

import java.io.*;
import java.io.*;

public class Baek1781 {
    static long answer = 0;
    static PriorityQueue<Node> queue = new PriorityQueue<>();
    static PriorityQueue<Integer> resultQueue = new PriorityQueue<>();
    static class Node implements Comparable<Node>{
        int prNum;
        int deadLine;
        int cupNoddle;
        public Node(int prNum, int deadLine, int cupNoddle) {
            this.prNum = prNum;
            this.deadLine = deadLine;
            this.cupNoddle = cupNoddle;
        }

        @Override
        public int compareTo(Node o) {

            // 데드라인은 작은 순 , 컵라면 수는 높은거를 기준으로 정렬
            int o1Dead = this.deadLine;
            int o2Dead = o.deadLine;

            int o1CupNum = this.cupNoddle;
            int o2CupNum = o.cupNoddle;

            if (o1Dead != o2Dead) {
                return  o1Dead - o2Dead;
            }

            return o2CupNum - o1CupNum;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "prNum=" + prNum +
                    ", deadLine=" + deadLine +
                    ", cupNoddle=" + cupNoddle +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            queue.add(new Node(i,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        while (!queue.isEmpty()) {
            int size = resultQueue.size();
            Node node = queue.poll();

            if (size < node.deadLine) {
                resultQueue.offer(node.cupNoddle);
            }else {

                if ( resultQueue.peek() <  node.cupNoddle) {
                    resultQueue.poll();
                    resultQueue.offer(node.cupNoddle);
                }

            }

        }

        while (!resultQueue.isEmpty()) {
            answer+=resultQueue.poll();
        }

        System.out.println(answer);

    }
}
