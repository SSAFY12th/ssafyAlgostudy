import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static int N;
    static int K;
    static char[] box;
    static int ans;
    static Set<Integer> numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            numbers = new HashSet<>();
            ans = 0;

            int rotate = N / 4;
            box = new char[N];
            String num = br.readLine();
            for (int i = 0; i < N; i++) {
                box[i] = num.charAt(i);
            }

            chkNum();
            for (int i = 0; i < rotate - 1; i++) {
                rotateArr();
                chkNum();
            }

            List<Integer> sorted = new ArrayList<>(numbers);
            Collections.sort(sorted, Collections.reverseOrder());
            ans = sorted.get(K - 1);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");


        }
        System.out.println(sb);
    }

    private static void rotateArr() {
        //오른쪽으로 돌려야 함
        char temp = box[N - 1];
        for (int i = N - 1; i >= 1; i--) {
            box[i] = box[i - 1];
        }
        box[0] = temp;
    }

    //16진수로 바꿔서 set에 넣는 메서드
    private static void chkNum() {

        for (int i = 0; i < N; i += N / 4) {

            String str = "";

            for (int j = i; j < i + N / 4; j++) {

                str += box[j];
            }
            //System.out.println(str);

            numbers.add(change(str));

        }

    }

    private static int change(String str) {

        return Integer.parseInt(str, 16);

    }
}