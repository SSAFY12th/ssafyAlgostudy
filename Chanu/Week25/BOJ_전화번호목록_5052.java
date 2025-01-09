import java.io.*;
import java.util.*;

public class BOJ_5052 {

    static HashSet<String> hashSet = new HashSet<>();
    static int n;
    static String[] arr;
    static String answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int tc =0; tc<T; tc++) {

            st = new StringTokenizer(br.readLine());
            n= Integer.parseInt(st.nextToken());

            hashSet.clear();
            answer = "YES";
            arr = new String[n];

            for (int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i] = st.nextToken();
                hashSet.add(arr[i]);
            }

            loop:
            for (int i=0; i<n; i++) {
                String str = arr[i];
                for (int j=1; j<str.length(); j++) {
                    String substring = str.substring(0, j);

                    if (hashSet.contains(substring)) {
                        answer = "NO";
                        break loop;
                    }

                }
            }

            System.out.println(answer);


        }



    }
}
