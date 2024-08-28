// next permutation을 구현한 풀이
import java.io.*;
import java.util.*;
public class Main {
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static boolean next_permutation(int[] a, int n){
        int i = n - 1;
        while (i > 0 && a[i - 1] >= a[i]) i -= 1;
        if (i <= 0) return false;
        int j = n - 1;
        while (a[j] <= a[i - 1]) j -= 1;
        swap(a, i - 1, j);
        j = n - 1;
        while (i < j) {
            swap(a, i, j);
            i += 1; j -= 1;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = i + 1;
        }
        do {
            for (int j = 0; j < N; j++) System.out.print(a[j] + " ");
            System.out.println();
        } while (next_permutation(a, N));
    }
}




/* visited를 이용한 순열로 풀이
import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        int[] arr = new int[n];
        int[] output = new int[n];
        boolean[] visited = new boolean[n];
 
        for(int i = 0; i < n; i++)
            arr[i] = i+1;
        perm(arr, output, visited, 0, n, n);        
    }
 
    static void perm(int[] arr, int[] output, boolean[] visited, int cnt, int n, int r) {
        if(cnt == r) {
            for(int i = 0; i < r; i++)
                System.out.print(output[i] + " ");
            System.out.println();
            return;
        }
    
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                output[cnt] = arr[i];
                perm(arr, output, visited, cnt + 1, n, r);    
                visited[i] = false;;
            }
        }
    }
 
    static void print(int[] arr, int r) {

    }
}
*/
