import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int L, C;
    static char[] alphabet;
    static char[] res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alphabet = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alphabet[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alphabet);
        res = new char[L];
        combination(0, 0);
    }

    private static void combination(int idx, int depth) {
        if (depth == L) {
            int vowel = 0;
            int consonant = 0;

            for (char val : res) {
                if (val == 'a' || val == 'e' || val == 'i' || val == 'o' || val == 'u') vowel++;
                else consonant++;
            }

            if (vowel >= 1 && consonant >= 2) {
                for (char val : res) {
                    System.out.print(val);
                }
                System.out.println();
            }
            return;
        }

        for (int i = idx; i < C; i++) {
            res[depth] = alphabet[i];
            combination(i + 1, depth + 1);
        }
    }
}
