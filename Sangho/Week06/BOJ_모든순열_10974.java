import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;

    static int N;

    static int[] number;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        number = new int[N];

        // 숫자배열 채우기
        for(int i = 0; i < N; i++){
            number[i]= i + 1;
        }

        do{
            for(int i = 0; i < N; i++){
                System.out.print(number[i]+" ");
            }
            System.out.println();
        }while (nextPermutation());


    }

    public static boolean nextPermutation() {
        int i = N - 1;

        // 뒤에서 부터 탐색해서 증가하는 부분 찾기
        while (i > 0 && number[i - 1] >= number[i]) {
            i--;
        }

        // i가 0보다 작아질때까지 while문을 돌았으면 5 4 3 2 1 끝까지 온거임 ㅅㄱ
        if (i <= 0) {
            return false;
        }

        int j = N - 1;

        // 피벗을 기준으로 오른쪽부터 피벗보다 큰 값의 인덱스를 찾는다
        while (number[j] <= number[i - 1]) {
            j--;
        }

        // 그거랑 바꿈
        swap(i - 1, j);

        // 피벗부터 맨끝까지 오름차순 정렬
        Arrays.sort(number, i, N);

        return true;
    }


    public static void swap(int x, int y){
        int temp = number[x];
        number[x] = number[y];
        number[y] = temp;
    }


}
