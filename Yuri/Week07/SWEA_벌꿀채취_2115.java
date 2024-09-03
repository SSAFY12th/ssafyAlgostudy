import java.util.*;
import java.io.*;

public class Solution {

    // 두 일꾼은 반드시 다른 행에서 벌통을 선택해야한다. (그래야 테스트케이스 통과됨)
    static int n, m, c, map[][], max;
    static List<Integer> maxList = new ArrayList<>();
    static List<Integer> columnSum = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            maxList.clear();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 각 행에서 최대로 얻을 수 있는 값 구하기.
            for (int i = 0; i < n; i++)
                getMaxValue(i);

            // 만들어진 최대값 리스트에서 2개 선택하여 가장 큰 값 구하기.
            maxList.sort(Collections.reverseOrder());
            int result = maxList.get(0) + maxList.get(1);
            System.out.println("#"+test_case+" "+result);

        }
    }

    public static void getMaxValue(int r) {
        columnSum.clear();
        max = 0;

        for (int i = 0; i < n; i++) {
            columnSum.add(map[r][i]);
            if(columnSum.size() > m)
                columnSum.remove(0);
            if(columnSum.size() == m)
                calMax(0, 0, 0); // 부분집합 구해야됨.
        }

        maxList.add(max);
    }

    public static void calMax(int cnt, int sum, int sumC) {

        if(sumC > c) return;
        if(cnt == m) {
            max = Math.max(max, sum);
            return;
        }

        int n = columnSum.get(cnt);
        // 선택하거나
        calMax(cnt+1, sum+(n*n), sumC+n);
        // 선택하지 않거나
        calMax(cnt+1, sum, sumC);
    }
}
