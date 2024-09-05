import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static int N,M,C;
    static int[][] map;
    static int[] selected;
    static int sumA,sumB; // A
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine()," ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            selected = new int[2];
            answer = 0;

            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine()," ");
                for(int j=0;j<N;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(0,0);
            System.out.printf("#%d %d \n",t,answer);
        }
    }
    static void dfs(int depth,int idx){
        if(depth == 2){
            if(selected[0] >= selected[1] - (M-1)) return;
            if((selected[0] + M - 1) / N != selected[0] / N ||  (selected[1] + M - 1) / N  != selected[1] / N) return;
            int honey = getHoney(selected[0],selected[1]);
            answer = Math.max(answer,honey);
            return;
        }

        for(int i=idx;i<N*N; i++){
            selected[depth] = i;
            dfs(depth+1,i+1);
        }
    }
    static int getHoney(int a,int b){
        sumA = 0;
        for(int r=1;r<=M;r++){
            combination(a,a+M,r,0,0,0,1);
        }
        sumB = 0;
        for(int r=1;r<=M;r++){
            combination(b,b+M,r,0,0,0,2);
        }

        return sumA + sumB;
    }
    static void combination(int start,int end,int r,int depth,int sum,int rev,int type){
        if(depth == r){
            if(sum > C) return;
            if(type == 1) sumA = Math.max(sumA,rev);
            else sumB = Math.max(sumB,rev);
            return;
        }
        for(int i=start;i<end;i++){
            int h = map[i/N][i%N];
            combination(i+1,end,r,depth+1,sum + h,rev + (int) Math.pow(h,2),type);
        }
    }


}
