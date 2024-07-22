import java.util.Arrays;
import java.util.Scanner;

public class test {
    public static boolean[] selected;
    public static int []chosen;
    public static int []arr;
    public static int k;
    public static StringBuilder sb = new StringBuilder();
    public static void rec(int depth, int start) {
        if(depth == 0){
            for (int j : chosen) {
                sb.append(j).append(" ");
            }
            sb.append('\n');
            return;
        }


        for(int i=0;i<arr.length;i++){
            if (selected[i] || (i > 0 && arr[i] == arr[i - 1] && !selected[i-1])) {
                System.out.println(arr[i]);
                continue;
            }

            selected[i] = true;
            chosen[k] = arr[i];
            k++;
            rec(depth-1,i);
            k--;
            selected[i] = false;

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N  = sc.nextInt();
        int M  = sc.nextInt();
        k = 0;
        selected = new boolean[N];
        arr = new int[N];
        chosen = new int[M];

        for(int i=0;i<N;i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        rec(M,0);
        System.out.print(sb);
    }
}