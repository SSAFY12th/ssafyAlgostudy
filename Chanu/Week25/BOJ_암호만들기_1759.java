	
import java.io.*;
import java.util.*;


public class Baek1759_2 {

    static int n;
    static int m;
    static char[] arr;
    static char[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new char[n];
        result = new char[m];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        back(0,0);

        System.out.println(sb.toString());

    }

    static void back(int depth, int start) {

        if (depth == m) {

            if (!check()){
                return;
            }
            sb.append(String.valueOf(result)).append("\n");
            return;
        }

        for (int i = start; i<n; i++) {
            result[depth] = arr[i];
            back(depth+1,i+1);

        }
    }

    static boolean check() {

        int a = 0;
        int b = 0;

        for (int i=0; i<m; i++) {

            if (result[i] == 'a' || result[i] == 'e' || result[i] == 'i' || result[i] == 'o' || result[i] == 'u' ) {
                a++;
            }else {
                b++;
            }
        }

        if (a >= 1 && b >= 2) {
            return true;
        }

        return false;

    }
}
