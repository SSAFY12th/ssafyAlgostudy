

import java.util.Scanner;

public class Baek1010 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int count = sc.nextInt();

        for (int i=0; i < count; i++) {
            int west = sc.nextInt();
            int east = sc.nextInt();

            if (west == east) {
                System.out.println(1);
                continue;
            }

            if ((east - west) < (east / 2) + 1) {
                west = east - west;
            }

            long sum = 1;

            for (int j = 0; j < west; j++) {
                sum*=east;
                east--;
            }
            System.out.println(sum / fac(west));

        }

    }

    static long fac (int num) {

        if (num == 1 ){
            return 1;
        }

        return num * fac(num-1);

    }

}
