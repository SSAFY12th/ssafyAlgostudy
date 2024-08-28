import java.util.Scanner;

public class Main {

    static int n, p[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        p = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = i+1;
        }

        do {
            for(int n : p)
                System.out.print(n+" ");
            System.out.println();
        } while(np());
    }

    public static boolean np() {
        int i = n-1;
        while(i > 0 && p[i-1] >= p[i]) --i;

        if(i == 0) return false;

        int j = n-1;
        while(p[i-1] >= p[j]) --j;

        swap(i-1, j);

        j = n-1;
        while(i < j) {
            swap(i++, j--);
        }

        return true;
    }

    public static void swap(int i, int j) {
        int tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
    }

}
