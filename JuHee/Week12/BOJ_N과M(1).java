// 순열 구하기
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] currentPermutation = new int[M];
        boolean[] used = new boolean[N + 1];

        Perm(1, N, M, 0, currentPermutation, used);
    }

    public static void Perm(int start, int n, int m, int index, int[] current, boolean[] used) {
        if(index == M){
            for(int i = 0; i < current.length; i++){
                System.out.print(current[i]+" ");
            }
            System.out.println();
            return;
        }
        for(int i = start; i <= N; i++){
            if(!used[i]){
                current[index] = i;
                used[i] = true;
                Perm(1, N, M, index+1, current, used);
                used[i] = false;
            }
        }
    }
}
