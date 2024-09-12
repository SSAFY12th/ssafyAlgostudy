import java.io.*;
import java.util.*;
 
public class Solution {
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int T = Integer.parseInt(st.nextToken());
 
        for (int test_case =1; test_case <=T; test_case++) {
 
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
 
            int len = n/4;
 
            List<String> list = new ArrayList<>();
            HashSet<Integer> set = new HashSet<>();
 
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
 
            for (int i=0; i<n; i++) {
                String a = str.charAt(i)+"";
                list.add(a);
            }
 
            for (int i=0; i<len; i++) {
 
                for (int j=0; j<n; j+=len) {
                    String num = "";
 
                    for (int r = 0; r<len; r++) {
                        num += list.get(j+r);
                    }
 
                    int z = 1;
                    int sum = 0;
                    for (int p = num.length()-1; p >= 0; p--) {
 
                        char number = num.charAt(p);
 
                        if (number == 'A') {
                            sum += (z*10);
                        }else if (number == 'B') {
                            sum += (z*11);
 
                        }else if (number == 'C') {
                            sum += (z*12);
 
                        }else if (number == 'D') {
                            sum += (z*13);
 
                        }else if (number == 'E') {
                            sum += (z*14);
 
                        }else if (number == 'F') {
                            sum += (z*15);
 
                        }else {
                            sum += (z * (number-'0'));
                        }
                        z *= 16;
                    }
 
                    set.add(sum);
 
 
                }
 
                String s = list.get(0);
                list.remove(0);
                list.add(s);
            }
 
 
            ArrayList<Integer> integers = new ArrayList<>(set);
 
            Collections.sort(integers,Collections.reverseOrder());
             
            Integer integer = integers.get(k-1);
 
            System.out.println("#"+test_case+" "+ integer);
 
 
        }
    }
}
