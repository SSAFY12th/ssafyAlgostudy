class Solution {
    public int solution(String s) {
        int answer = s.length();

        for (int unit = 1; unit <= s.length() / 2; unit++) {
            int compressedLength = compress(s, unit);
            answer = Math.min(answer, compressedLength);
        }

        return answer;
    }

    private int compress(String s, int unit) {
        StringBuilder compressed = new StringBuilder();
        String prev = s.substring(0, unit);
        int count = 1;

        for (int i = unit; i < s.length(); i += unit) {
            String current = s.substring(i, Math.min(i + unit, s.length()));

            if (current.equals(prev)) {
                count++;
            }
            else {
                compressed.append(count > 1 ? count : "").append(prev);
                prev = current;
                count = 1;
            }
        }

        compressed.append(count > 1 ? count : "").append(prev);

        return compressed.length();
    }
}
