import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int N, S;

    // 그림 클래스
    static class Art implements Comparable<Art> {
        int height;
        int price;

        Art(int height, int price) {
            this.height = height;
            this.price = price;
        }

        // compareTo 메서드 구현
        @Override
        public int compareTo(Art other) {
            return this.height - other.height;
        }
    }

    static Art[] arts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arts = new Art[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            arts[i] = new Art(height, price);
        }

        // 그림의 높이 순서로 정렬
        Arrays.sort(arts);

        // 정렬된 상태로 유지
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 0); // 기본 값 추가

        int maxPrice = 0;

        // 그림 순회
        for (Art art : arts) {
            // 현재 후보를 사용해 탐색
            // floorKey : key값 "이하" 값 반환 : 없다면 null 반환
            // 그림의 높이 - S 값
            Integer lowerKey = map.floorKey(art.height - S);

            // 이하 값이 null이 아니라면
            if (lowerKey != null) {
                // 최댓값 갱신
                maxPrice = Math.max(maxPrice, map.get(lowerKey) + art.price);
            }

            // 키 - 값 쌍 저장
            map.put(art.height, maxPrice);
        }

        System.out.println(maxPrice);
    }
}

// 높이가 작은 작품부터 차례대로 고려, 이전에 선택된 작품들 중에서 조건을 만족하는 최댓값 찾기

// 이전에 선택한 작품의 높이와 현재 선택하려는 작품의 높이가 S 이하,
// 즉, 현재 선택하려는 작품의 높이와, 이전에 선택한 작품의 높이 차이를 비교
// 그림을 하나씩 선택하면서 최적의 가격을 구하기


// 1. 높이 순으로 일단 정렬
// 2. 이제 어떤 그림을 고를지
// 3. 해당 높이에서,

// 해당 높이 [키] 가 골라졌을때, 취할 수 있는 최댓[값]을 저장한다.
