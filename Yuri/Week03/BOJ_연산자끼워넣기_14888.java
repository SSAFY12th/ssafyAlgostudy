import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] value = new int[11];
    static int[] oper = new int[4];
    static StringTokenizer st;
    static long max = -1000000000;
    static long min = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력값 받아오기
        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            value[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++)
            oper[i] = Integer.parseInt(st.nextToken());

        dfs(1, value[0]);   // 첫 번째 수를 그대로 전달하기 때문에 cnt는 1부터 시작

        System.out.print(max+"\n"+min);
    }

    public static void dfs(int cnt, int sum) {
        if(cnt == n) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        int tmp = sum;  // dfs 호출이 끝나면 원래 값으로 되돌려야하기 때문에 tmp를 사용
        for(int i = 0; i < 4; i++) {
            if(oper[i] > 0) {
                switch (i) {    // 인덱스 값에 따라 연산자 적용
                    case 0: // 더하기
                        tmp += value[cnt];
                        break;
                    case 1: // 배기
                        tmp -= value[cnt];
                        break;
                    case 2: // 곱하기
                        tmp *= value[cnt];
                        break;
                    case 3: // 나누기
                        // 음수 나누기는 c++14의 기준과 java 기준이 같은듯.
                        tmp/=value[cnt];
                        break;
                }
                oper[i]--;  // 연산자를 사용했음을 표시
                dfs(cnt+1, tmp);
                oper[i]++;  // 연산자 사용 표시를 원복
                tmp = sum;
            }
        }
    }
}
