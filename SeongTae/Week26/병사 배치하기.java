import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] worriors = new int[N];

        st = new StringTokenizer(br.readLine());
        int cnt = 0;
        worriors[0] = Integer.parseInt(st.nextToken());
        for(int i = 1; i < N; i++){
            worriors[i] = Integer.parseInt(st.nextToken());
            if(worriors[i] < worriors[i - 1]){
                continue;
            } else {
                cnt++;
                worriors[i - 1] = worriors[i];
            }
        }
      
        //System.out.println(Arrays.toString(dp));
        System.out.println(cnt);
    }
}
