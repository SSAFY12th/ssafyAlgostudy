import java.io.*;
import java.util.*;

public class Main {

    static int N,K,L;
    static int[] arr = new int[3];
    static int sum;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        NextCase : for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            arr[0] = Integer.parseInt(st.nextToken());
            arr[1] = Integer.parseInt(st.nextToken());
            arr[2] = Integer.parseInt(st.nextToken());

            sum = 0;
            for(int j = 0; j < 3; j++){
                if(arr[j] >= L){
                    sum += arr[j];
                }else{
                    continue NextCase;
                }
            }

            if(sum >= K){
                cnt++;
                for(int k = 0; k < arr.length; k++){
                    sb.append(arr[k] + " ");
                }
            }
        }
        System.out.println(cnt);
        System.out.println(sb);
    }
}
