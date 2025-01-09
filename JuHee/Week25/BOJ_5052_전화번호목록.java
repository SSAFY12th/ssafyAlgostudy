import java.io.*;
import java.util.*;

public class Main {

    static int t; // 테스트 케이스
    static int n; // 전화번호의 수
    static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            arr = new String[n];
            for(int j = 0; j < n; j++){
                arr[j] = br.readLine();
            }
            Arrays.sort(arr);

            if(isConsistent(n, arr)){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }

    public static boolean isConsistent(int n, String[] arr) {
        for(int i = 0; i < n-1; i++){
            if(arr[i+1].startsWith(arr[i])){ 
                return false;
            }
        }
        return true;
    }
}
