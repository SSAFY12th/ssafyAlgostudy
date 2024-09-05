
import java.io.*;
import java.util.*;

public class 무선충전 {

    static List<Charger> chargers = new ArrayList<Charger>();
    static int[] dx = {0,-1,0,1,0};
    static int[] dy = {0,0,1,0,-1};
    static int m,a;
    static int[] moveA;
    static int[] moveB;
    static List<Charger> selectA= new ArrayList<>();
    static List<Charger> selectB= new ArrayList<>();
    static int firstManX = 0, firstManY = 0;
    static int secondManX = 9, secondManY = 9;
    static int result;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());


        for (int test_case =1; test_case <=T; test_case ++) {

            st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());

            moveA = new int[m];
            moveB = new int[m];
            chargers.clear();
            result = 0;
            firstManX = 0;
            firstManY = 0;
            secondManX = 9;
            secondManY = 9;

            st = new StringTokenizer(br.readLine());
            for (int i=0; i<m; i++) {
                moveA[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<m; i++) {
                moveB[i] = Integer.parseInt(st.nextToken());
            }

            for (int i=0; i<a; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                chargers.add(new Charger(y-1, x-1,d,p));
            }

            for (int z = -1; z < m; z++) {

                if (z != -1) {

                    // 1번 사람
                    firstManX += dx[moveA[z]];
                    firstManY += dy[moveA[z]];

                    // 2번 사람
                    secondManX += dx[moveB[z]];
                    secondManY += dy[moveB[z]];

                }

                selectA.clear();
                selectB.clear();

                for (Charger charger : chargers) {

                    // 1 사람이 가능한  충전소
                    if (Math.abs(charger.ax - firstManX) + Math.abs(charger.ay - firstManY) <= charger.c) {
                        selectA.add(charger);
                    }

                    // 2 사람이 가능한  충전소
                    if (Math.abs(charger.ax - secondManX) + Math.abs(charger.ay - secondManY) <= charger.c) {
                        selectB.add(charger);
                    }

                }

                if (selectA.isEmpty() && !selectB.isEmpty()) {

                    int max = 0;

                    for (Charger charger : selectB) {
                        max = Math.max(max, charger.power);
                    }
                    result += max;

                } else if (selectB.isEmpty() && !selectA.isEmpty()) {

                    int max = 0;

                    for (Charger charger : selectA) {
                        max = Math.max(max, charger.power);
                    }

                    result += max;

                } else if (selectA.isEmpty() && selectB.isEmpty()) {
                    continue;
                } else if (!selectA.isEmpty() && !selectB.isEmpty()){

                    int resultMax = 0;

                    for (Charger chargeA : selectA) {

                        for (Charger chargeB : selectB) {

                            int sum = 0;

                            if (chargeA.equals(chargeB)) {
                                sum += chargeA.power;
                            }else {
                                sum += chargeB.power;
                                sum += chargeA.power;
                            }

                            resultMax = Math.max(resultMax, sum);

                        }

                    }
                    result += resultMax;
                }

            }
            System.out.println("#"+test_case+" "+result);

        }

    }
}

class Charger {

    public int ax;
    public int ay;
    public int c;
    public int power;

    public Charger(int ax, int ay, int c, int power) {
        this.ax = ax;
        this.ay = ay;
        this.c = c;
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Charger charger = (Charger) o;
        return ax == charger.ax && ay == charger.ay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ax, ay);
    }

    @Override
    public String toString() {
        return "Charger{" +
                "ax=" + ax +
                ", ay=" + ay +
                ", c=" + c +
                ", power=" + power +
                '}';
    }
}
