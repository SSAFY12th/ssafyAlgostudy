import java.io.*;
import java.util.*;

public class Main {

    static int L; // 정수1
    static int C; // 정수2
    static char[] arr;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        visited = new boolean[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        dfs(0, 0);
    }

    public static void dfs(int start, int count) {
        if (count == L) {
            String word = "";
            int a = 0;
            int b = 0;
            for (int i = 0; i < C; i++) {
                if (visited[i]) {
                    word += arr[i];

                    if (arr[i]=='a' || arr[i]=='e' || arr[i]=='i' || arr[i]=='o' || arr[i]=='u') {
                        a++;
                    } else {
                        b++;
                    }
                }
            }
            if (a >= 1 && b >= 2) {
                System.out.println(word);
            }
        }
        for(int i = start; i < C; i++){
            visited[i] = true;
            dfs(i+1, count+1);
            visited[i] = false;
        }
    }
}
