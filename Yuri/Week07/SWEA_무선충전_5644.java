import java.util.*;
import java.io.*;

public class Solution {

    // 최댓값을 구해야함.
    static int m, a, moveA[], moveB[], chargeA[], chargeB[], max;
    static List<AP> APList = new ArrayList<>();
    static Person A, B;
    static int[] dx = {0, 0, 1, 0, -1}; // 이동x, 상, 우, 하, 좌
    static int[] dy = {0, -1, 0, 1, 0};
    static List<AP> possibleA = new ArrayList<>();
    static List<AP> possibleB = new ArrayList<>();

    static class Person {
        int x, y;
        public Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class AP {
        int number, x, y, range, power;
        public AP(int number, int x, int y, int range, int power) {
            this.number = number;
            this.x = x;
            this.y = y;
            this.range = range;
            this.power = power;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            moveA = new int[m+1];
            moveB = new int[m+1];
            chargeA = new int[m+1];
            chargeB = new int[m+1];
            APList.clear();
            A = new Person(1, 1);
            B = new Person(10, 10);

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= m; i++)
                moveA[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= m; i++)
                moveB[i] = Integer.parseInt(st.nextToken());

            for (int i = 0; i < a; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int range = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());
                APList.add(new AP(i+1, x, y, range, power));
            }

            move();

            int sum = 0;
            for (int n : chargeA)
                sum += n;
            for (int n : chargeB)
                sum += n;

            System.out.println("#"+test_case+" "+sum);

        }
    }

    public static void move() {
        for (int i = 0; i <= m; i++) {
            // 현재 위치를 이동시킨다.
            A.x += dx[moveA[i]];
            A.y += dy[moveA[i]];
            B.x += dx[moveB[i]];
            B.y += dy[moveB[i]];

            // 각 위치에서 A와 B가 선택할 수 있는 충전기 종류를 모두 각각 저장한다.
            possibleA.clear();
            possibleB.clear();
            for (AP ap : APList) {
                int AD = Math.abs(A.x-ap.x) + Math.abs(A.y-ap.y);
                int BD = Math.abs(B.x-ap.x) + Math.abs(B.y-ap.y);
                if(AD <= ap.range)
                    possibleA.add(ap);
                if(BD <= ap.range)
                    possibleB.add(ap);
            }

            // 완탐을 이용하여 A와 B선택 중 가능한 모든 경우에서 sum이 최대인 값을 찾아 A와 B의 충전량 결정.
            max = 0;

            // A를 우선적으로 선택하는 경우
            for (int j = 0; j < possibleA.size(); j++) {
                AP apA = possibleA.get(j);
                AP apB = null;
                for (int k = 0; k < possibleB.size(); k++) {
                    // B를 선택할 수 있는경우...
                    apB = possibleB.get(k);
                    cal(apA, apB, i);
                }
                if(apB == null) // A에만 충전기가 있는 경우.
                    cal(apA, apB, i);
            }

            // B를 우선적으로 선택하는 경우.
            for (int j = 0; j < possibleB.size(); j++) {
                AP apA = null;
                AP apB = possibleB.get(j);
                for (int k = 0; k < possibleA.size(); k++) {
                    // A를 선택할 수 있는경우...
                    apA = possibleA.get(k);
                    cal(apA, apB, i);
                }
                if(apA == null) // B에만 충전기가 있는 경우.
                    cal(apA, apB, i);
            }
        }
    }

    public static void cal(AP apA, AP apB, int index) {
        int sum, selectANum, selectBNum;
        boolean same = false;

        if(apA == null) {
            sum = apB.power;
            selectBNum = apB.number;
            selectANum = 0;
        }
        else if(apB == null) {
            sum = apA.power;
            selectANum = apA.number;
            selectBNum = 0;
        }
        else if(apA.number == apB.number) { // A와 B가 같은 충전기를 사용하는 경우
            sum = apA.power;
            selectANum = apA.number;
            selectBNum = apB.number;
            same = true;
        }
        else {
            sum = apA.power + apB.power;
            selectANum = apA.number;
            selectBNum = apB.number;
        }

        if(sum > max) {
            max = sum;
            if(same) {  // A와 B가 같은 충전기를 사용하는 경우
                chargeA[index] = APList.get(selectANum-1).power / 2;
                chargeB[index] = chargeA[index];
            }
            else {
                if(selectANum > 0)
                    chargeA[index] = APList.get(selectANum-1).power;
                if(selectBNum > 0)
                    chargeB[index] = APList.get(selectBNum-1).power;
            }
        }

    }
}
