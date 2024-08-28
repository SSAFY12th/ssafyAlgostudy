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
        while(i > 0 && p[i-1] >= p[i]) --i;  // 마지막에서부터 꼭대기 위치 찾기(앞 수보다 더 큰 뒤의 수)

        if(i == 0) return false;            // 없으면 false 

        int j = n-1;                        
        while(p[i-1] >= p[j]) --j;          // 마지막에서부터 i-1번째보다 크면서 가장 작은 수 찾기

        swap(i-1, j);                        // 교환

        j = n-1;              
        while(i < j) {
            swap(i++, j--);                  // 교환한 위치 바로 다음부터 오름차순 정렬
        }

        return true;
    }

    public static void swap(int i, int j) {
        int tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
    }
}
