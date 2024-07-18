import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            //건물의 수와 건물 높이 배열에 입력받기
            int buildingCount = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] buildingHeight = new int[buildingCount];
            for (int i = 0; i < buildingHeight.length; i++) {
                buildingHeight[i] = Integer.parseInt(st.nextToken());
            }

            int ans = 0;
            //앞뒤 두영역 제외하고 탐색
            for (int i = 2; i < buildingHeight.length - 2; i++) {
                int tempArr[] = new int[5]; // 앞두칸 뒤두칸 이므로 크기 5의 새로운 배열 생성
                int tempIndex = 0;
                for (int j = i - 2; j < i + 3; j++) {
                   tempArr[tempIndex++] = buildingHeight[j]; //새로운 배열에 앞 두칸부터 뒤 두칸까지 요소 담아서
                }
                Arrays.sort(tempArr); // 정렬
                if (buildingHeight[i] == tempArr[4]) { //만약 현재 탐색 값과 범위 안의 값 중 최댓값이 같다면
                    ans += tempArr[4] - tempArr[3]; // 최댓값 - 그 다음 값을 ans에 누산!
                }
            }
            bw.write("#" + tc + " " + ans + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}