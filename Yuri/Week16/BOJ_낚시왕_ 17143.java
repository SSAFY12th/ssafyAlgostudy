import java.io.*;
import java.util.*;

public class Main {

    static int R, C, M;
    static int sizeSum = 0;
    static PriorityQueue<Shark> sharks = new PriorityQueue<>();

    static class Shark implements Comparable<Shark> {
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        public boolean samePosition(Shark o) {
            if(this.r == o.r && this.c == o.c)
                return true;
            else
                return false;
        }

        @Override
        public int compareTo(Shark o) { // 상어를 왼쪽에 있으면서 위에 있으면서 크기가 큰 순서로 정렬
            if(this.c != o.c)
                return this.c - o.c;
            else if(this.r != o.r)
                return this.r - o.r;
            return o.z - this.z;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());   // 행 (1부터 시작)
            int c = Integer.parseInt(st.nextToken());   // 열
            int s = Integer.parseInt(st.nextToken());   // 속력
            int d = Integer.parseInt(st.nextToken());   // 방향
            int z = Integer.parseInt(st.nextToken());   // 크기
            sharks.add(new Shark(r, c, s, d, z));
        }

        fishing();

        System.out.println(sizeSum);

    }

    public static void fishing() {
        for (int i = 1; i <= C; i++) {   // 낚시왕이 오른쪽으로 한칸씩 이동.

            int SIZE = sharks.size();
            PriorityQueue<Shark> pq = new PriorityQueue<Shark>();

            // 낚시왕이 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
            for (int j = 0; j < SIZE; j++) {

                Shark shark = sharks.poll();
                if(shark.c < i)
                    pq.add(shark);
                else if(shark.c == i) {
                    sizeSum += shark.z; // 상어를 잡은 경우 크기를 더함.
                    break;
                }
                else if(shark.c > i) {
                    // 낚시꾼보다 오른쪽에 있는 상어를 만났다면 종료
                    sharks.add(shark);
                    break;
                }
            }

            pushEle(pq);    // 잡지 않은 상어들은 원래 사용하던 pq에 넣어준다.
            moveShark();    // 상어가 이동한다!

        }
    }

    // 남은 상어들 원래 pq로 넣어주는 메서드
    public static void pushEle(PriorityQueue<Shark> pq) {
        while(!pq.isEmpty()) {
            sharks.add(pq.poll());
        }
    }

    public static void moveShark() {
        PriorityQueue<Shark> pq = new PriorityQueue<>();
        int SIZE = sharks.size();

        for (int i = 0; i < SIZE; i++) {
            Shark tmp = sharks.poll();
            int speed = tmp.s;  // 상어의 속력

            while(speed > 0) {  // 속력이 0이 될 때까지 (이동이 끝날때까지)
                if(tmp.d == 1) {    // 위쪽 방향인 경우
                    if(speed > tmp.r-1) {
                        speed -= tmp.r-1;
                        tmp.r = 1;
                        tmp.d = changeDir(tmp.d);
                    }
                    else {
                        tmp.r -= speed;
                        speed = 0;
                    }
                }
                else if(tmp.d == 2) {   // 아래쪽 방향인 경우
                    if(speed > R - tmp.r) {
                        speed -= (R - tmp.r);
                        tmp.r = R;
                        tmp.d = changeDir(tmp.d);
                    }
                    else {
                        tmp.r += speed;
                        speed = 0;
                    }
                }
                else if(tmp.d == 3) {   // 오른쪽 방향인 경우
                    if(speed > C - tmp.c) {
                        speed -= (C - tmp.c);
                        tmp.c = C;
                        tmp.d = changeDir(tmp.d);
                    }
                    else {
                        tmp.c += speed;
                        speed = 0;
                    }
                }
                else if(tmp.d == 4) {   // 왼쪽 방향인 경우
                    if(speed > tmp.c-1) {
                        speed -= tmp.c-1;
                        tmp.c = 1;
                        tmp.d = changeDir(tmp.d);
                    }
                    else {
                        tmp.c -= speed;
                        speed = 0;
                    }
                }
            }

            pq.add(tmp);

        }

        sharks = pq;

        pq = new PriorityQueue<>();
        Shark last = sharks.poll();

        while(!sharks.isEmpty()) {

            Shark shark = sharks.poll();

            // 같은 칸에 있으면서 크기가 작은 상어들은 모두 잡아먹힌다.
            if(last.samePosition(shark)) continue;
            else {
                pq.add(last);
                last = shark;
            }
        }

        if(last != null)
            pq.add(last);
        sharks = pq;

    }
    
    // 방향을 반대로 바꿔주는 메서드
    public static int changeDir(int d) {
        if(d == 1) return 2;
        if(d == 2) return 1;
        if(d == 3) return 4;
        if(d == 4) return 3;
        return d;
    }

}
