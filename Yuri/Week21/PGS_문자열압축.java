class Solution {
    public int solution(String s) {
        int answer = s.length();
            int cnt = 1;
            StringBuilder result = new StringBuilder();

            for (int i = 1; i <= s.length()/2; i++) {
                result.setLength(0);
                String base = s.substring(0, i);

                for (int j = i; j <= s.length(); j+=i) {
                    int endIdx = Math.min(i+j, s.length());
                    String tmp = s.substring(j, endIdx);
                    if(base.equals(tmp))
                        cnt++;
                    else {
                        if(cnt > 1)
                            result.append(cnt);
                        result.append(base);
                        base = tmp;
                        cnt = 1;
                    }
                }
                result.append(base);
                answer = Math.min(answer, result.length());

            }
            return answer;
    }
}
