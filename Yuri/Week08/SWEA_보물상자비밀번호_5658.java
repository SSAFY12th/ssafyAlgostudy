import java.io.*;
import java.util.*;

public class Solution {

    static int n, k;
    static Set<Long> hexList = new HashSet<>();
    static LinkedList<Character> s = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // n: 숫자의 개수
            k = Integer.parseInt(st.nextToken()); // k: 찾을 k번째 큰 수
            hexList.clear();
            s.clear(); // s 리스트 초기화

            String tmp = br.readLine(); // 숫자 문자열 입력
            for (int i = 0; i < n; i++) {
                s.add(tmp.charAt(i)); // 숫자를 LinkedList에 추가
            }

            // n/4번 회전하면서 4자리씩 잘라서 16진수 변환
            for(int rotation = 0; rotation < n / 4; rotation++) {
                // 4자리씩 잘라서 처리
                for (int i = 0; i < n; i += n / 4) {
                    StringBuilder hex = new StringBuilder();
                    for (int j = i; j < i + n / 4; j++) {
                        hex.append(s.get(j)); // 4자리씩 붙이기
                    }
                    hexList.add(Long.parseLong(hex.toString(), 16)); // 16진수를 10진수로 변환하여 Set에 추가
                }
                // 시계 방향 회전
                s.addFirst(s.removeLast());
            }

            // Set을 ArrayList로 변환 후 내림차순 정렬
            ArrayList<Long> list = new ArrayList<>(hexList);
            Collections.sort(list, Collections.reverseOrder());

            // k번째로 큰 값 출력
            System.out.println("#" + t + " " + list.get(k - 1));
        }
    }
}
