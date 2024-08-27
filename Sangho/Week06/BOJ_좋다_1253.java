import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Good {
    static StringTokenizer st;

    static int N;

    static int[] arr;

    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        answer = 0;

        // 숫자들 입력 받기
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 숫자 정렬해주기 !
        Arrays.sort(arr);

        for(int i = 0; i < N; i++){
            int left = 0;
            int right = N-1;
            while (true){
                // 왼쪽 포인터가 현재 나라면 하나 증가
                if(i == left) left++;
                // 오른쪽 포인터가 현재 나라면 하나 감소
                else if(i == right) right--;

                // 왼쪽 포인터가 오른쪽 포인터를 넘어가면 찾을수가 없다는 뜻이겠죠
                if(left >= right) break;

                // 포인터 인덱스 값을 더해봤는데 현재 값보다 크면? 오른쪽 포인터를 감소 해줘야겠죠 정렬 해뒀으니까
                if(arr[left] + arr[right] > arr[i]) right--;
                // 반대로 작으면 왼쪽 값을 키워줘야 겠죠
                else if(arr[left] + arr[right] < arr[i]) left++;
                // 답을 찾았어? 좋다 !
                else {
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);


    }
}
