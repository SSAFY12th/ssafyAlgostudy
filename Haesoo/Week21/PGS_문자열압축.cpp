#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;

int solution(string s) {
    int answer = 0;
    answer = s.length(); // 1개 단위로 압축 시의 길이..

    for (int i = 1; i <= s.length() / 2; i++) {
        vector <string> v;
        string ss;
        for (int j = 0; j < s.length(); j++) {
            ss += s[j]; // 문자열 한 칸씩 넣고
            if (ss.length() == i) {
                v.push_back(ss); // 문자열 길이 i 도달 -> 문자열 조각 저장
                ss = ""; // 초기화
            }
            else if (j == s.length() - 1) v.push_back(ss);
        } // 길이를 몇으로 자를지?

        string temp; // 압축 문자열
        while (v.size() != 0) {
            int cnt = 0; // 조각 중복 횟수
            for (int j = 1; j < v.size(); j++) {
                if (v[0] == v[j]) cnt++; // 각 문자열 조각들이 같은지 확인
                else break;
            }
            
            if (cnt > 0) temp += (to_string(cnt + 1) + v[0]); // 문자열 내에 같은 조각들이 존재할 시
            else temp += v[0]; // 존재하지 않는다면 그대로 추가
            
            v.erase(v.begin(), v.begin() + cnt + 1); // 중복된 조각만큼 삭제
        }
        
        int compare = (int)temp.length();
        answer = min(answer, compare); // 처음 길이랑, 압축 길이 비교
    }

    return answer;
}
