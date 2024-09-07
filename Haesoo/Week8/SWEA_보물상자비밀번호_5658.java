import java.io.*;
import java.util.*;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int eachCnt = N / 4; //  한 면에 들어갈 숫자 개수
            String s = br.readLine();
            Map<String, Integer> numbers = new HashMap<>();
            Character[] allnums = new Character[N];
 
            String eachnum = "";
            for (int i = 0; i < s.length(); i++) {
                allnums[i] = s.charAt(i);
                eachnum += s.charAt(i);
                if ((i + 1) % eachCnt == 0) {
                    numbers.put(eachnum, 1);
                    eachnum = "";
                }
            }
 
            for (int i = 0; i < eachCnt - 1; i++) { // (N / 4) - 1 번의 회전
                Character tempnum = allnums[0];
                for (int j = N - 1; j >= 1; j--) {
                    allnums[(j + 1) % N] = allnums[j];
                }
                allnums[1] = tempnum;
 
                String temp = "";
                for (int j = 0; j < N; j++) {
                    temp += allnums[j];
                    if ((j + 1) % eachCnt == 0) {
                        numbers.put(temp, 1);
                        temp = "";
                    }
                }
            }
 
            int[] finalnums = new int[28]; // 중복 없다고 가정하면 4개씩 최대 7번 회전해서 28개?
            int k = 0;
            for (String it : numbers.keySet()) {
                int a = (int) Integer.parseInt(it, 16);
                finalnums[k] = a;
                k++;
            }
            Integer[] tmp = Arrays.stream(finalnums).boxed().toArray(Integer[]::new);
            Arrays.sort(tmp, Collections.reverseOrder());
            System.out.println("#" + t + " " + tmp[K - 1]);
        }
    }
}
