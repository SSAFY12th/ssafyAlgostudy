import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int l, c;
    static char[] alphabet;
    static char[] pick;
    static StringBuilder result = new StringBuilder();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        alphabet = new char[c];
        pick = new char[l];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < c; i++)
            alphabet[i] = st.nextToken().charAt(0);

        Arrays.sort(alphabet);

        combi(0, 0);

        System.out.println(result.toString());

    }

    public static void combi(int cnt, int idx) {
        if(cnt == l) {
            // 검증
            if(validation()) {
                sb.setLength(0);
                for (int i = 0; i < l; i++)
                    sb.append(pick[i]);
                result.append(sb).append("\n");
            }
            return;
        }
        if(idx == c) return;

        pick[cnt] = alphabet[idx];
        combi(cnt+1, idx+1);

        combi(cnt, idx+1);

    }

    public static boolean validation() {
        int moum = 0;
        int jaum = 0;

        for (int i = 0; i < l; i++) {
            if(isMoum(pick[i])) moum++;
            else jaum++;
        }

        if(moum > 0 && jaum > 1) return true;
        return false;
    }

    public static boolean isMoum(char c) {
        if(c == 'a' || c == 'e' || c=='i' || c=='o' || c=='u') {
            return true;
        }
        return false;
    }

}
