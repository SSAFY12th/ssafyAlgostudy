import java.util.*;
import java.io.*;

public class Main{
    static int N;
    static int M;
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for(int i=0;i<TC;i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int res = M-N;
            int[] arrM = new int[N];
            int[] arrN = new int[N];
            for(int j=0;j<N;j++) {
            	arrM[j] = res+j+1;
            	arrN[j] = j+1;
            }
            
            for(int j=N-1;j>=0;j--) {
            	for(int k=N-1;k>=0;k--) {
            		if(arrM[j] % arrN[k] == 0) {
            			arrM[j] = arrM[j]/arrN[k];
            			arrN[k] = 1;
            		}
            	}
            }
            long mul1 = 1;
            long mul2 = 1;
            for(int j=0;j<N;j++) {
            	mul1 = mul1 * arrM[j];
            	mul2 = mul2 * arrN[j];
            }
            
            result = mul1/mul2;
            System.out.println(result);
            result = 0;
        }
    }       
}
