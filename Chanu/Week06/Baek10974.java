import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static int[] result;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        result = new int[n];
        visited = new boolean[n];

        for(int i=0; i<n; i++){
            arr[i] = i+1;
        }

        rec(0);
        System.out.println(sb);
    }

    public static void rec(int depth){
        if(depth == n){
            for(int i=0; i<n; i++){
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i=0; i<n; i++){
            if(!visited[i]){
                visited[i] =true;
                result[depth] = arr[i];
                rec(depth+1);
                visited[i] =false;
            }
        }
    }
}
