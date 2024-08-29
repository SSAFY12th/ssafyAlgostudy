import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    static  int N;
    static int[] res, arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N]; // 1~N까지의 수 담을 배열
        res = new int[N]; // 만들어진 순열 담을 배열
        visited = new boolean[N]; // 방문처리 할 배열

        for(int i = 1; i <= N; i++){
            arr[i - 1] = i;
        }

        perm(0);
    }

    static void perm(int depth) {

        if(depth == N){

            for(int i : res){
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for(int i = 0; i < N; i++){
            if(visited[i])
                continue;

            if (!visited[i]){
                visited[i] = true;
                res[depth] = i + 1;
                perm(depth + 1);
                visited[i] = false;
            }
        }
    }
}
