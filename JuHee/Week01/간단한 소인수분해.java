import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Solution {
         
    public static void main(String[] args) throws Exception{
             
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int T =  Integer.parseInt(br.readLine()); // 테스트 케이스
            ArrayList<String> arr = new ArrayList<>();
             
            for(int i=0; i<T; i++) {
                 
                int N = Integer.parseInt(br.readLine());
                int a = 0; // 2의 지수
                int b = 0; // 3의 지수
                int c = 0; // 5의 지수
                int d = 0; // 7의 지수
                int e = 0; // 11의 지수
                 
                 
                while(N!=1) {
                    if(N%11==0) {
                        N /= 11;
                        e++;
                    } else if(N%7==0) {
                        N /= 7;
                        d++;
                    } else if(N%5==0) {
                        N /= 5;
                        c++;
                    } else if(N%3==0) {
                        N /= 3;
                        b++;
                    } else if(N%2==0) {
                        N /= 2;
                        a++;
                    }
                }
                 
                String ans = a + " " + b + " "+ c + " "+ d + " "+ e;
                arr.add(ans);
            }
             
                 
            for(int tc=1; tc<=T; tc++) {
                System.out.println("#" + tc + " " + arr.get(tc-1));
            }
         
             
    }
             
}
