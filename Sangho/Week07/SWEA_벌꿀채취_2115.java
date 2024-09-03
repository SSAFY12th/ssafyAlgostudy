import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static StringTokenizer st;

    static int T;

    static int N,M,C;
    static int[][] board;

    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            board = new int[N][N];

            // 최대 값
            answer = Integer.MIN_VALUE;

            for(int r = 0; r < N; r++){
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < N; c++){
                    board[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            calculateHoney();

            System.out.println("#"+tc+" "+answer);
        }
    }
    // 최대 꿀 채취량 계산
    public static void calculateHoney(){
        // 각 위치에서
        int[][] maxHoney = new int[N][N - M + 1];

        // 각 위치에서 M개의 벌통 선택해서 최대 꿀량 저장
        for(int i = 0; i < N; i++){
            for(int j = 0; j <= N - M; j++){
                maxHoney[i][j] = getMaxHoney(i,j);
            }
        }

        // 두 일꾼의 겹치지 않는 꿀통을 고르는 과정
        // 해당 인덱스에서 M만큼 꿀통을 골랐을때 계산된 최대 꿀량만 고르면 끝
        for(int i1 = 0; i1 < N; i1++){
            for(int j1 = 0; j1 <= N - M; j1++){
                for(int i2 = i1; i2 < N; i2++){
                    int startJ = (i1 == i2) ? j1 + M : 0; // 같은 행일 경우 j1 + M 부터 시작
                    for(int j2 = startJ; j2 <= N - M; j2++){
                        int currentProfit = maxHoney[i1][j1] + maxHoney[i2][j2];
                        answer = Math.max(answer,currentProfit);
                    }
                }
            }
        }
    }

    // 특정 위치에서 M개의 벌통을 선택 했을 때 최대 꿀 양 계산하기
    public static int getMaxHoney(int r, int c){
        int[] selectedHoney = new int[M];

        for(int i = 0; i < M; i++){
            selectedHoney[i] = board[r][c+i];
        }

        return getMaxCombination(selectedHoney,0,0,0);
    }

    // 꿀통이 선택된 상황에서 그 꿀통에서 최대 값을 선택하는 메서드
    public static int getMaxCombination(int[] arr, int index, int currentSum, int currentHoney){
        if(currentSum > C){ // 현재 선택된 꿀의 양이 C를 초과하면 0 반환
            return 0;
        }
        if(index == arr.length){
            return currentHoney;
        }

        // 수영장 문제랑 비슷한 로직
        // 현재 인덱스를 선택하는 경우
        int select = getMaxCombination(arr, index + 1, currentSum + arr[index], currentHoney + arr[index] * arr[index]);

        // 현재 인덱스를 선택하지 않는 경우
        int skip = getMaxCombination(arr, index + 1, currentSum, currentHoney);

        return Math.max(select,skip);
    }

}


