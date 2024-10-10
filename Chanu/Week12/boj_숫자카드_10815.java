

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Baek10815 {

    static int n;
    static int m;
    static HashSet<Integer> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            set.add(Integer.parseInt(st.nextToken()));

        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<m; i++) {
            if (set.contains( Integer.parseInt(st.nextToken()))){
                sb.append(1);
            }else {
                sb.append(0);
            }
            sb.append(" ");
        }

        System.out.println(sb.toString());


    }
}
