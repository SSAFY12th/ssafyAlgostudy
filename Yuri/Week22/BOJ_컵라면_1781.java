import java.util.*;
import java.io.*;

public class Main {

    static class Study implements Comparable<Study> {
        int deadLine, cup;

        public Study(int deadLine, int cup) {
            this.deadLine = deadLine;
            this.cup = cup;
        }

        @Override
        public int compareTo(Study o) {
            if(this.cup != o.cup) return o.cup - this.cup;
            return o.deadLine - this.deadLine;
        }

    }

    static ArrayList<Study> studies = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int cup = Integer.parseInt(st.nextToken());
            studies.add(new Study(deadLine, cup));
        }

        studies.sort((Study s1, Study s2) -> {
            return s2.deadLine - s1.deadLine;
        });

        PriorityQueue<Study> pq = new PriorityQueue<>();

        int cnt = 0;
        long answer = 0;

        for(int i=200000;i>=1;i--) {
            while(cnt<studies.size() && studies.get(cnt).deadLine == i) {
                pq.add(studies.get(cnt++));
            }

            if(pq.isEmpty()) continue;

            answer+=pq.poll().cup;
        }

        System.out.println(answer);

    }
}
