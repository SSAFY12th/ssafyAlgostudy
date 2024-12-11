class Solution {
    public int solution(String s) {
        int minLength = s.length(); // 초기 최소 길이를 문자열 전체 길이로 설정

        // 압축 단위를 1부터 문자열 길이의 절반까지 반복
        for (int unit = 1; unit <= s.length() / 2; unit++) {
            StringBuilder compressed = new StringBuilder(); // 압축 결과를 저장할 StringBuilder
            String prev = s.substring(0, unit); // 첫 번째 단위를 초기 이전 문자열로 설정
            int count = 1; // 동일한 단위 문자열의 반복 횟수

            // 문자열을 단위 길이만큼 순회하며 압축
            for (int j = unit; j < s.length(); j += unit) {
                // 현재 단위 문자열 추출 (범위를 초과하면 남은 문자열을 사용)
                String current = j + unit <= s.length() ? s.substring(j, j + unit) : s.substring(j);

                if (prev.equals(current)) {
                    count++; // 이전 단위와 같으면 카운트 증가
                } else {
                    // 이전 단위 문자열과 반복 횟수를 압축 결과에 추가
                    if (count > 1) compressed.append(count); // 반복 횟수가 2 이상일 때만 추가
                    compressed.append(prev); // 이전 단위 문자열 추가

                    // 현재 단위를 새로운 이전 문자열로 설정
                    prev = current;
                    count = 1; // 반복 횟수 초기화
                }
            }

            // 마지막 단위 문자열 처리
            if (count > 1) compressed.append(count); // 반복 횟수가 2 이상일 때 추가
            compressed.append(prev); // 마지막 단위 문자열 추가

            // 압축된 문자열의 길이와 최소 길이를 비교하여 갱신
            minLength = Math.min(minLength, compressed.length());
        }

        return minLength; // 최소 압축 문자열의 길이 반환
    }
}
