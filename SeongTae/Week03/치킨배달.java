import java.util.*;
import java.io.*;

public class Main { // 이 문제는 시간초과가 관건이었음. DFS할 때 정해진 루트만 돌자(중복X)
    public static class Point{
        int r;
        int c;
        Point(int x,int y){
            this.r = x;
            this.c = y;
        }
    }
    public static int N;
    public static int M;   
    public static int min = Integer.MAX_VALUE;
    public static List<Point> houses = new ArrayList<>();
    public static List<Point> Chickens = new ArrayList<>();
    public static List<Point> selectedList = new ArrayList<>();

    static void distance(){
        int result = 0;
        int short_distance = Integer.MAX_VALUE;
        for(int i=0;i<houses.size();i++){
            for(int j=0;j<selectedList.size();j++){
                int d =(Math.abs(houses.get(i).r - selectedList.get(j).r) + 
                Math.abs(houses.get(i).c - selectedList.get(j).c));
                short_distance = Math.min(d,short_distance);
            }
            result+= short_distance;
            short_distance = Integer.MAX_VALUE;
        }
        min = Math.min(min,result);
    }
    static void DFS(int start, int depth){
        if(depth == M){
            distance();
            return;
        }
        for(int i= start;i<Chickens.size();i++){
            selectedList.add(Chickens.get(i));
            DFS(i+1,depth+1);
            selectedList.remove(selectedList.size()-1);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(arr[i][j] == 2){
                    Chickens.add(new Point(i, j));
                }
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(arr[i][j]== 1){
                    houses.add(new Point(i, j));
                }
            }
        }
        DFS(0,0);
        System.out.println(min);
    }
}
