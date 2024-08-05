import java.util.*;
import java.io.*;

public class Main{
	public static int N;
    public static int[] arr;
    public static int[] operator;
    public static int max = Integer.MIN_VALUE;
    public static int min = Integer.MAX_VALUE;
	public static void DFS(int depth,int num) {
		if(depth == N-1){
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for(int i=0;i<4;i++){
            if(operator[i]!=0){
                operator[i]--;
                if(i==0){
                    DFS(depth+1,num+arr[depth+1]);
                }else if(i==1){
                    DFS(depth+1,num-arr[depth+1]);
                }else if(i==2){
                    DFS(depth+1,num*arr[depth+1]);
                }else if(i==3){
                    DFS(depth+1,num/arr[depth+1]);  
                }
            }
          operator[i]++;
        }
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
		operator = new int[4];

		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
        for(int i=0;i<4;i++){
            operator[i] = Integer.parseInt(st.nextToken());
        }
        
		DFS(0,arr[0]);
        System.out.println(max+" "+min);
	}
}
