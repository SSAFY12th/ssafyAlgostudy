import java.util.*;
import java.io.*;

public class Main {

    static LinkedList<Integer>[] saw = new LinkedList[4];   // 삽입삭제를 편하게 하기 위해 LinkedList 사용
    static int k, target, target_d;
    static int sum = 0;
    static int[] direction;
    static StringTokenizer st;
    static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력받기
        for(int i = 0; i < 4; i++) {
            s = br.readLine();
            saw[i] = new LinkedList<Integer>();
            for(int j = 0; j < 8; j++)
                saw[i].addLast(s.charAt(j)-'0');
        }

        k = Integer.parseInt(br.readLine());

        // 톱니 회전시키기
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            target = Integer.parseInt(st.nextToken());
            target_d = Integer.parseInt(st.nextToken());
            direction = new int[4];
            direction[target-1] = target_d;

            // 중심으로부터 오른쪽으로 이동하며 회전 확인
            for(int j = target-1; j < 3; j++) {
                if(saw[j].get(2) == saw[j+1].get(6)) {
                    break;
                }
                direction[j+1] = direction[j]*-1;
                    
            }

            // 중심으로부터 왼쪽으로 이동하며 회전 확인
            for(int j = target-1; j > 0; j--) {
                if(saw[j].get(6) == saw[j-1].get(2))
                    break;
                else
                    direction[j-1] = direction[j]*-1;
            }

            int tmp;
            for(int j = 0; j < 4; j++) {
                if(direction[j] == 1) {         // 시계방향
                    // 맨 뒤 요소를 맨 앞으로 삽입
                    tmp = saw[j].removeLast();
                    saw[j].addFirst(tmp);
                }
                else if(direction[j] == -1) {   // 반시계방향
                    // 맨 앞 요소를 맨 뒤로 삽입
                    tmp = saw[j].removeFirst();
                    saw[j].addLast(tmp);
                }
            }
        }

        // 점수계산
        if(saw[0].getFirst() == 1)
            sum+= 1;
        if(saw[1].getFirst() == 1)
            sum+= 2;
        if(saw[2].getFirst() == 1)
            sum+= 4;
        if(saw[3].getFirst() == 1)
            sum+= 8;

        System.out.println(sum);
    }
}
