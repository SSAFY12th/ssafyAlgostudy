import java.util.*;
public class Main {
    public static int answer = 0;
    public  static int N;
    public static boolean visitedY[];
    public static boolean visitedRT[];
    public static boolean visitedRB[];
    public static void backTracking(int depth,int row){
        if(N == depth) {
            answer++;
            return;
        }

        for (int i=0;i<N;i++){
            if(!visitedY[i] && !visitedRT[row+i] && !visitedRB[row-i-1+N]){
                visitedY[i] = true; // 열 검사
                visitedRT[row+i] = true; // 우상 방향 대각선 검사
                visitedRB[row-i-1+N] = true; // 우하 방향 대각선 검사

                backTracking(depth+1,row+1);

                visitedY[i] = false;
                visitedRT[row+i] = false;
                visitedRB[row-i-1+N] = false;
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        visitedY = new boolean[N];
        visitedRT = new boolean[N*2-1];
        visitedRB = new boolean[N*2-1];
        backTracking(0,0);
        System.out.println(answer);
        sc.close();
    }
}
